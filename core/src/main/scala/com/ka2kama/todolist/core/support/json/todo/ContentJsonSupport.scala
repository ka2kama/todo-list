package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.domain.todo.model.Content
import com.ka2kama.todolist.core.support.json.{SingleValueJsonDecoder, SingleValueJsonEncoder}
import io.circe.{Decoder, Encoder}

trait ContentJsonSupport extends ContentJsonEncoder with ContentJsonDecoder

trait ContentJsonEncoder extends SingleValueJsonEncoder {
  implicit val contentEncoder: Encoder[Content] = encoder(_.value)
}

trait ContentJsonDecoder extends SingleValueJsonDecoder {
  implicit val contentDecoder: Decoder[Content] = decoder(Content.apply)
}
