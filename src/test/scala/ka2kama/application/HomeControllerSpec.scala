package ka2kama.application

import org.scalatest.TryValues._
import play.api.libs.json.Json
import play.api.test.Helpers._

class HomeControllerSpec extends ControllerSpecBase {

  """「/」にGETメソッドでアクセス""" - {

    """HTTP Statusは200が返る""" in {
      val home = getResponse("/")

      status(home) shouldBe 200
    }

    """コンテンツはJSONのリストが返る""" in {
      val home = getResponse("/")

      contentAsJson(home).success.value shouldBe Json.obj(
        "hello"-> "world",
        "language" -> "scala")
    }
  }
}
