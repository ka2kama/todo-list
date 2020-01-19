val scalaTestVersion = "3.1.0"
val circeVersion = "0.12.3"
val scalikeJDBCVersion = "3.4.0"

val baseSettings = Seq(
  organization := "com.ka2kama",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-unused",
    "-Ywarn-value-discard",
    "-Xfatal-warnings"
  ),
  resolvers += Resolver.sonatypeRepo("releases"),
)

val baseDependencies = Seq(
  libraryDependencies ++= Seq(
    "net.codingwell" %% "scala-guice" % "4.2.6",
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
  )
)

lazy val root = (project in file("."))
  .settings(baseSettings ++ Seq(
    name := "todo-list",
  ))
  .aggregate(web, core, util)

lazy val web = (project in file("web"))
  .enablePlugins(PlayWeb)
  .disablePlugins(PlayLayoutPlugin)
  .settings(baseSettings ++ baseDependencies ++ Seq(
    name := "todo-list-web",
    play.sbt.routes.RoutesKeys.routesImport := Seq.empty,
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      evolutions,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "com.dripower" %% "play-circe" % "2812.0",
      "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.4",
    ))
  )
  .dependsOn(
    core % "test->test;compile->compile",
    util % "test->test;compile->compile"
  )

lazy val core = (project in file("core"))
  .settings(baseSettings ++ baseDependencies ++ Seq(
    name := "todo-list-core",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
      "com.h2database" % "h2" % "1.4.200",
      "org.postgresql" % "postgresql" % "42.2.9",
      "org.playframework.anorm" %% "anorm" % "2.6.5",
      "com.typesafe.play" %% "play-slick" % "5.0.0",
    //  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "org.scalikejdbc" %% "scalikejdbc" % scalikeJDBCVersion,
      "org.scalikejdbc" %% "scalikejdbc-config" % scalikeJDBCVersion,
      "org.scalikejdbc" %% "scalikejdbc-test" % scalikeJDBCVersion % "test",
      "org.reactivemongo" %% "play2-reactivemongo" % "0.20.1-play28",
      "org.reactivemongo" %% "reactivemongo-play-json-compat" % "0.20.1-play28",
    )
  ))
  .dependsOn(util % "test->test;compile->compile")

lazy val util = (project in file("util"))
  .settings(baseSettings ++ Seq(name := "todo-list-util"))