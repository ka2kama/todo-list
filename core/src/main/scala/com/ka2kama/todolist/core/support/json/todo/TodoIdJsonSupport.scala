package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.{SingleValueJsonDecoder, SingleValueJsonEncoder}
import com.ka2kama.todolist.core.todo.domain.model.TodoId
import io.circe.{Decoder, Encoder}

trait TodoIdJsonSupport extends TodoIdJsonEncoder with TodoIdJsonDecoder

trait TodoIdJsonEncoder extends SingleValueJsonEncoder {
  implicit val todoIdEncoder: Encoder[TodoId] = encoder(_.value)
}

trait TodoIdJsonDecoder extends SingleValueJsonDecoder {
  implicit val todoIdDecoder: Decoder[TodoId] = decoder(TodoId.apply)

}
