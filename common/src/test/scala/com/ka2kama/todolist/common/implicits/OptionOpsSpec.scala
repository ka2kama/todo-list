package com.ka2kama.todolist.common.implicits

import com.ka2kama.todolist.common.SpecBase

import scala.util.Success

class OptionOpsSpec extends SpecBase {

  import com.ka2kama.todolist.common.implicits.Implicits.OptionOps

  "toTry" - {
    "引数なし" - {
      "値が存在すればSuccessに変換して返す" in {
        val value = "foo"
        Some(value).toTry shouldBe Success(value)
      }

      "値が存在しなければFailure(NoSuchElementException)を返す" in {
        val result = None.toTry
        result.isFailure shouldBe true
        an[NoSuchElementException] should be thrownBy result.get
      }
    }

    "引数あり" - {
      "値が存在すればSuccessに変換して返す" in {
        val value = "foo"
        Some(value).toTry(new Exception) shouldBe Success(value)
      }

      "値が存在しなければ引数で指定した例外を返す" in {
        val errorMessage = "test exception"
        val result       = None.toTry(new UnsupportedOperationException(errorMessage))

        result.isFailure shouldBe true
        the[UnsupportedOperationException] thrownBy {
          result.get
        } should have message errorMessage
      }
    }
  }
}
