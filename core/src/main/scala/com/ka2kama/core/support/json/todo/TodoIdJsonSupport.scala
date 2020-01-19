package com.ka2kama.core.support.json.todo

import com.ka2kama.core.support.json.ValueClassJsonSupport
import com.ka2kama.core.todo.domain.model.TodoId
import io.circe.{Decoder, Encoder}

trait TodoIdJsonSupport extends ValueClassJsonSupport {
  implicit val todoIdEncoder: Encoder[TodoId] = encoder(_.value)
  implicit val todoIdDecoder: Decoder[TodoId] = decoder(TodoId)
}
