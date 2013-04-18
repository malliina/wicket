import sbt._

/**
 * @author Mle
 */

object Dependencies {
  val wiqueryVersion = "6.2.0"
  val utilGroup = "com.github.malliina"
  val utilVersion = "0.69-SNAPSHOT"
  val util = utilGroup %% "util" % utilVersion
  val utilActor = utilGroup %% "util-actor" % utilVersion
  val utilRmi = utilGroup %% "util-rmi" % utilVersion
  val utilAuth = utilGroup %% "util-auth" % utilVersion
  val utilJdbc = utilGroup %% "util-jdbc" % utilVersion
  val utilWeb = utilGroup %% "util-web" % utilVersion
  val warDep = "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
  val wiQueryCore = "org.odlabs.wiquery" % "wiquery-core" % wiqueryVersion
  val wiQueryUi = "org.odlabs.wiquery" % "wiquery-jquery-ui" % wiqueryVersion
  val wiQuery = Seq(wiQueryCore, wiQueryUi)
  val scalaTest = "org.scalatest" %% "scalatest" % "1.9.1" % "test"
}