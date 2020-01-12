package com.ka2kama.application.json

import com.ka2kama.core.TodoId
import io.circe.{Decoder, Encoder}

trait TodoJsonSupport {
  implicit val idEncoder: Encoder[TodoId] = Encoder[Long].contramap(_.value)
  implicit val idDecoder: Decoder[TodoId] = Decoder[Long].map(TodoId)
}
