package com.ka2kama.application.json

trait JsonEncoder[A] {
  def encode(obj: A): String
}

abstract class JsonEncoderExt[A: JsonEncoder] {
  def self: A
  def asJson: String =
    implicitly[JsonEncoder[A]].encode(self)
}
