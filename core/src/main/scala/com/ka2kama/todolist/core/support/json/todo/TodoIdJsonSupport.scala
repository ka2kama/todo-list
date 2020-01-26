package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.{ValueClassJsonDecoder, ValueClassJsonEncoder}
import com.ka2kama.todolist.core.todo.domain.model.TodoId
import io.circe.{Decoder, Encoder}

trait TodoIdJsonSupport extends TodoIdJsonEncoder with TodoIdJsonDecoder

trait TodoIdJsonEncoder extends ValueClassJsonEncoder {
  implicit val todoIdEncoder: Encoder[TodoId] = encoder(_.value)
}

trait TodoIdJsonDecoder extends ValueClassJsonDecoder {
  implicit val todoIdDecoder: Decoder[TodoId] = decoder(TodoId)

}
