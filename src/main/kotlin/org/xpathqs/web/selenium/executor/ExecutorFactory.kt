package org.xpathqs.web.selenium.executor

import io.github.bonigarcia.wdm.config.DriverManagerType
import org.openqa.selenium.WebDriver
import org.xpathqs.driver.executor.CachedExecutor
import org.xpathqs.driver.executor.Executor
import org.xpathqs.web.cache.HtmlCache
import org.xpathqs.web.constants.Global
import org.xpathqs.web.constants.WebCachedHtml
import org.xpathqs.web.executor.CachedWebExecutor
import org.xpathqs.web.executor.WebExecutor
import org.xpathqs.web.selenium.driver.SeleniumWebDriver
import org.xpathqs.web.selenium.factory.DriverFactory

class ExecutorFactory(
    val driver: WebDriver,
    val webDriver: SeleniumWebDriver = SeleniumWebDriver(driver)
) {
    constructor(type: DriverManagerType) : this(
        DriverFactory.default.create()
    )

    fun getCached(): SeleniumCachedExecutor {
        Global.updateAll(WebCachedHtml())

        return SeleniumCachedExecutor(
            driver,
            SeleniumBaseExecutor(
                driver,
                CachedWebExecutor(
                    WebExecutor(
                        webDriver,
                        CachedExecutor(
                            Executor(
                                webDriver
                            ),
                            HtmlCache()
                        )
                    )
                )
            )
        )
    }


    fun getDefault() =
        SeleniumExecutor(
            driver,
            WebExecutor(
                webDriver,
                Executor(
                    webDriver
                )
            )
        )
}