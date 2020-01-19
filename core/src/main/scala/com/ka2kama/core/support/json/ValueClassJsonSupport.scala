package com.ka2kama.core.support.json

import io.circe.{Decoder, Encoder}

trait ValueClassJsonSupport {

  // convert Encoder[UnderlyingValue] to Encoder[WrapperClass ]
  def encoder[W, U](unwrap: W => U)(implicit enc: Encoder[U]): Encoder[W] =
    Encoder[U].contramap(unwrap)

  // decode json as UnderlyingValue, then map to WrapperClass
  def decoder[W, U](wrap: U => W)(implicit dec: Decoder[U]): Decoder[W] =
    Decoder[U].map(wrap)
}
