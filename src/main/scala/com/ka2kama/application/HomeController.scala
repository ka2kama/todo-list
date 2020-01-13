package com.ka2kama.application

import com.ka2kama.application.json.TodoJsonSupport
import com.ka2kama.core.todo.service.TodoService
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

  def list: Action[AnyContent] = Action.async { implicit request =>
    logger.info("list: ")

    val todos = todoService.list
    Future.successful(Ok(todos.asJson))
  }
}
