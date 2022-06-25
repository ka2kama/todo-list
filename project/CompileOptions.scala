object CompileOptions {
  lazy val compileOptions: Seq[String] = Seq(
    "-encoding",
    "UTF-8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Wconf:cat=lint-byname-implicit:s",
  )
}
