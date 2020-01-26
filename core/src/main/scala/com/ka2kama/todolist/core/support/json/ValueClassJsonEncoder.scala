package com.ka2kama.todolist.core.support.json

import io.circe.Encoder

trait ValueClassJsonEncoder {
  // convert Encoder[UnderlyingValue] to Encoder[WrapperClass ]
  def encoder[W, U](unwrap: W => U)(implicit enc: Encoder[U]): Encoder[W] =
    Encoder[U].contramap(unwrap)
}
