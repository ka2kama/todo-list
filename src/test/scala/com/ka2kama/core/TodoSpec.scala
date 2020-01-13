package com.ka2kama.core

import com.ka2kama.SpecBase

class TodoSpec extends SpecBase {
  """TodoIdによる同一性判定""" - {
    """TodoIdの値が等しいインスタンス同士は同一とみなされる""" in {

      val expected = Todo(TodoId(10), "expected", 1)
      val actual = Todo(TodoId(10), "actual", 2)

      expected shouldEqual actual
    }

    """TodoIdの値が異なるインスタンス同士は別物とみなされる""" in {

      val content = "content"
      val state = 1
      val expected = Todo(TodoId(100), content, state)
      val actual = Todo(TodoId(101), content, state)

      expected should not equal actual
    }
  }
}
