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
  "com.h2database" % "h2" % "1.4.200",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
)