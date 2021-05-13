package org.xpathqs.web.selenium.driver

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.web.driver.IWebDriver

open class SeleniumWebDriver(
    protected val driver: WebDriver
) : IWebDriver {
    override val pageSource: String
        get() = driver.pageSource

    override fun clear(selector: ISelector) {
        selector.toWebElement().clear()
    }

    override fun click(x: Int, y: Int) {
        Actions(driver).moveByOffset(x, y).click().build().perform();
    }

    override fun click(selector: ISelector) {
        selector.toWebElement().click()
    }

    override fun input(selector: ISelector, value: String) {
        selector.toWebElement().sendKeys(value)
    }

    override fun open(url: String) {
        driver.get(url)
    }

    override fun submit(selector: ISelector) {
        selector.toWebElement().submit()
    }

    private fun ISelector.toWebElement(): WebElement {
        return driver.findElement(By.xpath(this.toXpath()))
    }
}

