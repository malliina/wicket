import sbt._

/**
 * @author Mle
 */

object Dependencies {
  val wiqueryVersion = "6.2.0"
  val utilVersion = "0.68-SNAPSHOT"
  val util = "com.mle" %% "util" % utilVersion
  val utilActor = "com.mle" %% "util-actor" % utilVersion
  val utilRmi = "com.mle" %% "util-rmi" % utilVersion
  val utilAuth = "com.mle" %% "util-auth" % utilVersion
  val utilJdbc = "com.mle" %% "util-jdbc" % utilVersion
  val utilWeb = "com.mle" %% "util-web" % "0.66-SNAPSHOT"
  val warDep = "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
  val wiQueryCore = "org.odlabs.wiquery" % "wiquery-core" % wiqueryVersion
  val wiQueryUi = "org.odlabs.wiquery" % "wiquery-jquery-ui" % wiqueryVersion
  val wiQuery = Seq(wiQueryCore, wiQueryUi)
  val scalaTest = "org.scalatest" %% "scalatest" % "1.9.1" % "test"
}