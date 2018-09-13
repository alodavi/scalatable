scalaVersion := "2.13.0-M4"

name := "scalatable"
version := "0.1"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.13.0-M4",
  "org.specs2" %% "specs2-core" % "4.3.4" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

enablePlugins(TutPlugin)

tutTargetDirectory := baseDirectory.value
