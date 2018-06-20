
name := "akka-http-k8s"

version := "0.1"

scalaVersion := "2.12.6"


lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.3" % Test,
  "com.typesafe.akka" %% "akka-actor" % "2.5.13",
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",

  "org.slf4j" % "slf4j-simple" % "1.7.25" % Test
)

maintainer in Docker := "a.abdalimov.97@gmail.com"
dockerBaseImage := "openjdk:8"
dockerRepository := Some("localhost:5000")
dockerExposedPorts:= Seq(8080)
daemonUser in Docker := "root"