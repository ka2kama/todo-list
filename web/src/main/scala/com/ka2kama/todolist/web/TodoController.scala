package com.ka2kama.todolist.web

import com.ka2kama.todolist.core.support.json.todo.TodoJsonSupport
import com.ka2kama.todolist.core.todo.TodoService
import com.ka2kama.todolist.core.todo.domain.model.TodoId
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.Future

final class TodoController @Inject()(todoService: TodoService,
                                     cc: ControllerComponents)
    extends TodoBaseController(cc)
    with TodoJsonSupport {

  def list: Action[AnyContent] = Action.async {
    logger.info("list: ")

    val todos = todoService.list
    Future.successful(Ok(todos.asJson))
  }

  def get(id: Long): Action[AnyContent] = Action.async {
    logger.info("get: ")

    val todoOption = todoService.get(TodoId(id))

    val todoJsonOption = todoOption.map(_.asJson)

    val result = todoJsonOption.fold(NotFound: Result)(Ok(_))
    Future.successful(result)
  }
}
