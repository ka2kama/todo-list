package com.ka2kama.todolist.web

import com.ka2kama.todolist.core.support.json.todo.TodoJsonSupport
import com.ka2kama.todolist.core.todo.TodoService
import com.ka2kama.todolist.core.todo.domain.model.TodoId
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.ExecutionContext

final class TodoController @Inject()(
  todoService: TodoService,
  cc: ControllerComponents
)(implicit ec: ExecutionContext)
    extends TodoBaseController(cc)
    with TodoJsonSupport {

  def list: Action[AnyContent] = Action.async {
    logger.info("list: ")

    val todosFuture = todoService.list
    todosFuture.map(todos => Ok(todos.asJson))
  }

  def get(id: Long): Action[AnyContent] = Action.async {
    logger.info("get: ")

    val todoOptionFuture = todoService.get(TodoId(id))

    val todoJsonOption = todoOptionFuture.map(_.map(_.asJson))

    todoJsonOption.map(_.fold(NotFound: Result)(Ok(_)))
  }
}
