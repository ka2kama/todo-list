package com.ka2kama.core

import com.ka2kama.SpecBase

class TodoSpec extends SpecBase {
  """TodoIdによる同一性判定""" - {
    """TodoIdの値が等しいインスタンス同士は同一とみなされる""" in {

      val expected = new Todo(TodoId(10), "expected", 1)
      val actual = new Todo(TodoId(10), "actual", 2)

      expected shouldEqual actual
    }

    """TodoIdの値が異なるインスタンス同士は別物とみなされる""" in {

      val content = "content"
      val state = 1
      val expected = new Todo(TodoId(100), content, state)
      val actual = new Todo(TodoId(101), content, state)

      expected should not equal actual
    }
  }
}
