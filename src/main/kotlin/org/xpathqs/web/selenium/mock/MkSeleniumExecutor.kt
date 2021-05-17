package org.xpathqs.web.selenium.mock

import org.xpathqs.driver.moke.MkExecutor
import org.xpathqs.web.executor.WebExecutor
import org.xpathqs.web.mock.MkWebDriver
import org.xpathqs.web.selenium.executor.SeleniumExecutor
import org.xpathqs.web.selenium.mock.MkWebDriver as MkSelWebdriver

open class MkSeleniumExecutor : SeleniumExecutor(
    MkSelWebdriver(),
    WebExecutor(
        MkWebDriver(),
        MkExecutor()
    )
)