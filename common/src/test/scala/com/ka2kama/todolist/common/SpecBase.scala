package com.ka2kama.todolist.common

import org.scalactic.TripleEquals
import org.scalatest.TryValues._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should

import scala.util.Try

trait SpecBase extends AnyFreeSpec with should.Matchers with TripleEquals

object SpecBase {
  implicit class EitherOps[A, B](val self: Either[A, B]) extends AnyVal {
    def successVal(implicit ev: A <:< Throwable): B =
      self.toTry.successVal
  }

  implicit class TryOps[A](val self: Try[A]) extends AnyVal {
    def successVal: A = self.success.value
  }
}
