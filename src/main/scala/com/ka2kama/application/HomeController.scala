package com.ka2kama.application

import com.ka2kama.application.json.TodoJsonSupport
import com.ka2kama.core.{Todo, TodoId}
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.Future

class HomeController @Inject()(cc: ControllerComponents)
    extends TodoBaseController(cc)
    with TodoJsonSupport {

  def index(): Action[AnyContent] = {
    logger.debug("index: ")
    list()
  }

  def list(): Action[AnyContent] = Action.async { implicit request =>
    logger.debug("list: ")
    val todo = new Todo(TodoId(1), "掃除", 0)

    Future.successful(Ok(todo.asJson))
  }
}
