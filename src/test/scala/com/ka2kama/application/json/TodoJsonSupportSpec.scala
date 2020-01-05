package com.ka2kama.application.json

import com.ka2kama.SpecBase
import com.ka2kama.SpecBase.TryOps
import com.ka2kama.application.json.TodoJsonSupport.TodoDecoder
import com.ka2kama.core.{Todo, TodoId}
import io.circe.Json
import io.circe.syntax._

class TodoJsonSupportSpec extends SpecBase {
  """デコード""" - {

    """デコード時にidをTodoIdに変換したインスタンスを作成する""" in {
      val json = Json.obj(
        "id" -> 1.asJson,
        "content" -> "掃除".asJson,
        "state" -> 0.asJson,
      )

      TodoDecoder.decode(json).successVal shouldBe new Todo(TodoId(1), "掃除", 0)
    }
  }
}
