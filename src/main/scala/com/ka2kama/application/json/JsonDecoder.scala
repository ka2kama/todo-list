package com.ka2kama.application.json

import io.circe.{Decoder, Json}

import scala.util.{Failure, Success, Try}

trait JsonDecoder[A] {
  implicit val decoder: Decoder[A]

  def decode(json: Json): Try[A] = {
    json.as[A].fold(Failure(_), Success(_))
  }
}
