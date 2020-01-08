package com.ka2kama.application.json.circe

import com.ka2kama.application.json.JsonDecoder
import io.circe.parser._
import io.circe.{Decoder, Json}

import scala.util.Try

abstract class JsonDecoderOnCirce[A](implicit val decoder: Decoder[A])
    extends JsonDecoder[A] {

  def decode(json: String): Try[A] = parse(json).toTry.flatMap(decode)

  def decode(json: Json): Try[A] = json.as[A].toTry
}
