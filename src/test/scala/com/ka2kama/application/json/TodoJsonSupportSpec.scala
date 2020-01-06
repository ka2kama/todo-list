package com.ka2kama.application.json

import com.ka2kama.SpecBase
import com.ka2kama.SpecBase.TryOps
import com.ka2kama.application.json.TodoJsonSupport.TodoDecoder
import com.ka2kama.core.{Todo, TodoId}
import io.circe.{Decoder, HCursor, Json}
import io.circe.syntax._
import io.circe.parser._
import io.circe.generic.auto._
import io.circe.generic.semiauto._

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


    """デコードTest""" in {

      case class FooId(value: Int)
      case class Foo(fooId: FooId, name: String)

      implicit val decoder: Decoder[FooId] = (c: HCursor) => {
        println(c.value)
        c.value.as[Int].map(FooId)
      }

      implicit val fooDecoder: Decoder[Foo] = deriveDecoder

      val json = Json.obj(
        "fooId" -> 1.asJson,
        "name" -> "FooName".asJson
      )

      decode[Foo](json.noSpaces).right.get shouldBe Foo(FooId(1), "FooName")
    }
  }
}
