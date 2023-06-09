// author: Afshin Sabahi

val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "shopping_basket",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M7" % Test
  )
