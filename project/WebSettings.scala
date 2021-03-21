import play.sbt.PlayImport.{evolutions, guice}
import play.sbt.routes.RoutesKeys
import sbt.Keys.{libraryDependencies, name}
import sbt._

object WebSettings {
  object Version {
    lazy val scalaTestPlusPlayVersion = "5.1.0"
  }

  object Dependencies {
    lazy val dependencies = Seq(
      guice,
      evolutions,
      "org.scalatestplus.play" %% "scalatestplus-play" % Version.scalaTestPlusPlayVersion % Test,
    )
  }

  lazy val settings =
    BaseSettings.settings ++ Seq(
      name := "todo-list-web",
      RoutesKeys.routesImport := Seq.empty,
      libraryDependencies ++= Dependencies.dependencies,
    )
}
