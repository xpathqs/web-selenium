package org.xpathqs.web.selenium.executor

import org.openqa.selenium.WebDriver
import org.xpathqs.core.selector.base.BaseSelector
import org.xpathqs.driver.executor.Decorator
import org.xpathqs.web.selenium.constants.Global

open class SeleniumCachedExecutor(
    webDriver: WebDriver,
    origin: Decorator
) : SeleniumBaseExecutor(webDriver, origin) {
    override fun getAttr(selector: BaseSelector, attr: String): String {
        try {
            val res = super.getAttr(selector, attr)
            return res
        } catch (e: Exception) {

        }

        if(attr == "value") {
            val elem = selector.webElement
            return if (attr == Global.TEXT_ARG) elem.text else elem.getAttribute(attr)
        }

        throw Exception("No '$attr' attribute for the $selector")
    }
}