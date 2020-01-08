package com.ka2kama.application.json

import scala.util.Try

trait JsonDecoder[A] {
  def decode(json: String): Try[A]
}
