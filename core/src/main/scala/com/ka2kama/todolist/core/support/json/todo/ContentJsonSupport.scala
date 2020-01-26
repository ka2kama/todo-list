package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.{ValueClassJsonDecoder, ValueClassJsonEncoder}
import com.ka2kama.todolist.core.todo.domain.model.Content
import io.circe.{Decoder, Encoder}

trait ContentJsonSupport extends ContentJsonEncoder with ContentJsonDecoder

trait ContentJsonEncoder extends ValueClassJsonEncoder {
  implicit val contentEncoder: Encoder[Content] = encoder(_.value)
}

trait ContentJsonDecoder extends ValueClassJsonDecoder {
  implicit val contentDecoder: Decoder[Content] = decoder(Content)
}
