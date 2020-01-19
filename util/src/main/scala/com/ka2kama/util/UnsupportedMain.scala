package com.ka2kama.util

object UnsupportedMain {
  def main(args: Array[String]): Unit = {
    throw new UnsupportedOperationException(
      """
        | project `util` cannot be executed in a stand-alone.
        | type "sbt util/test" for testing or "sbt web/run" for running.
        |""".stripMargin
    )
  }
}
