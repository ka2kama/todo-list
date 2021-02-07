import play.sbt.routes.RoutesKeys

val scalaTestVersion  = "3.2.2"
val catsVersion       = "2.3.1"
val scalaGuiceVersion = "4.2.6"
val monixVersion      = "3.3.0"
val enumeratumVersion = "1.6.1"

val baseSettings = Seq(
  organization := "com.ka2kama",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.13.4",
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
        //guice,
        //jdbc,
        evolutions,
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
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
        "net.codingwell"             %% "scala-guice"           % scalaGuiceVersion,
        "ch.qos.logback"              % "logback-classic"       % "1.2.3",
        "com.typesafe.scala-logging" %% "scala-logging"         % "3.9.2",
        "com.h2database"              % "h2"                    % "1.4.200",
        "org.postgresql"              % "postgresql"            % "42.2.9",
        "com.typesafe.play"          %% "play-slick"            % "5.0.0",
        "com.typesafe.play"          %% "play-slick-evolutions" % "5.0.0",
      ),
    )
  )
  .dependsOn(common % "test->test;compile->compile")

lazy val common = project
  .settings(
    baseSettings ++ baseDependencies ++ Seq(
      name := "todo-list-common",
      libraryDependencies ++= Seq(
        "com.beachape" %% "enumeratum" % enumeratumVersion
      ),
    )
  )
