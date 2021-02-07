package com.ka2kama.todolist.web

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import play.api.Logging
import play.api.mvc._

import javax.inject.Inject

abstract class TodoBaseController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc)
    with TodoBaseControllerOps
    with Logging {}

trait TodoBaseControllerOps { self: TodoBaseController =>
  def ActionAsync[A <: Result](task: Task[A]): Action[AnyContent] = Action.async {
    task.runToFuture
  }
}
