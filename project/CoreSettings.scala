import sbt.Keys.{libraryDependencies, name}
import sbt._

object CoreSettings {
  object Version {
    lazy val scalaGuiceVersion = BaseSettings.Version.scalaGuiceVersion
    lazy val logbackVersion = BaseSettings.Version.logbackVersion
    lazy val scalaLoggingVersion = BaseSettings.Version.scalaLoggingVersion
  }

  object Dependencies {
    lazy val dependencies = Seq(
      "net.codingwell"             %% "scala-guice"     % Version.scalaGuiceVersion,
      "ch.qos.logback"              % "logback-classic" % Version.logbackVersion,
      "com.typesafe.scala-logging" %% "scala-logging"   % Version.scalaLoggingVersion
    )
  }

  lazy val settings =
    BaseSettings.settings ++ Seq(
      name := "todo-list-core",
      libraryDependencies ++= Dependencies.dependencies
    )
}
