package com.ka2kama.application.json

import com.ka2kama.core.{EntityId, TodoId}
import io.circe.syntax._
import io.circe.{Decoder, Encoder}

trait JsonEncoder[A] {
  val self: A
  implicit val encoder: Encoder[A]

  def asJson: String = self.asJson.noSpaces
}