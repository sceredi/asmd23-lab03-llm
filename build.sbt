val scala3Version = "3.4.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "asmd23-lab03-llm",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "com.github.sbt" % "junit-interface" % "0.13.3" % "test",
    )
  )
