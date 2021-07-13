package org.xpathqs.web.selenium.executor

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.driver.actions.ClickAction
import org.xpathqs.driver.actions.InputAction
import org.xpathqs.driver.executor.ActionExecMap
import org.xpathqs.driver.executor.Decorator

open class SeleniumBaseExecutor(
    val webDriver: WebDriver,
    origin: Decorator
) : Decorator(origin) {

    override val actions = ActionExecMap().apply {
        set(ClickAction(Selector()).name) {
            executeAction(it as ClickAction)
        }
        set(InputAction("", Selector()).name) {
            executeAction(it as InputAction)
        }
    }

    protected open fun executeAction(action: ClickAction) {
        if(action.moveMouse) {
            val el = action.on.toWebElement()
            val builder = Actions(webDriver)
            builder.moveToElement(el).click(el)
            builder.perform()
        } else {
            driver.click(action.on)
        }
    }

    protected open fun executeAction(action: InputAction) {
        driver.input(action.on, action.text)
    }

    protected val ISelector.webElement: WebElement
        get() {
            return webDriver.findElement(By.xpath(this.toXpath()))
        }

    protected val ISelector.webElements: Collection<WebElement>
        get() {
            return webDriver.findElements(By.xpath(this.toXpath()))
        }

    private fun ISelector.toWebElement(): WebElement {
        return webDriver.findElement(By.xpath(this.toXpath()))
    }
}