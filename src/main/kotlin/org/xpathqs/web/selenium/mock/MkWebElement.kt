package org.xpathqs.web.selenium.mock

import org.openqa.selenium.*

open class MkWebElement : WebElement {
    override fun findElements(by: By?): MutableList<WebElement> {
        TODO("Not yet implemented")
    }

    override fun findElement(by: By?): WebElement {
        TODO("Not yet implemented")
    }

    override fun <X : Any?> getScreenshotAs(target: OutputType<X>?): X {
        TODO("Not yet implemented")
    }

    override fun click() {
        TODO("Not yet implemented")
    }

    override fun submit() {
        TODO("Not yet implemented")
    }

    override fun sendKeys(vararg keysToSend: CharSequence?) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun getTagName(): String {
        TODO("Not yet implemented")
    }

    override fun getAttribute(name: String?): String {
        TODO("Not yet implemented")
    }

    override fun isSelected(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getText(): String {
        TODO("Not yet implemented")
    }

    override fun isDisplayed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLocation(): Point {
        TODO("Not yet implemented")
    }

    override fun getSize(): Dimension {
        TODO("Not yet implemented")
    }

    override fun getRect(): Rectangle {
        TODO("Not yet implemented")
    }

    override fun getCssValue(propertyName: String?): String {
        TODO("Not yet implemented")
    }
}