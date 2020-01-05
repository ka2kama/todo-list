package com.ka2kama.application

import io.circe.Json
import io.circe.syntax._
import play.api.test.Helpers._

class HomeControllerSpec extends ControllerSpecBase {

  """「/」にGETメソッドでアクセス""" - {

    """HTTP Statusは200が返る""" in {
      val home = getResponse("/")

      status(home) shouldBe 200
    }

    """コンテンツはJSONが返る""" in {
      val home = getResponse("/")

      contentAsCirceJson(home) shouldBe Json.obj(
        "id" -> 1.asJson,
        "content" -> "掃除".asJson,
        "state" -> 0.asJson,
      )
    }
  }
}
