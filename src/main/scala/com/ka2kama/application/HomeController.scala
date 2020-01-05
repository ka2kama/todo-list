package com.ka2kama.application

import javax.inject.Inject
import play.api.Logger
import play.api.db.DBApi
import play.api.mvc._

import scala.concurrent.Future

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val logger = Logger(this.getClass)

  def index(): Action[AnyContent] = Action.async { implicit request =>
    logger.debug(s"request received")
    Future.successful(Ok("Hello World"))
  }
}