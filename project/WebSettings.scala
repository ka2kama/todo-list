import play.sbt.PlayImport.evolutions
import play.sbt.routes.RoutesKeys
import sbt.Keys.{libraryDependencies, name}
import sbt._

object WebSettings {
  object Version {
    val scalaTestPlusPlayVersion = "5.1.0"
  }

  object Dependencies {
    val dependencies = Seq(
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
