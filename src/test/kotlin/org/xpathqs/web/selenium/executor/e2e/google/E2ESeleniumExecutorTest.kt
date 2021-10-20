package org.xpathqs.web.selenium.executor.e2e.google

import org.junit.jupiter.api.Test
import org.xpathqs.core.constants.CoreGlobalProps
import org.xpathqs.core.reflection.scanPackage
import org.xpathqs.core.selector.base.BaseSelector
import org.xpathqs.core.selector.block.Block
import org.xpathqs.core.selector.extensions.core.get


import org.xpathqs.core.selector.extensions.plus
import org.xpathqs.core.selector.extensions.repeat
import org.xpathqs.core.selector.extensions.withAttributeValue
import org.xpathqs.core.selector.selector.prefix
import org.xpathqs.core.util.SelectorFactory.idSelector
import org.xpathqs.driver.extensions.clear
import org.xpathqs.driver.extensions.input
import org.xpathqs.driver.extensions.text
import org.xpathqs.driver.extensions.waitForVisible

import org.xpathqs.driver.log.Log
import org.xpathqs.driver.navigation.NavExecutor
import org.xpathqs.driver.navigation.Navigator
import org.xpathqs.log.BaseLogger

import org.xpathqs.web.extensions.submit
import org.xpathqs.web.factory.HTML
import org.xpathqs.web.factory.HtmlTags
import org.xpathqs.web.factory.HtmlTags.DIV
import org.xpathqs.web.factory.HtmlTags.H3
import org.xpathqs.web.selenium.constants.Global
import org.xpathqs.web.selenium.driver.SeleniumWebDriver
import org.xpathqs.web.selenium.executor.ExecutorFactory
import org.xpathqs.web.selenium.executor.e2e.github.base.Page
import org.xpathqs.web.selenium.factory.DriverFactory


object GoogleSearch : Page(
    baseUrl = "https://www.google.com",
) {
    val searchInput = HtmlTags.INPUT.withAttributeValue("Search")

    fun search(value: String) {
        searchInput
            .input(value)
            .submit()
    }

    object Results : Block(
        idSelector("search") + DIV.prefix("/").repeat(3)
    ) {
        val lblTitle = H3
        val lblDesc = DIV[2]
    }
}

object Nav: Navigator()

internal class E2ESeleniumExecutorTest {

    private fun defaultSetup() {
        System.setProperty("i18n", "ru")
        val driver = DriverFactory().create()
        val webDriver = SeleniumWebDriver(driver)

        val executor =/* NavExecutor(
            ExecutorFactory(driver, webDriver).getCached(),
            Nav
        )*/
                ExecutorFactory(driver, webDriver).getCached()

        Global.init(executor)

        org.xpathqs.core.constants.Global.update(
            CoreGlobalProps("configDefault.yml")
        )

        val traceLog = BaseLogger()

        Log.log = traceLog

        scanPackage(this)
    }

    @Test
    fun tt() {
        print(GoogleSearch.searchInput)
        print(GoogleSearch.Results)
        print(GoogleSearch.Results.lblTitle)
        print(GoogleSearch.Results.lblDesc)
        print(GoogleSearch.Results[2].lblTitle)
    }

    fun print(sel: BaseSelector) {
        println(sel.name)
        println(sel.xpath)
        println()
    }

    @Test
    fun test1() {

        defaultSetup()

        GoogleSearch.open()
        GoogleSearch.search("Wiki")
      //  Thread.sleep(5000)

        //toXpath используется в логах...
        GoogleSearch.Results.lblTitle.waitForVisible()



        val s1 = GoogleSearch.Results.lblTitle.text
        val s2 = GoogleSearch.Results[2].lblTitle.text

        println(s1)
        println(s2)
    }
}