package com.ka2kama.application

import io.circe.Json
import io.circe.syntax._
import play.api.test.Helpers._

class HomeControllerSpec extends ControllerSpecBase {

  """「/」にGETメソッドでアクセス""" - {

    """コンテンツはJSONでTODOのリストが返る""" in {
      val home = getResponse("/")

      status(home) shouldBe 200
      contentType(home) shouldBe Some("application/json")
      contentAsCirceJson(home) shouldBe Json.arr(
        Json.obj(
          "id" -> 1.asJson,
          "content" -> "掃除".asJson,
          "state" -> 0.asJson,
        ),
        Json.obj(
          "id" -> 2.asJson,
          "content" -> "洗濯".asJson,
          "state" -> 1.asJson,
        ),
        Json.obj(
          "id" -> 3.asJson,
          "content" -> "料理".asJson,
          "state" -> 2.asJson,
        ),
      )
    }
  }
}
