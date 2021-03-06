package com.mle.wicket.markup

import com.mle.web.wicket.markup.PanelPage


/**
 * Page classes for cases where we have [[org.apache.wicket.markup.html.panel.Panel]]s but want [[org.apache.wicket.Page]]s.
 *
 * @author Mle
 */
object Pages {

  class LoginPage extends PanelPage(new LoginPanel(_))

  class WebSocketsPage extends BootstrapPage(new WebSocketTabs(_))

  class AtmospherePage extends BootstrapPage(new Atmosphere(_))

  class SettingsPage extends BootstrapPage(new BootstrapPanel(_))

  class SortPage extends BootstrapPage(new SortPanel(_))

  class MessagePage extends BootstrapPage(new SubTabs(_))

  class AccountPage extends BootstrapPage(new AccountPanel(_))

  class UsersPage extends BootstrapPage(new Users(_))

  class GroupsPage extends BootstrapPage(new Groups(_))

  class HostsPage extends BootstrapPage(new Hosts(_))

  class LdapUsersPage extends BootstrapPage(new LdapUsers(_))


}
