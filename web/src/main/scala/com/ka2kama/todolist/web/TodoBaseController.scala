package com.ka2kama.todolist.web

import io.circe.Encoder
import io.circe.syntax._
import javax.inject.Inject
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import play.api.Logging
import play.api.libs.circe.Circe
import play.api.mvc._

abstract class TodoBaseController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc)
    with Logging
    with Circe {

  def ActionAsync[A <: Result](task: Task[A]): Action[AnyContent] = Action.async {
    task.runToFuture
  }

  implicit class JsonResult[A: Encoder](val self: A) {
    def toJsonResult: Result = Ok(self.asJson)
  }
}
