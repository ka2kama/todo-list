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

  val todos = Seq(
    Todo(TodoId(1), "掃除", 0),
    Todo(TodoId(2), "洗濯", 1),
    Todo(TodoId(3), "料理", 2),
  )

  def index(): Action[AnyContent] = {
    logger.info("index: ")
    list()
  }

  def list(): Action[AnyContent] = Action.async { implicit request =>
    logger.info("list: ")

    Future.successful(Ok(todos.asJson))
  }
}
