package com.ka2kama.application.json

import com.ka2kama.core.{Todo, TodoId}
import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}

object TodoJsonSupport {

  object TodoEncoder extends JsonEncoder[Todo] {
    implicit val idEncoder: Encoder[TodoId] = Encoder[Long].contramap(_.value)
    implicit val encoder: Encoder[Todo] = deriveEncoder
  }

  object TodoDecoder extends JsonDecoder[Todo] {
    implicit val idDecoder: Decoder[TodoId] = _.value.as[Long].map(TodoId)
    implicit val decoder: Decoder[Todo] = deriveDecoder
  }

  implicit class TodoEncoderExt(val self: Todo) extends JsonEncoderExt[Todo] {
    implicit val encoder: Encoder[Todo] = TodoEncoder.encoder
  }
}