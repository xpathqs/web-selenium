package org.xpathqs.web.selenium.executor

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.driver.actions.ClickAction
import org.xpathqs.driver.actions.ScreenShotAction
import org.xpathqs.driver.executor.ActionExecMap
import org.xpathqs.driver.executor.Decorator
import org.xpathqs.driver.extensions.screenshot
import org.xpathqs.driver.log.Log
import org.xpathqs.web.selenium.util.Screenshot
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


open class SeleniumBaseExecutor(
    val webDriver: WebDriver,
    origin: Decorator
) : Decorator(origin) {

    override val actions = ActionExecMap().apply {
        set(ClickAction(Selector()).name) {
            executeAction(it as ClickAction)
        }
        set(ScreenShotAction(Selector()).name) {
            executeAction(it as ScreenShotAction)
        }
    }

    protected open fun executeAction(action: ClickAction) {
        Log.step(action.toStyledString()) {
            action.on.screenshot()
            if(action.moveMouse) {
                val el = action.on.toWebElement()
                val builder = Actions(webDriver)
                builder.moveToElement(el).click(el)
                builder.perform()
            } else {
                driver.click(action.on)
            }
        }
    }

    protected open fun executeAction(action: ScreenShotAction) {
        if(!enableScreenshots) {
            return
        }

        val sc = Screenshot(webDriver)
        val bi = if(action.boundRect) {
            val elem = action.sel.toWebElement()
            (webDriver as JavascriptExecutor).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                elem)
            Thread.sleep(500)

            sc.take(action.sel.toWebElement().rect)
        } else sc.take()

        val baos = ByteArrayOutputStream()
        ImageIO.write(bi, "png", baos)
        val bytes = baos.toByteArray()

        Log.attachment(bytes.toTypedArray())
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

    companion object {
        val enableScreenshots = (System.getenv("enableScreenshots") ?: "false").toBoolean()
    }
}