package com.ka2kama.util

import scala.util.Try

object Implicits {
  implicit class OptionOps[A](val self: Option[A]) extends AnyVal {
    def toTry: Try[A] = Try(self.get)
    def toTry(throwable: Throwable): Try[A] =
      Try(self.getOrElse(throw throwable))
  }
}
