import play.sbt.PlayImport.{evolutions, guice}
import play.sbt.routes.RoutesKeys
import sbt.Keys.{libraryDependencies, name}
import sbt.{Def, _}

object WebSettings {
  object Version {
    lazy val scalaTestPlusPlayVersion = "5.1.0"
    lazy val circeVersion             = "0.14.2"
    lazy val playCirceVersion         = "2814.2"
  }

  object Dependencies {
    lazy val dependencies: Seq[ModuleID] = Seq(
      guice,
      evolutions,
      "org.scalatestplus.play" %% "scalatestplus-play" % Version.scalaTestPlusPlayVersion % Test,
      "io.circe"               %% "circe-generic"      % Version.circeVersion,
      "com.dripower"           %% "play-circe"         % Version.playCirceVersion,
    )
  }

  lazy val settings: Seq[Def.Setting[_]] =
    BaseSettings.settings ++ Seq(
      name := "todo-list-web",
      RoutesKeys.routesImport := Seq.empty,
      libraryDependencies ++= Dependencies.dependencies,
    )
}
