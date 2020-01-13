package com.ka2kama.application.json

import com.ka2kama.core.TodoId
import io.circe.{Decoder, Encoder}

trait TodoIdJsonSupport {
  implicit val todoIdEncoder: Encoder[TodoId] = Encoder[Long].contramap(_.value)
  implicit val todoIdDecoder: Decoder[TodoId] = Decoder[Long].map(TodoId)
}
