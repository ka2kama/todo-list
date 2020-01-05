package com.ka2kama.application

import javax.inject.Inject
import play.api.Logger
import play.api.db.DBApi
import play.api.libs.json.Json
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
    val json = Json.obj(
      "hello"-> "world",
      "language" -> "scala")
    Future.successful(Ok(json))
  }
}