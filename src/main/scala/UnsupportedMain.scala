object UnsupportedMain {
  def main(args: Array[String]): Unit = {
    throw new UnsupportedOperationException("""
        | project `root` cannot be executed.
        | type "sbt web/run" for running.
        |""".stripMargin)
  }
}
