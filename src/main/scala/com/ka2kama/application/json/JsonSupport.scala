package com.ka2kama.application.json

trait JsonSupport[A] {
  implicit val encoder: JsonEncoder[A]
  implicit val decoder: JsonDecoder[A]
  implicit class EncoderExt(val self: A) extends JsonEncoderExt[A]
}
