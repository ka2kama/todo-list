package com.ka2kama.application

import javax.inject.Inject
import play.api.mvc._

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index(): Action[AnyContent] = Action(Ok("hello world"))
}