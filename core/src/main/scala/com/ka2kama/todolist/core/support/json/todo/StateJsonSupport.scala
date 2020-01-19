package com.ka2kama.todolist.core.support.json.todo

import com.ka2kama.todolist.core.support.json.ValueClassJsonSupport
import com.ka2kama.todolist.core.todo.domain.model.State
import io.circe.{Decoder, Encoder}

trait StateJsonSupport extends ValueClassJsonSupport {
  implicit val stateEncoder: Encoder[State] = encoder(_.value)
  implicit val stateDecoder: Decoder[State] = decoder(
    (State.of _).andThen(_.get)
  )
}
