name := "cluster-node-1"

version := "1.0"

logLevel := Level.Info

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalaVersion := "2.10.4"

libraryDependencies ++= {
  val akkaVersion = "2.3.6"
  Seq(
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
    "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
    "com.typesafe.akka" %% "akka-kernel" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
    "org.slf4j" % "slf4j-api" % "1.7.2",
    "ch.qos.logback" % "logback-classic" % "1.0.7"
  )
}

mainClass in Compile := Some("behrad.cn1.Run")