package com.ka2kama.todolist.core.support.json

import com.ka2kama.todolist.common.SpecBase
import com.ka2kama.todolist.common.SpecBase.EitherOps
import com.ka2kama.todolist.core.support.json.todo.TodoJsonEncoder
import com.ka2kama.todolist.core.todo.domain.model._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

class TodoJsonEncoderSpec extends SpecBase with TodoJsonEncoder {

  "エンコード" - {
    "TodoIdを数値に変換したJSONを作成する" in {
      val todo = Todo(TodoId(1000), Content("テスト"), Done)

      val json = """
         | {
         | "id": 1000,
         | "content": "テスト",
         | "state": 2
         | }
        """.stripMargin

      todo.asJson shouldBe parse(json).successVal
    }
  }
}
