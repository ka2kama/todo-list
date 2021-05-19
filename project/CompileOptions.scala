object CompileOptions {
  lazy val compileOptions = Seq(
    "-encoding",
    "UTF-8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    // "-Xfatal-warnings",
    "-Wconf:cat=lint-byname-implicit:s,any:e",
  )
}
