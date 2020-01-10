package com.ka2kama.application.json

import scala.util.Try

trait JsonEncoder[A] {
  def encode(obj: A): String
}

trait JsonDecoder[A] {
  def decode(json: String): Try[A]
}

abstract class JsonEncoderExt[A: JsonEncoder] {
  def self: A
  def asJson: String =
    implicitly[JsonEncoder[A]].encode(self)
}

trait JsonConverter[A] {
  implicit val encoder: JsonEncoder[A]
  implicit val decoder: JsonDecoder[A]
  implicit class EncoderExt(val self: A) extends JsonEncoderExt[A]
}
