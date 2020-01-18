name := """todo-list"""
organization := "com.ka2kama"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayWeb)
  .disablePlugins(PlayLayoutPlugin)

scalaVersion := "2.13.1"

val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  guice,
  evolutions,
  jdbc,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "com.h2database" % "h2" % "1.4.200",
  "org.postgresql" % "postgresql" % "42.2.9",
  "org.playframework.anorm" %% "anorm" % "2.6.5",
//  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
//  "io.circe" %% "circe-parser" % circeVersion,
  "com.dripower" %% "play-circe" % "2812.0"
)
