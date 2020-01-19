package com.ka2kama.util

import scala.util.Try

object Implicits {
  implicit class RichOption[A](val op: Option[A]) extends AnyVal {
    def toTry: Try[A] = Try(op.get)
    def toTry(throwable: Throwable): Try[A] =
      Try(op.getOrElse(throw throwable))
  }
}
