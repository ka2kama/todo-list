package com.ka2kama.application.json.circe

import com.ka2kama.application.json.{JsonConverter, JsonDecoder, JsonEncoder}
import io.circe.parser.parse
import io.circe.syntax._
import io.circe.{Decoder, Encoder, Json}

import scala.util.Try

abstract class JsonEncoderOnCirce[A](implicit val encoder: Encoder[A])
    extends JsonEncoder[A] {
  def encode(obj: A): String = to2SpacesJson(obj)
  def to2SpacesJson(obj: A): String = obj.asJson.spaces2
}

abstract class JsonDecoderOnCirce[A](implicit val decoder: Decoder[A])
    extends JsonDecoder[A] {

  def decode(json: String): Try[A] = parse(json).toTry.flatMap(decode)

  def decode(json: Json): Try[A] = json.as[A].toTry
}

object DefaultCirceEncoder {
  def apply[A](implicit encoder: Encoder[A]): JsonEncoderOnCirce[A] =
    new JsonEncoderOnCirce {}
}

object DefaultCirceDecoder {
  def apply[A](implicit decoder: Decoder[A]): JsonDecoderOnCirce[A] =
    new JsonDecoderOnCirce {}
}

trait JsonConverterOnCirce[A] extends JsonConverter[A] {
  override implicit val encoder: JsonEncoderOnCirce[A]
  override implicit val decoder: JsonDecoderOnCirce[A]
}
