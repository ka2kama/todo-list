import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtConfig
import sbt.Keys._
import sbt._

object BaseSettings {
  object Version {
    lazy val scalaVersion      = "2.13.4"
    lazy val scalaTestVersion  = "3.2.2"
    lazy val catsVersion       = "2.3.1"
    lazy val monixVersion      = "3.3.0"
    lazy val enumeratumVersion = "1.6.1"

    lazy val scalaGuiceVersion   = "4.2.6"
    lazy val logbackVersion      = "1.2.3"
    lazy val scalaLoggingVersion = "3.9.2"
  }

  object Dependencies {
    lazy val dependencies = Seq(
      "org.scalactic" %% "scalactic"  % Version.scalaTestVersion,
      "org.scalatest" %% "scalatest"  % Version.scalaTestVersion % "test",
      "org.typelevel" %% "cats-core"  % Version.catsVersion,
      "io.monix"      %% "monix"      % Version.monixVersion,
      "com.beachape"  %% "enumeratum" % Version.enumeratumVersion,
    )
  }

  lazy val settings = Seq(
    organization := "com.ka2kama",
    version := "1.0-SNAPSHOT",
    scalaVersion := Version.scalaVersion,
    scalacOptions ++= CompileOptions.compileOptions,
    scalafmtConfig := file(".scalafmt.conf"),
    resolvers += Resolver.sonatypeRepo("releases"),
    libraryDependencies ++= Dependencies.dependencies,
  )
}
