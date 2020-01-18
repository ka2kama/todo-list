package com.ka2kama.application

import com.ka2kama.application.json.TodoJsonSupport
import com.ka2kama.core.todo.TodoService
import com.ka2kama.core.todo.domain.model.TodoId
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

    val todoOpt = todoService.get(TodoId(id))
    Future.successful(todoOpt.toJsonResult)
  }
}
