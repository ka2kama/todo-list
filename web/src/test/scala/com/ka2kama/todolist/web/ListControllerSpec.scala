package com.ka2kama.todolist.web

import play.api.libs.json._
import play.api.test.Helpers._

class ListControllerSpec extends ControllerSpecBase {

  "「/todos」にGETメソッドでアクセス" - {

    "コンテンツはJSONでTODOのリストが返る" in {
      val home = getResponse("/todos")

      status(home) shouldBe OK
      contentType(home) shouldBe Some("application/json")
      contentAsJson(home) shouldBe Json.arr(
        Json.obj(
          "id"      -> 1,
          "content" -> "掃除",
          "state"   -> 0,
        ),
        Json.obj(
          "id"      -> 2,
          "content" -> "洗濯",
          "state"   -> 1,
        ),
        Json.obj(
          "id"      -> 3,
          "content" -> "料理",
          "state"   -> 2,
        ),
      )
    }
  }

  "「/todos/1」にGETメソッドでアクセス" - {

    "TodoIdが1、contentが掃除、stateが0のTodoがJSONで返る" in {
      val home = getResponse("/todos/1")

      status(home) shouldBe OK
      contentType(home) shouldBe Some("application/json")
      contentAsJson(home) shouldBe Json.obj(
        "id"      -> 1,
        "content" -> "掃除",
        "state"   -> 0,
      )
    }
  }
}
