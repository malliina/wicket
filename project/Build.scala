import com.mle.sbt.unix.{UnixZipPackaging, LinuxPackaging}
import com.mle.sbt.win.WindowsPlugin
import com.github.siasia.WebPlugin.webSettings
import com.github.siasia.PluginKeys._
import com.typesafe.packager.{windows, PackagerPlugin}
import sbt.Keys._
import sbt._
import cloudbees.Plugin.{CloudBees, cloudBeesSettings}
import com.mle.util.{Util => MyUtil}
import Dependencies._

/**
 * @author Mle
 */

object GitBuild extends Build {
  val credentialPath = Path.userHome / ".sbt" / "credentials.txt"
  val credentialSettings =
    if (credentialPath.exists())
      Seq(credentials += Credentials(credentialPath))
    else Seq.empty
  val commonSettings = Defaults.defaultSettings ++ Seq(
    organization := "com.mle",
    version := "0.68-SNAPSHOT",
    scalaVersion := "2.10.0",
    retrieveManaged := false,
    publishTo := Some(Resolver.url("my-sbt-releases", new URL("http://xxx/artifactory/my-sbt-releases/"))(Resolver.ivyStylePatterns)),
    publishMavenStyle := false,
    // system properties seem to have no effect in tests,
    // causing e.g. tests requiring javax.net.ssl.keyStore props to fail
    // ... unless fork is true
    sbt.Keys.fork in Test := true,
    // the jars of modules depended on are not included unless this is true
    exportJars := true
  ) ++ credentialSettings

  val beesConfig = MyUtil.optionally(
    MyUtil.props((Path.userHome / ".bees" / "bees.config").toString)
  ).getOrElse(Map.empty)

  def beesSettings = Seq(
    CloudBees.apiKey := beesConfig get "bees.api.key",
    CloudBees.apiSecret := beesConfig get "bees.api.secret",
    CloudBees.username := beesConfig get "bees.project.app.domain"
  )
  lazy val wicketSettings: Seq[Setting[_]] = PackagerPlugin.packagerSettings ++
    WindowsPlugin.windowsSettings ++
    LinuxPackaging.rpmSettings ++
    LinuxPackaging.debianSettings ++
    UnixZipPackaging.unixZipSettings
  val myWebSettings = webSettings ++ Seq(
    webappResources in Compile <+= (sourceDirectory in Runtime)(sd => sd / "resources" / "publicweb")
  )
  lazy val wicket = Project("wicket", file("."), settings = commonSettings)
    .settings(wicketSettings: _*)
    .settings(
    CloudBees.applicationId := Some("wicket"),
    CloudBees.apiKey := beesConfig get "bees.api.key",
    CloudBees.apiSecret := beesConfig get "bees.api.secret",
    CloudBees.username := beesConfig get "bees.project.app.domain")
    .settings(
    libraryDependencies ++= wiQuery ++ Seq(warDep, util, utilActor, utilRmi, utilAuth, utilJdbc, utilWeb, scalaTest),
    webappResources in Compile <+= (sourceDirectory in Runtime)(sd => sd / "resources" / "publicweb"),
    mainClass := Some("com.mle.wicket.WicketStart"))
    .settings(myWebSettings: _*)
}