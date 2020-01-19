package com.ka2kama.core.support.json

import com.ka2kama.core.todo.domain.model.State
import io.circe.{Decoder, Encoder}

trait StateJsonSupport {
  implicit val stateEncoder: Encoder[State] =
    Encoder[Int].contramap(_.value)
  implicit val stateDecoder: Decoder[State] =
    Decoder[Int].map(State.of(_).get)
}
