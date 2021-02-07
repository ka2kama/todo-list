lazy val root = (project in file("."))
  .settings(
    BaseSettings.settings ++ Seq(
      name := "todo-list"
    )
  )
  .aggregate(web, core, common)

lazy val common = project.settings(CommonSettings.settings: _*)

lazy val data = project
  .settings(DataSettings.settings: _*)
  .dependsOn(common % "test->test;compile->compile")

lazy val core = project
  .settings(CoreSettings.settings: _*)
  .dependsOn(
    common % "test->test;compile->compile",
    data   % "test->test;compile->compile",
  )

lazy val web = project
  .enablePlugins(PlayWeb)
  .disablePlugins(PlayLayoutPlugin)
  .settings(WebSettings.settings: _*)
  .dependsOn(
    core   % "test->test;compile->compile",
    common % "test->test;compile->compile",
  )
