package com.ka2kama.todolist.web

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import com.ka2kama.todolist.core.domain.list.ListService
import com.ka2kama.todolist.core.domain.todo.model.TodoId
import io.circe.generic.auto._
import io.circe.syntax._
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import play.api.Environment
import play.api.mvc._
import views.html.defaultpages.todo

import javax.inject.Inject

final class ListController @Inject() (listService: ListService, cc: ControllerComponents)
    extends TodoBaseController(cc) {

  def list: Action[AnyContent] = ActionAsync {
    logger.info("list: ")
    for {
      todosT <- listService.list
    } yield Ok(todosT.asJson)
  }

  def get(id: Long): Action[AnyContent] = ActionAsync {
    logger.info("get: ")

    val response = for {
      todo <- listService.get(TodoId(id))
    } yield {
      todo.asJson
    }
    response.fold(NotFound: Result)(Ok(_))
  }

  def stream: Action[AnyContent] = ActionAsync {
    logger.info("stream: ")
    Task {
      Ok.sendFile(Environment.simple().getFile("/tmp/sample.pdf"))
    }
  }
}
