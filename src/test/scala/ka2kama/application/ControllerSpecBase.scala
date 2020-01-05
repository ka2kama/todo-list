package ka2kama.application

import ka2kama.SpecBase
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.{HttpEntity, HttpVerbs, Writeable}
import play.api.mvc.{Request, ResponseHeader, Result}
import play.api.test.Helpers.{route, _}
import play.api.test.{FakeRequest, Injecting}

import scala.concurrent.Future

abstract class ControllerSpecBase extends SpecBase
  with GuiceOneAppPerTest
  with Injecting {

  val NotFound: Result = Result(ResponseHeader(NOT_FOUND), HttpEntity.NoEntity)

  protected def getResponse[T](request: Request[T])(implicit w: Writeable[T]): Future[Result] = {
    route(app, request).getOrElse(Future.successful(NotFound))
  }

  protected def getResponse(path: String, method: String = GET, params: Option[Any] = None): Future[Result] = {
    val request = FakeRequest(method, path)
    getResponse(request)
  }
}
