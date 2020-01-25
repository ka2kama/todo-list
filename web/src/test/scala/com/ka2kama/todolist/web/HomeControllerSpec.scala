package com.ka2kama.todolist.web

import play.api.test.Helpers._

class HomeControllerSpec extends ControllerSpecBase {

  "「/」にGETメソッドでアクセス" - {

    "｢/todos｣へリダイレクトする" in {
      val home = getResponse("/")

      status(home) shouldBe SEE_OTHER
      redirectLocation(home) shouldBe Some("/todos")
    }
  }
}
