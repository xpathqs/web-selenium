package org.xpathqs.web.selenium.executor

import io.github.bonigarcia.wdm.config.DriverManagerType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.xpathqs.core.reflection.SelectorParser
import org.xpathqs.core.selector.Block
import org.xpathqs.core.selector.extensions.get
import org.xpathqs.core.selector.extensions.plus
import org.xpathqs.core.selector.extensions.prefix
import org.xpathqs.core.selector.extensions.repeat
import org.xpathqs.core.util.SelectorFactory
import org.xpathqs.core.util.SelectorFactory.idSelector
import org.xpathqs.driver.extensions.clear
import org.xpathqs.driver.extensions.input
import org.xpathqs.driver.extensions.text
import org.xpathqs.driver.log.ConsoleLog
import org.xpathqs.driver.log.Log
import org.xpathqs.web.Page
import org.xpathqs.web.extensions.submit
import org.xpathqs.web.factory.HTML
import org.xpathqs.web.factory.HtmlTags
import org.xpathqs.web.factory.HtmlTags.DIV
import org.xpathqs.web.factory.HtmlTags.H3
import org.xpathqs.web.selenium.constants.Global
import org.xpathqs.web.selenium.driver.SeleniumWebDriver
import org.xpathqs.web.selenium.factory.DriverFactory


object GoogleSearch: Page(url = "https://www.google.com") {
    val searchInput = HTML.input(title = "Search")
    val btnSubmit =  HTML.submit()

    fun searchInput(value: String) {
        searchInput
            .clear()
            .input(value)
            .submit()
    }

    object Results: Block(idSelector("search") + DIV.prefix("/").repeat(3)) {
        val lblTitle = H3
        val lblDesc = DIV[2]
    }
}

internal class SeleniumExecutorTest {

    private fun defaultSetup() {
        val driver = DriverFactory.create(DriverManagerType.CHROME)
        val executor = SeleniumExecutor(driver, SeleniumWebDriver(driver))
        Global.init(executor)
        Log.init(ConsoleLog())
        SelectorParser(GoogleSearch).parse()
    }

    @Test
    fun test1() {
        defaultSetup()

        GoogleSearch.open()
        GoogleSearch.searchInput("Wiki")

        val s1 = GoogleSearch.Results.lblTitle.text
        val s2 = GoogleSearch.Results[2].lblTitle.text

        println(s1)
        println(s2)
    }
}