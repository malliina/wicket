package com.mle.wicket

import bootstrap.BootstrapThemes
import de.agilecoders.wicket.settings.BootstrapSettings
import de.agilecoders.wicket.Bootstrap
import markup.Pages._
import org.apache.wicket.Page
import org.apache.wicket.markup.html.WebPage
import com.mle.util.Log
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType
import org.apache.wicket.protocol.http.WebApplication

/**
 * @author Mle
 */
trait Bootstrapping extends WebApplication with Log {
  var themeService: BootstrapThemes = null
  private val defaultTabs = buildTabs(
    ("Sorting", classOf[SortPage], None),
    ("Bootstrap", classOf[SettingsPage], Some(IconType.cog)),
    ("MOTD", classOf[MessagePage], None),
    ("Account", classOf[AccountPage], Some(IconType.user)),
    ("Users", classOf[UsersPage], Some(IconType.user)),
    ("Unix Users", classOf[LdapUsersPage], Some(IconType.user)),
    ("Groups", classOf[GroupsPage], Some(IconType.user)),
    ("Hosts", classOf[HostsPage], Some(IconType.user))
  )

  def tabs: Seq[BootTab[_ <: WebPage]] = defaultTabs

  override def init() {
    super.init()
    val settings = new BootstrapSettings()
    //    log info "Using responsive CSS: " + settings.useResponsiveCss()
    settings minify true // use minimized version of all bootstrap references
    Bootstrap.install(this, settings)
    themeService = new BootstrapThemes(settings)
  }

  def buildTabs(tabs: (String, Class[_ <: WebPage], Option[IconType])*) = tabs.map(pair => {
    val (title, clazz, icon) = pair
    BootTab(title, clazz, icon)
  })
}

case class BootTab[T <: Page](title: String, pageClass: Class[T], icon: Option[IconType] = None)
