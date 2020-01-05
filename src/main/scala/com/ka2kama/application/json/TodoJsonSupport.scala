package com.ka2kama.application.json

import com.ka2kama.core.{Todo, TodoId}
import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.syntax._
import io.circe.generic.auto._

object TodoJsonSupport {

  implicit class TodoEncoder(val self: Todo) extends JsonEncoder[Todo] {
    override implicit val encoder: Encoder[Todo] = TodoJsonSupport.encoder
  }

  implicit val encoder: Encoder[Todo] = (a: Todo) => Json.obj(
    "id" -> a.id.value.asJson,
    "content" -> a.content.asJson,
    "state"-> a.state.asJson
  )

  implicit val decoder: Decoder[Todo] = (c: HCursor) =>
    for {
      id <- c.downField("id").as[Long]
      content <- c.downField("content").as[String]
      state <- c.downField("state").as[Int]
    } yield {
      new Todo(TodoId(id), content, state)
    }


}