import sbt.Keys.name

object CommonSettings {
  lazy val settings =
    BaseSettings.settings ++ Seq(
      name := "todo-list-common"
    )
}
