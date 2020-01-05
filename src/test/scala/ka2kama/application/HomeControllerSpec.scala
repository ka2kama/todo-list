package ka2kama.application

import com.ka2kama.application.json.{JsonEncoder, TodoJsonSupport}
import com.ka2kama.core.{Todo, TodoId}
import io.circe.Json
import io.circe.syntax._
import org.scalatest.TryValues._
import play.api.test.Helpers._
import io.circe.generic.auto._
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
class HomeControllerSpec extends ControllerSpecBase {

  """「/」にGETメソッドでアクセス""" - {

    """HTTP Statusは200が返る""" in {
      val home = getResponse("/")

      status(home) shouldBe 200
    }

    """コンテンツはJSONが返る""" in {
      val home = getResponse("/")

      contentAsCirceJson(home).success.value shouldBe Json.obj(
        "id" -> 1.asJson,
        "content" -> "掃除".asJson,
        "state" -> 0.asJson,
      )

      val json = Json.obj(
        "id" -> 1.asJson,
        "content" -> "掃除".asJson,
        "state" -> 0.asJson,
      )

      implicit val decoder: Decoder[Todo] = TodoJsonSupport.decoder

      json.as[Todo].right.get shouldBe new Todo(TodoId(1), "掃除", 0)
    }
  }
}
