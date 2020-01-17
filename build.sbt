name := "MonopolyGame"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/release"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"

// guice dependecies

libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.6"

// json, xml dependencies

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.2"