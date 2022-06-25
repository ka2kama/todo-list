import sbt.Keys.{libraryDependencies, name}
import sbt.{Def, _}

object DataSettings {
  object Version {
    lazy val h2Version         = "2.1.212"
    lazy val postgresqlVersion = "42.3.6"
    lazy val playSlickVersion  = "5.0.2"
  }

  object Dependencies {
    lazy val dependencies: Seq[ModuleID] = Seq(
      "net.codingwell"             %% "scala-guice"           % BaseSettings.Version.scalaGuiceVersion,
      "ch.qos.logback"              % "logback-classic"       % BaseSettings.Version.logbackVersion,
      "com.typesafe.scala-logging" %% "scala-logging"         % BaseSettings.Version.scalaLoggingVersion,
      "com.h2database"              % "h2"                    % Version.h2Version,
      "org.postgresql"              % "postgresql"            % Version.postgresqlVersion,
      "com.typesafe.play"          %% "play-slick"            % Version.playSlickVersion,
      "com.typesafe.play"          %% "play-slick-evolutions" % Version.playSlickVersion,
    )
  }

  lazy val settings: Seq[Def.Setting[_]] =
    BaseSettings.settings ++ Seq(
      name := "todo-list-data",
      libraryDependencies ++= Dependencies.dependencies,
    )
}
