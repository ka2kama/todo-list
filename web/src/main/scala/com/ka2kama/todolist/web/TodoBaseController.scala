package com.ka2kama.todolist.web

import javax.inject.Inject
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import play.api.Logging
import play.api.libs.circe.Circe
import play.api.mvc._

abstract class TodoBaseController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc)
    with TodoBaseControllerOps
    with Logging
    with Circe

trait TodoBaseControllerOps { self: TodoBaseController =>
  def ActionAsync[A <: Result](task: Task[A]): Action[AnyContent] = Action.async {
    task.runToFuture
  }
}
