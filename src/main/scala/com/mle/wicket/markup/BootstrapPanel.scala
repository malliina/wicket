package com.mle.wicket.markup

import ch.qos.logback.classic.Level
import collection.JavaConversions._
import com.mle.util.{Log, AppUtils}
import com.mle.wicket.behavior.OnChangeAjaxFormBehavior
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.Model
import org.apache.wicket.markup.html.basic.Label
import de.agilecoders.wicket.markup.html.bootstrap.components.{TooltipConfig, TooltipBehavior}
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.head.IHeaderResponse
import com.mle.wicket.component.bootstrap.ThemeDropDown
import com.mle.web.wicket.model.RWModel

/**
 * @author Mle
 */

class BootstrapPanel(id: String) extends Panel(id) with Log {
  val levels = Seq(Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.OFF)
  val levelDropDown = new DropDownChoice("logLevel", RWModel[Level](AppUtils.getLogLevel, AppUtils.setLogLevel(_)), levels)
  levelDropDown add new OnChangeAjaxFormBehavior()
  add(levelDropDown)
  val tippedLabel = new Label("testLabel", Model.of("I am tooltipped"))
  add(tippedLabel)
  val toolConf = new TooltipConfig
  toolConf.withAnimation(true).withPlacement(TooltipConfig.Placement.bottom)
  val toolTip = new TooltipBehavior(Model.of("Hello,<br> world<br>"), toolConf)
  tippedLabel add toolTip
  val toolWmc = new WebMarkupContainer("toolWmc")
  add(toolWmc)
  toolWmc add toolTip
  val themeChoice = new ThemeDropDown("themes")
  add(themeChoice)

  override def renderHead(response: IHeaderResponse) {
    super.renderHead(response)
    //    response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(classOf[Settings], "random.js")))
  }
}