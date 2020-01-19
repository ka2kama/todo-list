package com.ka2kama.todolist.core

import com.ka2kama.todolist.common.SpecBase
import com.ka2kama.todolist.core.todo.domain.model._

class TodoSpec extends SpecBase {
  "TodoIdによる同一性判定" - {
    "TodoIdの値が等しいインスタンス同士は同一とみなされる" in {

      val expected = Todo(TodoId(10), Content("expected"), Doing)
      val actual = Todo(TodoId(10), Content("actual"), Done)

      expected shouldEqual actual
    }

    "TodoIdの値が異なるインスタンス同士は別物とみなされる" in {

      val content = Content("content")
      val state = Doing
      val expected = Todo(TodoId(100), content, state)
      val actual = Todo(TodoId(101), content, state)

      expected should not equal actual
    }
  }
}
