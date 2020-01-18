package com.ka2kama.application

import javax.inject.Inject
import play.api.Logging
import play.api.libs.circe.Circe
import play.api.mvc._

abstract class TodoBaseController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc)
    with Logging
    with Circe
