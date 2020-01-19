package com.ka2kama.web

import com.ka2kama.core.todo.TodoService
import com.ka2kama.core.todo.domain.model.TodoId
import com.ka2kama.web.json.TodoJsonSupport
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.Future

class HomeController @Inject()(todoService: TodoService,
                               cc: ControllerComponents)
    extends TodoBaseController(cc)
    with TodoJsonSupport {

  def index: Action[AnyContent] = {
    logger.info("index: ")
    list
  }

  def list: Action[AnyContent] = Action.async {
    logger.info("list: ")

    val todos = todoService.list
    Future.successful(Ok(todos.asJson))
  }

  def get(id: Long): Action[AnyContent] = Action.async {
    logger.info("get: ")

    val result = todoService.get(TodoId(id)) match {
      case Some(todo) => Ok(todo.asJson)
      case _          => NotFound
    }
    Future.successful(result)
  }
}