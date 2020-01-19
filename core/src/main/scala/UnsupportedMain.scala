object UnsupportedMain {
  def main(args: Array[String]): Unit = {
    throw new UnsupportedOperationException(
      """
        | project `core` cannot be executed in a stand-alone.
        | type "sbt core/test" for testing or "sbt web/run" for running.
        |""".stripMargin
    )
  }
}
