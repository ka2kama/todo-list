import play.sbt.routes.RoutesKeys
RoutesKeys.routesImport := Seq.empty

resolvers += Resolver.sonatypeRepo("releases")

name := """todo-list"""
organization := "com.ka2kama"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayWeb)
  .disablePlugins(PlayLayoutPlugin)

val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "com.h2database" % "h2" % "1.4.200",
  "org.postgresql" % "postgresql" % "42.2.9",
  "org.playframework.anorm" %% "anorm" % "2.6.5",
  "com.typesafe.play" %% "play-slick" % "5.0.0",
//  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "org.scalikejdbc" %% "scalikejdbc" % "3.4.0",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.4.0",
  "org.scalikejdbc" %% "scalikejdbc-test" % "3.4.0" % "test",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.4",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.1-play28",
  "org.reactivemongo" %% "reactivemongo-play-json-compat" % "0.20.1-play28",
//  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
//  "io.circe" %% "circe-parser" % circeVersion,
  "com.dripower" %% "play-circe" % "2812.0"
)
