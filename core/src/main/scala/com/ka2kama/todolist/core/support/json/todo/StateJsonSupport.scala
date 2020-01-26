package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.{SingleValueJsonDecoder, SingleValueJsonEncoder}
import com.ka2kama.todolist.core.todo.domain.model.State
import io.circe.{Decoder, Encoder}

trait StateJsonSupport extends StateJsonEncoder with StateJsonDecoder

trait StateJsonEncoder extends SingleValueJsonEncoder {
  implicit val stateEncoder: Encoder[State] = encoder(_.value)
}

trait StateJsonDecoder extends SingleValueJsonDecoder {
  implicit val stateDecoder: Decoder[State] = decoder((State.of _).andThen(_.get))
}
