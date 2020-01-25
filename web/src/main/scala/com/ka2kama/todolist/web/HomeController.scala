package com.ka2kama.todolist.web
import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, ControllerComponents}

class HomeController @Inject() (cc: ControllerComponents) extends TodoBaseController(cc) {

  def index: Action[AnyContent] = Action {
    logger.info("index: ")
    Redirect("/todos")
  }
}
