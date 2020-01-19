package com.ka2kama.core

import org.scalactic.TripleEquals
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should

trait SpecBase extends AnyFreeSpec with should.Matchers with TripleEquals
