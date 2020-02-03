package com.ka2kama.todolist.core.support.json

import com.ka2kama.todolist.common.SpecBase
import com.ka2kama.todolist.common.SpecBase.EitherOps
import com.ka2kama.todolist.core.domain.todo.model.{Content, Todo, TodoId, Unfinished}
import com.ka2kama.todolist.core.support.json.todo.TodoJsonDecoder
import io.circe.generic.auto._
import io.circe.parser.decode

class TodoJsonDecoderSpec extends SpecBase with TodoJsonDecoder {

  "デコード" - {
    "idをTodoIdに変換したインスタンスを作成する" in {
      val json = """
                   | {
                   | "id": 1,
                   | "content": "掃除",
                   | "state": 0
                   | }
        """.stripMargin

      val todo = Todo(TodoId(1), Content("掃除"), Unfinished)

      decode[Todo](json).successVal shouldBe todo
    }
  }
}
