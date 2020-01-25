package com.ka2kama.todolist.web

import io.circe.Json
import io.circe.syntax._
import play.api.test.Helpers._

class TodoControllerSpec extends ControllerSpecBase {

  "「/todos」にGETメソッドでアクセス" - {

    "コンテンツはJSONでTODOのリストが返る" in {
      val home = getResponse("/todos")

      status(home) shouldBe OK
      contentType(home) shouldBe Some("application/json")
      contentAsCirceJson(home) shouldBe Json.arr(
        Json.obj(
          "id"      -> 1.asJson,
          "content" -> "掃除".asJson,
          "state"   -> 0.asJson
        ),
        Json.obj(
          "id"      -> 2.asJson,
          "content" -> "洗濯".asJson,
          "state"   -> 1.asJson
        ),
        Json.obj(
          "id"      -> 3.asJson,
          "content" -> "料理".asJson,
          "state"   -> 2.asJson
        )
      )
    }
  }

  "「/todos/1」にGETメソッドでアクセス" - {

    "TodoIdが1、contentが掃除、stateが0のTodoがJSONで返る" in {
      val home = getResponse("/todos/1")

      status(home) shouldBe OK
      contentType(home) shouldBe Some("application/json")
      contentAsCirceJson(home) shouldBe Json.obj(
        "id"      -> 1.asJson,
        "content" -> "掃除".asJson,
        "state"   -> 0.asJson
      )
    }
  }
}
