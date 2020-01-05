package ka2kama.application

import play.api.test.FakeRequest
import play.api.test.Helpers._

class HomeControllerSpec extends ControllerSpecBase {

  """「/」にGETメソッドでアクセス""" - {

    """HTTP Statusは200が返る""" in {
      val home = getResponse("/")

      status(home) shouldBe 200
    }

    """コンテンツは"Hello World"が返る""" in {
      val home = getResponse("/")

      contentAsString(home) shouldBe "Hello World"
    }
  }
}
