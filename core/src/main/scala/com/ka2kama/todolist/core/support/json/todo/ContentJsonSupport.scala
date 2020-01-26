package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.{SingleValueJsonDecoder, SingleValueJsonEncoder}
import com.ka2kama.todolist.core.todo.domain.model.Content
import io.circe.{Decoder, Encoder}

trait ContentJsonSupport extends ContentJsonEncoder with ContentJsonDecoder

trait ContentJsonEncoder extends SingleValueJsonEncoder {
  implicit val contentEncoder: Encoder[Content] = encoder(_.value)
}

trait ContentJsonDecoder extends SingleValueJsonDecoder {
  implicit val contentDecoder: Decoder[Content] = decoder(Content.apply)
}
