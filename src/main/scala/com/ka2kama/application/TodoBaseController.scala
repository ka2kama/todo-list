package com.ka2kama.application

import io.circe.Encoder
import io.circe.syntax._
import javax.inject.Inject
import play.api.Logging
import play.api.http.Writeable
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents, Result}

abstract class TodoBaseController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc)
    with Logging
    with Circe {

  implicit class OptionToResult[A](val self: Option[A]) {

    def toJsonResult(implicit encoder: Encoder[A]): Result =
      self.map(_.asJson).toResult

    def toResult(implicit writeable: Writeable[A]): Result =
      self.fold(NotFound: Result)(Ok(_))
  }
}
