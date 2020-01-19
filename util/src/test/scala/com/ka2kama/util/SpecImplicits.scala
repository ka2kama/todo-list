package com.ka2kama.util

import org.scalatest.TryValues._
import scala.util.Try

object SpecImplicits {
  implicit class EitherOps[A, B](val self: Either[A, B]) extends AnyVal {
    def successVal(implicit ev: A <:< Throwable): B =
      self.toTry.successVal
  }

  implicit class TryOps[A](val self: Try[A]) extends AnyVal {
    def successVal: A = self.success.value
  }
}
