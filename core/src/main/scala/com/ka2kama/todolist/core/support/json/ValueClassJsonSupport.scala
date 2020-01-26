package com.ka2kama.todolist.core.support.json

import io.circe.{Decoder, Encoder}

trait ValueClassJsonSupport extends ValueClassJsonEncoder with ValueClassJsonDecoder

trait ValueClassJsonEncoder {
  // convert Encoder[UnderlyingValue] to Encoder[WrapperClass ]
  def encoder[W, U](unwrap: W => U)(implicit enc: Encoder[U]): Encoder[W] =
    Encoder[U].contramap(unwrap)
}

trait ValueClassJsonDecoder {
  // decode json as UnderlyingValue, then map to WrapperClass
  def decoder[W, U](wrap: U => W)(implicit dec: Decoder[U]): Decoder[W] =
    Decoder[U].map(wrap)
}
