import sbt.Keys._
import sbt._

/**
 * Build definition of the build definition. I don't like build.sbt/plugins.sbt files.
 *
 * This replacement gives better IDE support and a more consistent approach to configuration.
 *
 * @author Mle
 */
object BuildBuild extends Build {

  // "build.sbt" goes here
  override lazy val settings = super.settings ++ Seq(
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % ("0.12.0-0.2.11.1")),
    addSbtPlugin("eu.getintheloop" %% "sbt-cloudbees-plugin" % "0.4.1"),
    addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.3.0-SNAPSHOT"),
    addSbtPlugin("com.mle" % "sbt-packager" % "0.6-SNAPSHOT"),
    addSbtPlugin("com.mle" %% "util" % "0.67-SNAPSHOT")
  )
  lazy val root = Project("plugins", file("."))
}