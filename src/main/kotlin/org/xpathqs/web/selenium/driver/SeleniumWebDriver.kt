package org.xpathqs.web.selenium.driver

import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.xpathqs.core.selector.base.BaseSelector
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.core.selector.base.findAnnotation
import org.xpathqs.driver.log.Log
import org.xpathqs.driver.navigation.annotations.UI
import org.xpathqs.web.selenium.constants.Global


open class SeleniumWebDriver(
    protected val driver: WebDriver
) : ISeleniumWebDriver {
    override val pageSource: String
        get() = driver.pageSource

    override fun clear(selector: ISelector) {
        click(selector)

        val elem = selector.toWebElement()
        while (elem.getAttribute("value")?.isNotEmpty() == true) {
            elem.sendKeys(Keys.BACK_SPACE)
        }
    }

    override fun click(x: Int, y: Int) {
        Actions(driver).moveByOffset(x, y).click().build().perform()
    }

    override fun click(selector: ISelector) {
        try {
            selector.toWebElement().click()
        } catch (e: Exception) {
            val t = WebDriverWait(driver, Global.WAIT_FOR_ELEMENT_TIMEOUT.seconds)
            val elem = selector.toWebElement()

            eval {
                (driver as JavascriptExecutor).executeScript(
                    "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                    elem)
                Thread.sleep(500)
            }
            eval {
                t.until(ExpectedConditions.elementToBeClickable(elem))
            }
            eval {
                t.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector.toXpath())))
            }
            try {
                elem.click()
            }catch (e: ElementClickInterceptedException) {
                val elem = selector.toWebElement()
                elem.click()
            }
        }
    }

    override fun input(selector: ISelector, value: String) {
        try {
            selector.toWebElement().sendKeys(value)
        } catch (e: Exception) {
            click(selector)
            selector.toWebElement().sendKeys(value)
        }
        val ms = (selector as? BaseSelector)?.findAnnotation<UI.Widgets.Input>()?.afterInputDelayMs ?: 0
        if(ms > 0) {
            Log.action("Sleep for $ms after input into $selector") {
                Thread.sleep(ms)
            }
        }
    }

    private fun eval(l: ()->Unit) {
        try {
            l()
        } catch (e: Exception) {
        }
    }

    override fun open(url: String) {
        driver.get(url)
    }

    override fun submit(selector: ISelector) {
        selector.toWebElement().submit()
    }

    private fun ISelector.toWebElement(): WebElement {
        return try {
            driver.findElement(By.xpath(this.toXpath()))
        } catch (e: Exception) {
            Log.error("Selector $name can't be found")
            throw e
        }
    }
}

