import sbt.Keys.{libraryDependencies, name}
import sbt.{Def, _}

object DataSettings {
  object Version {
    lazy val h2Version         = "1.4.200"
    lazy val postgtrsqlVersion = "42.2.18"
    lazy val playSlickVersion  = "5.0.0"
  }

  object Dependencies {
    lazy val dependencies = Seq(
      "net.codingwell"             %% "scala-guice"           % BaseSettings.Version.scalaGuiceVersion,
      "ch.qos.logback"              % "logback-classic"       % BaseSettings.Version.logbackVersion,
      "com.typesafe.scala-logging" %% "scala-logging"         % BaseSettings.Version.scalaLoggingVersion,
      "com.h2database"              % "h2"                    % Version.h2Version,
      "org.postgresql"              % "postgresql"            % Version.postgtrsqlVersion,
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
