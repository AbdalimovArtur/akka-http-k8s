name := "akka-http-k8s"

version := "1.0.0"

scalaVersion := "2.12.8"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin)

val Version = new {
  val akka      = "2.5.23"
  val slick     = "3.3.1"
  val json4s    = "3.5.3"
  val scalactic = "3.0.8"
}

libraryDependencies ++= Seq(
  "com.typesafe.akka"          %% "akka-stream"                    % Version.akka,
  "com.typesafe.akka"          %% "akka-actor"                     % Version.akka,
  "com.typesafe.akka"          %% "akka-slf4j"                     % Version.akka,
  "com.typesafe.akka"          %% "akka-kz.sofier.api.http"        % "10.1.8",
  "com.typesafe.scala-logging" %% "scala-logging"                  % "3.9.2",
  "com.typesafe.slick"         %% "slick"                          % Version.slick,
  "com.typesafe.slick"         %% "slick-hikaricp"                 % Version.slick,
  "com.zaxxer"                 % "HikariCP"                        % Version.slick,
  "de.heikoseeberger"          %% "akka-kz.sofier.api.http-json4s" % "1.20.1",
  "org.json4s"                 %% "json4s-native"                  % Version.json4s,
  "org.json4s"                 %% "json4s-jackson"                 % Version.json4s,
  "org.postgresql"             % "postgresql"                      % "42.2.5",
  "org.scalactic"              %% "scalactic"                      % Version.scalactic,
  "org.scalatest"              %% "scalatest"                      % Version.scalactic % "test",
  "joda-time"                  % "joda-time"                       % "2.10.3",
  "ch.qos.logback"             % "logback-classic"                 % "1.2.3"
)

maintainer in Docker := "a.abdalimov.97@gmail.com"
dockerBaseImage := "openjdk:8"
dockerRepository := Some("localhost:5000")
dockerExposedPorts := Seq(8080)
daemonUser in Docker := "root"
