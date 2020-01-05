package com.ka2kama.application

import com.ka2kama.core.{Todo, TodoId}
import com.ka2kama.application.json.TodoJsonSupport.TodoEncoder
import javax.inject.Inject
import play.api.Logger
import play.api.mvc._

import scala.concurrent.Future

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val logger = Logger(this.getClass)

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