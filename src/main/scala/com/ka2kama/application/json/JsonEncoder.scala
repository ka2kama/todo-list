package com.ka2kama.application.json

import io.circe.Encoder
import io.circe.syntax._

trait JsonEncoder[A] {
  implicit val encoder: Encoder[A]
}

trait JsonEncoderExt[A] {
  def self : A
  implicit val encoder: Encoder[A]
  def asNoSpaceJson: String = self.asJson.noSpaces
}