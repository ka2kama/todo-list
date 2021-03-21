import sbt.Def
import sbt.Keys.name

object CommonSettings {
  lazy val settings: Seq[Def.Setting[_]] =
    BaseSettings.settings ++ Seq(
      name := "todo-list-common"
    )
}
