import play.sbt.routes.RoutesKeys

val scalaTestVersion   = "3.1.1"
val catsVersion        = "2.1.1"
val circeVersion       = "0.13.0"
val scalikeJDBCVersion = "3.4.0"
val scalaGuiceVersion  = "4.2.6"
val monixVersion       = "3.2.2"

val baseSettings = Seq(
  organization := "com.ka2kama",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.13.2",
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfatal-warnings",
  ),
  scalacOptions --= Seq("-Xlint:byname-implicit"),
  scalafmtConfig := file(".scalafmt.conf"),
  resolvers += Resolver.sonatypeRepo("releases"),
)

val baseDependencies = Seq(
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "org.typelevel" %% "cats-core" % catsVersion,
    "io.monix"      %% "monix"     % monixVersion,
  )
)

lazy val root = (project in file("."))
  .settings(
    baseSettings ++ Seq(
      name := "todo-list"
    )
  )
  .aggregate(web, core, common)

lazy val web = project
  .enablePlugins(PlayWeb)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    baseSettings ++ baseDependencies ++ Seq(
      name := "todo-list-web",
      RoutesKeys.routesImport := Seq.empty,
      libraryDependencies ++= Seq(
        guice,
        //jdbc,
        //evolutions,
        "org.scalatestplus.play" %% "scalatestplus-play"           % "5.0.0" % Test,
        "com.dripower"           %% "play-circe"                   % "2812.0",
        "io.circe"               %% "circe-generic"                % circeVersion,
        "org.scalikejdbc"        %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.4",
        //  "com.typesafe.akka"      %% "akka-stream"                  % "2.6.3"
      ),
    )
  )
  .dependsOn(
    core   % "test->test;compile->compile",
    common % "test->test;compile->compile",
  )

lazy val core = project
  .settings(
    baseSettings ++ baseDependencies ++ Seq(
      name := "todo-list-core",
      libraryDependencies ++= Seq(
        "net.codingwell"             %% "scala-guice"     % scalaGuiceVersion,
        "io.circe"                   %% "circe-core"      % circeVersion,
        "io.circe"                   %% "circe-generic"   % circeVersion,
        "io.circe"                   %% "circe-parser"    % circeVersion,
        "ch.qos.logback"              % "logback-classic" % "1.2.3",
        "com.typesafe.scala-logging" %% "scala-logging"   % "3.9.2",
      ),
    )
  )
  .dependsOn(
    common % "test->test;compile->compile",
    data   % "test->test;compile->compile",
  )

lazy val data = project
  .settings(
    baseSettings ++ baseDependencies ++ Seq(
      name := "todo-list-data",
      libraryDependencies ++= Seq(
        "net.codingwell"             %% "scala-guice"                    % scalaGuiceVersion,
        "ch.qos.logback"              % "logback-classic"                % "1.2.3",
        "com.typesafe.scala-logging" %% "scala-logging"                  % "3.9.2",
        "com.h2database"              % "h2"                             % "1.4.200",
        "org.postgresql"              % "postgresql"                     % "42.2.9",
        "org.playframework.anorm"    %% "anorm"                          % "2.6.5",
        "com.typesafe.play"          %% "play-slick"                     % "5.0.0",
        "com.typesafe.play"          %% "play-slick-evolutions"          % "5.0.0",
        "org.scalikejdbc"            %% "scalikejdbc"                    % scalikeJDBCVersion,
        "org.scalikejdbc"            %% "scalikejdbc-config"             % scalikeJDBCVersion,
        "org.scalikejdbc"            %% "scalikejdbc-test"               % scalikeJDBCVersion % "test",
        "org.reactivemongo"          %% "play2-reactivemongo"            % "0.20.1-play28",
        "org.reactivemongo"          %% "reactivemongo-play-json-compat" % "0.20.1-play28",
      ),
    )
  )
  .dependsOn(common % "test->test;compile->compile")

lazy val common = project
  .settings(
    baseSettings ++ baseDependencies ++ Seq(
      name := "todo-list-common",
      libraryDependencies ++= Seq(),
    )
  )
