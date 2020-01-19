package com.ka2kama.todolist.web

import com.ka2kama.todolist.common.SpecBase
import com.ka2kama.todolist.common.SpecBase.EitherOps
import io.circe.Json
import io.circe.parser._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.{HttpEntity, Writeable}
import play.api.mvc.{Request, ResponseHeader, Result}
import play.api.test.Helpers.{route, _}
import play.api.test.{FakeRequest, Injecting}

import scala.concurrent.Future

abstract class ControllerSpecBase
    extends SpecBase
    with GuiceOneAppPerTest
    with Injecting {

  protected val NotFound: Result =
    Result(ResponseHeader(NOT_FOUND), HttpEntity.NoEntity)

  def getResponse[T](
    request: Request[T]
  )(implicit w: Writeable[T]): Future[Result] = {
    route(app, request).getOrElse(Future.successful(NotFound))
  }

  def getResponse(path: String, method: String = GET): Future[Result] = {
    val request = FakeRequest(method, path)
    getResponse(request)
  }

  def contentAsCirceJson(of: Future[Result]): Json =
    parse(contentAsString(of)).successVal
}
