package com.ka2kama.application.json

import com.ka2kama.SpecBase
import com.ka2kama.SpecBase.EitherOps
import com.ka2kama.core.{Todo, TodoId}
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

class TodoJsonSupportSpec extends SpecBase {

  import TodoJsonSupport._

  """エンコード""" - {
    """TodoIdを数値に変換したJSONを作成する""" in {
      val todo = new Todo(TodoId(1000), "テスト", 2)

      todo.asJson shouldBe
        parse("""{ "id": 1000, "content": "テスト", "state": 2 }""").successVal
    }
  }

  """デコード""" - {

    """idをTodoIdに変換したインスタンスを作成する""" in {
      val json = """
         | {
         | "id": 1,
         | "content": "掃除",
         | "state": 0
         | }
        """.stripMargin

      decode[Todo](json).successVal shouldBe new Todo(TodoId(1), "掃除", 0)
    }
  }
}
