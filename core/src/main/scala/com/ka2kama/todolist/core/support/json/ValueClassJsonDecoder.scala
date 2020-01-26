package com.ka2kama.todolist.core.support.json

import io.circe.Decoder

trait ValueClassJsonDecoder {
  // decode json as UnderlyingValue, then map to WrapperClass
  def decoder[U, W](wrap: U => W)(implicit dec: Decoder[U]): Decoder[W] =
    Decoder[U].map(wrap)
}
