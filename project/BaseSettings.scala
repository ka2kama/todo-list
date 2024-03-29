import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtConfig
import sbt.Keys._
import sbt.{Def, _}

object BaseSettings {
  object Version {
    lazy val scalaVersion      = "2.13.5"
    lazy val scalaTestVersion  = "3.2.12"
    lazy val catsVersion       = "2.7.0"
    lazy val monixVersion      = "3.4.1"
    lazy val enumeratumVersion = "1.7.0"

    lazy val scalaGuiceVersion   = "5.1.0"
    lazy val logbackVersion      = "1.2.11"
    lazy val scalaLoggingVersion = "3.9.5"
  }

  object Dependencies {
    lazy val dependencies: Seq[ModuleID] = Seq(
      "org.scalactic" %% "scalactic"  % Version.scalaTestVersion,
      "org.scalatest" %% "scalatest"  % Version.scalaTestVersion % "test",
      "org.typelevel" %% "cats-core"  % Version.catsVersion,
      "io.monix"      %% "monix"      % Version.monixVersion,
      "com.beachape"  %% "enumeratum" % Version.enumeratumVersion,
    )
  }

  lazy val settings: Seq[Def.Setting[_]] = Def.settings(
    organization := "com.ka2kama",
    version := "1.0-SNAPSHOT",
    scalaVersion := Version.scalaVersion,
    scalacOptions ++= CompileOptions.compileOptions,
    scalafmtConfig := file(".scalafmt.conf"),
    resolvers += Resolver.sonatypeRepo("releases"),
    libraryDependencies ++= Dependencies.dependencies,
  )
}
