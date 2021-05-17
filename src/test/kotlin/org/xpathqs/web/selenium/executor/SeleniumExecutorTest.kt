package org.xpathqs.web.selenium.executor

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.Test
import org.xpathqs.core.util.SelectorFactory.tagSelector
import org.xpathqs.driver.actions.ClickAction
import org.xpathqs.driver.actions.WaitAction
import org.xpathqs.driver.executor.Executor
import org.xpathqs.web.Page
import org.xpathqs.web.actions.OpenUrlAction
import org.xpathqs.web.executor.WebExecutor
import org.xpathqs.web.selenium.constants.Global
import org.xpathqs.web.selenium.driver.SeleniumWebDriver
import org.xpathqs.web.selenium.mock.MkWebDriver

class SeleniumExecutorTest {
    val driver = MkWebDriver()
    val webDriver = SeleniumWebDriver(driver)

    //MkSeleniumExecutor()
    val executor = SeleniumExecutor(
        driver,
        WebExecutor(
            webDriver,
            Executor(
                webDriver
            )
        )
    )

    init {
        Global.init(executor)
    }

    @Test
    fun hasActionHandlerForWaitAction() {
        assertThat(
            executor.hasActionHandler(WaitAction())
        ).isEqualTo(true)
    }

    @Test
    fun hasActionHandlerForOpenUrlAction() {
        assertThat(
            executor.hasActionHandler(OpenUrlAction(Page()))
        ).isEqualTo(true)
    }

    @Test
    fun actionHandlerForOpenUrlAction() {
        assertThat(
            executor.getActionHandler(OpenUrlAction(Page()))
        ).isNotNull()
    }

    @Test
    fun hasActionHandlerForClickAction() {
        assertThat(
            executor.hasActionHandler(
                ClickAction(
                    tagSelector("div")
                )
            )
        ).isEqualTo(true)
    }

    @Test
    fun open() {
        Page("").open()
    }
}