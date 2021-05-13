package org.xpathqs.web.selenium.executor

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.xpathqs.core.selector.base.BaseSelector
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.driver.actions.ClickAction
import org.xpathqs.driver.actions.InputAction
import org.xpathqs.driver.cache.ICache
import org.xpathqs.driver.executor.ActionExecMap
import org.xpathqs.driver.executor.BaseExecutor
import org.xpathqs.driver.executor.CacheExecutor
import org.xpathqs.web.driver.IWebDriver
import org.xpathqs.web.executor.WebExecutor
import org.xpathqs.web.selenium.constants.Global
import java.util.concurrent.TimeUnit

open class SeleniumExecutor(
    private val webDriver: WebDriver,
    driver: IWebDriver
): WebExecutor (driver) {

    override fun getAttr(selector: BaseSelector, attr: String): String {
        val elem = selector.webElement
        return if(attr == Global.TEXT_ARG) elem.text else elem.getAttribute(attr)
    }

    override fun getAttrs(selector: BaseSelector, attr: String): Collection<String> {
        return selector.webElements.map { it.getAttribute(attr) }
    }

    override fun isPresent(selector: ISelector): Boolean {
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS)
        val res = selector.webElements.isNotEmpty()
        webDriver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS)

        return res
    }

    private val ISelector.webElement: WebElement
        get() {
            return webDriver.findElement(By.xpath(this.toXpath()))
        }

    private val ISelector.webElements: Collection<WebElement>
        get() {
            return  webDriver.findElements(By.xpath(this.toXpath()))
        }
}