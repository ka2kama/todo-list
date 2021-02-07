import sbt.Keys.{libraryDependencies, name}
import sbt._

object CoreSettings {
  object Version {}

  object Dependencies {
    val dependencies = Seq(
      "net.codingwell"             %% "scala-guice"     % BaseSettings.Version.scalaGuiceVersion,
      "ch.qos.logback"              % "logback-classic" % BaseSettings.Version.logbackVersion,
      "com.typesafe.scala-logging" %% "scala-logging"   % BaseSettings.Version.scalaLoggingVersion,
    )
  }

  lazy val settings =
    BaseSettings.settings ++ Seq(
      name := "todo-list-core",
      libraryDependencies ++= Dependencies.dependencies,
    )
}
