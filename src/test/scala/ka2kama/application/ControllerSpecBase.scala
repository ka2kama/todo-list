package ka2kama.application

import io.circe.Json
import io.circe.parser._
import io.circe.syntax._
import ka2kama.SpecBase
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.{HttpEntity, Writeable}
import play.api.libs.json.JsValue
import play.api.mvc.{Request, ResponseHeader, Result}
import play.api.test.Helpers.{route, _}
import play.api.test.{FakeRequest, Helpers, Injecting}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

abstract class ControllerSpecBase extends SpecBase
  with GuiceOneAppPerTest
  with Injecting {

  protected val NotFound: Result = Result(ResponseHeader(NOT_FOUND), HttpEntity.NoEntity)

  def getResponse[T](request: Request[T])(implicit w: Writeable[T]): Future[Result] = {
    route(app, request).getOrElse(Future.successful(NotFound))
  }

  def getResponse(path: String, method: String = GET, params: Option[Any] = None): Future[Result] = {
    val request = FakeRequest(method, path)
    getResponse(request)
  }

  def contentAsCirceJson(of: Future[Result]): Try[Json] =
    parse(contentAsString(of)).fold(Failure(_), Success(_))
}
