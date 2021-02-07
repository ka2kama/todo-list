package com.ka2kama.todolist.web

import com.ka2kama.todolist.core.domain.list.ListService
import com.ka2kama.todolist.core.domain.todo.model.TodoId
import monix.execution.Scheduler.Implicits.global
import play.api.Environment
import play.api.mvc._
import com.ka2kama.todolist.web.support.json.JsonSupport._
import play.api.libs.json.Json

import javax.inject.Inject

final class ListController @Inject() (listService: ListService, cc: ControllerComponents)
    extends TodoBaseController(cc) {

  def list: Action[AnyContent] = ActionAsync {
    logger.info("list: ")

    val todos = listService.list
    todos.map(x => Ok(Json.toJson(x)))
  }

  def get(id: Long): Action[AnyContent] = ActionAsync {
    logger.info("get: ")

    val todo = listService.get(TodoId(id))

    todo.fold(NotFound: Result)(x => Ok(Json.toJson(x)))
  }

  def stream: Action[AnyContent] = Action {
    logger.info("stream: ")

    Ok.sendFile(Environment.simple().getFile("/tmp/fileToServe.pdf"))
  }
}
