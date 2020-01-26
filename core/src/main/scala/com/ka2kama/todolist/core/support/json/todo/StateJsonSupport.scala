package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.{ValueClassJsonDecoder, ValueClassJsonEncoder}
import com.ka2kama.todolist.core.todo.domain.model.State
import io.circe.{Decoder, Encoder}

trait StateJsonSupport extends StateJsonEncoder with StateJsonDecoder

trait StateJsonEncoder extends ValueClassJsonEncoder {
  implicit val stateEncoder: Encoder[State] = encoder(_.value)
}

trait StateJsonDecoder extends ValueClassJsonDecoder {
  implicit val stateDecoder: Decoder[State] = decoder((State.of _).andThen(_.get))
}
