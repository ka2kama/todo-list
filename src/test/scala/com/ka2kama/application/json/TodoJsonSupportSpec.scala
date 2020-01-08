package com.ka2kama.application.json

import com.ka2kama.SpecBase
import com.ka2kama.SpecBase.TryOps
import com.ka2kama.application.json.circe.TodoJsonSupportOnCirce
import com.ka2kama.application.json.circe.TodoJsonSupportOnCirce.TodoJsonDecoder
import com.ka2kama.core.{Todo, TodoId}

class TodoJsonSupportSpec extends SpecBase {
  """デコード""" - {

    """デコード時にidをTodoIdに変換したインスタンスを作成する""" in {
      val json = """
         | {
         | "id" : 1,
         | "content" : "掃除",
         | "state" : 0
         | }
        """.stripMargin

      TodoJsonSupportOnCirce.todoJsonDecoder
        .decode(json)
        .successVal shouldBe new Todo(TodoId(1), "掃除", 0)
    }
  }
}
