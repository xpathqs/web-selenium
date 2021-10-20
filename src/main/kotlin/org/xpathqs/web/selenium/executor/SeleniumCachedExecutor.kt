package org.xpathqs.web.selenium.executor

import org.openqa.selenium.WebDriver
import org.xpathqs.driver.executor.Decorator

open class SeleniumCachedExecutor(
    webDriver: WebDriver,
    origin: Decorator
) : SeleniumBaseExecutor(webDriver, origin)