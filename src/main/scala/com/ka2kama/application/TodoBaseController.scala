package com.ka2kama.application

import javax.inject.Inject
import play.api.Logger
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

abstract class TodoBaseController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc)
    with Circe {
  protected val logger: Logger = Logger(this.getClass)
}
