package com.ka2kama.application.json

import com.ka2kama.core.todo.domain.model.TodoId
import io.circe.{Decoder, Encoder}

trait TodoJsonSupport extends TodoIdJsonSupport

trait TodoIdJsonSupport {
  implicit val todoIdEncoder: Encoder[TodoId] = Encoder[Long].contramap(_.value)
  implicit val todoIdDecoder: Decoder[TodoId] = Decoder[Long].map(TodoId)
}
