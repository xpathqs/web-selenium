package org.xpathqs.web.selenium.mock

import org.openqa.selenium.*
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.Logs
import java.net.URL
import java.util.concurrent.TimeUnit

open class MkWebDriver : WebDriver {
    override fun findElements(by: By?): MutableList<WebElement> {
        return ArrayList()
    }

    override fun findElement(by: By?): WebElement {
        return MkWebElement()
    }

    override fun get(url: String?) {

    }

    override fun getCurrentUrl(): String {
        return ""
    }

    override fun getTitle(): String {
        return ""
    }

    override fun getPageSource(): String {
        return ""
    }

    override fun close() {

    }

    override fun quit() {

    }

    override fun getWindowHandles(): MutableSet<String> {
        return HashSet()
    }

    override fun getWindowHandle(): String {
        return ""
    }

    override fun switchTo(): WebDriver.TargetLocator {
        return MkTargetLocator()
    }

    override fun navigate(): WebDriver.Navigation {
        return MkNavigator()
    }

    override fun manage(): WebDriver.Options {
        return MkOptions()
    }
}

open class MkTargetLocator : WebDriver.TargetLocator {
    override fun frame(index: Int): WebDriver {
        return MkWebDriver()
    }

    override fun frame(nameOrId: String?): WebDriver {
        return MkWebDriver()
    }

    override fun frame(frameElement: WebElement?): WebDriver {
        return MkWebDriver()
    }

    override fun parentFrame(): WebDriver {
        return MkWebDriver()
    }

    override fun window(nameOrHandle: String?): WebDriver {
        return MkWebDriver()
    }

    override fun newWindow(typeHint: WindowType?): WebDriver {
        TODO("Not yet implemented")
    }

    override fun defaultContent(): WebDriver {
        return MkWebDriver()
    }

    override fun activeElement(): WebElement {
        return MkWebElement()
    }

    override fun alert(): Alert {
        return MkAlert()
    }
}

open class MkAlert : Alert {
    override fun dismiss() {

    }

    override fun accept() {
    }

    override fun getText(): String {
        return ""
    }

    override fun sendKeys(keysToSend: String?) {

    }
}

open class MkNavigator : WebDriver.Navigation {
    override fun back() {
    }

    override fun forward() {
    }

    override fun to(url: String?) {
    }

    override fun to(url: URL?) {
    }

    override fun refresh() {
    }

}


open class MkLogs : Logs {
    override fun get(logType: String?): LogEntries {
        return LogEntries(emptyList())
    }

    override fun getAvailableLogTypes(): MutableSet<String> {
        return HashSet()
    }
}

open class MkTimeouts : WebDriver.Timeouts {
    override fun implicitlyWait(time: Long, unit: TimeUnit?): WebDriver.Timeouts {
        return MkTimeouts()
    }

    override fun setScriptTimeout(time: Long, unit: TimeUnit?): WebDriver.Timeouts {
        return MkTimeouts()
    }

    override fun pageLoadTimeout(time: Long, unit: TimeUnit?): WebDriver.Timeouts {
        return MkTimeouts()
    }
}

open class MkOptions : WebDriver.Options {
    override fun addCookie(cookie: Cookie?) {
    }

    override fun deleteCookieNamed(name: String?) {
    }

    override fun deleteCookie(cookie: Cookie?) {
    }

    override fun deleteAllCookies() {
    }

    override fun getCookies(): MutableSet<Cookie> {
        return HashSet()
    }

    override fun getCookieNamed(name: String?): Cookie {
        return Cookie("", "")
    }

    override fun timeouts(): WebDriver.Timeouts {
        return MkTimeouts()
    }

    override fun window(): WebDriver.Window {
        return MkWindow()
    }

    override fun logs(): Logs {
        return MkLogs()
    }

}

class MkWindow : WebDriver.Window {
    override fun setSize(targetSize: Dimension?) {
    }

    override fun setPosition(targetPosition: Point?) {
    }

    override fun getSize(): Dimension {
        return Dimension(0, 0)
    }

    override fun getPosition(): Point {
        return Point(0, 0)
    }

    override fun maximize() {
    }

    override fun minimize() {
        TODO("Not yet implemented")
    }

    override fun fullscreen() {
    }

}