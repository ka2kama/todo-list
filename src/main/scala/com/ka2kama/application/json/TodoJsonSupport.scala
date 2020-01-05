package com.ka2kama.application.json

import com.ka2kama.core.{Todo, TodoId}
import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder, HCursor}

object TodoJsonSupport {

  object TodoEncoderExt extends JsonEncoder[Todo] {
    implicit val idEncoder: Encoder[TodoId] = Encoder[Long].contramap(_.value)
    implicit val encoder: Encoder[Todo] = deriveEncoder
  }

  object TodoDecoder extends JsonDecoder[Todo] {
    implicit val decoder: Decoder[Todo] = (c: HCursor) => {
      for {
        id <- c.downField("id").as[Long].map(TodoId)
        content <- c.downField("content").as[String]
        state <- c.downField("state").as[Int]
      } yield {
        new Todo(id, content, state)
      }
    }
  }

  implicit class TodoEncoderExt(val self: Todo) extends JsonEncoderExt[Todo] {
    implicit val encoder: Encoder[Todo] = TodoEncoderExt.encoder
  }
}