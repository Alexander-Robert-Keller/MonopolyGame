name := "MonopolyGame"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/release"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"

// guice dependecys

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.0"
