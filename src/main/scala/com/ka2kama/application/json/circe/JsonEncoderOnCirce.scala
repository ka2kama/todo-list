package com.ka2kama.application.json.circe

import com.ka2kama.application.json.JsonEncoder
import io.circe.Encoder
import io.circe.syntax._

abstract class JsonEncoderOnCirce[A](implicit val encoder: Encoder[A])
    extends JsonEncoder[A] {
  def encode(obj: A): String = to2SpacesJson(obj)
  def to2SpacesJson(obj: A): String = obj.asJson.spaces2
}

object JsonEncoderOnCirce {
  def apply[A](implicit encoder: Encoder[A]): JsonEncoderOnCirce[A] =
    new JsonEncoderOnCirce {}
}
