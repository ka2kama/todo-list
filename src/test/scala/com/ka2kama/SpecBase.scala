package com.ka2kama

import org.scalactic.TripleEquals
import org.scalatest.TryValues._
import org.scalatest.{FreeSpec, Matchers}

import scala.util.Try

trait SpecBase extends FreeSpec with Matchers with TripleEquals {
}

object SpecBase {
  implicit class EitherOps[+A, +B](val self: Either[A, B]) {
    def toTryValue(implicit ev: A <:< Throwable): B =
      self.toTry.success.value
  }

  implicit class TryOps[+A](val self: Try[A]) {
    def successVal: A = self.success.value
  }
}
