package com.ka2kama.todolist.web

import cats.implicits._
import com.ka2kama.todolist.core.support.json.todo.TodoJsonSupport
import com.ka2kama.todolist.core.todo.TodoService
import com.ka2kama.todolist.core.todo.domain.model.TodoId
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.ExecutionContext

final class TodoController @Inject() (
    todoService: TodoService,
    cc: ControllerComponents
)(implicit ec: ExecutionContext)
    extends TodoBaseController(cc)
    with TodoJsonSupport {

  def list: Action[AnyContent] = Action.async {
    logger.info("list: ")

    val result = for {
      todos <- todoService.list
    } yield todos.asJson

    result.map(Ok(_))
  }

  def get(id: Long): Action[AnyContent] = Action.async {
    logger.info("get: ")

    val result = for {
      todo <- todoService.get(TodoId(id))
    } yield todo.asJson

    result.fold(NotFound: Result)(Ok(_))
  }
}
