package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.ValueClassJsonSupport
import com.ka2kama.todolist.core.todo.domain.model.Content
import io.circe.{Decoder, Encoder}

trait ContentJsonSupport extends ValueClassJsonSupport {
  implicit val contentEncoder: Encoder[Content] = encoder(_.value)
  implicit val contentDecoder: Decoder[Content] = decoder(Content)
}
