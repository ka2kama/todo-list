package com.ka2kama.todolist.web

import cats.implicits._
import com.ka2kama.todolist.core.domain.list.ListService
import com.ka2kama.todolist.core.domain.todo.model.TodoId
import com.ka2kama.todolist.core.support.json.todo.TodoJsonEncoder
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.ExecutionContext

final class ListController @Inject() (listService: ListService, cc: ControllerComponents)(
    implicit ec: ExecutionContext
) extends TodoBaseController(cc)
    with TodoJsonEncoder {

  def list: Action[AnyContent] = Action.async {
    logger.info("list: ")

    val result = for {
      todos <- listService.list
    } yield todos.asJson

    result.map(Ok(_))
  }

  def get(id: Long): Action[AnyContent] = Action.async {
    logger.info("get: ")

    val todo     = listService.get(TodoId(id))
    val todoJson = todo.map(_.asJson)

    todoJson.fold(NotFound: Result)(Ok(_))
  }
}
