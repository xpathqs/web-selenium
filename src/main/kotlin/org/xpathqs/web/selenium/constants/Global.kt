package org.xpathqs.web.selenium.constants

import org.openqa.selenium.WebDriver
import org.xpathqs.driver.executor.IExecutor
import org.xpathqs.web.constants.WebGlobalCls
import org.xpathqs.web.selenium.executor.SeleniumExecutor
import java.time.Duration

open class SeleniumGlobalCls : WebGlobalCls() {
    val EXCPLICIT_TIMEOUT: Duration
        get() = Duration.ofMillis(
            (props["constants.timeouts.explicit"] as? String
                ?: "5000").toLong()
        )

    val webDriver = ThreadLocal<WebDriver>()

    fun init(executor: IExecutor) {
        this.localExecutor.set(executor)

        (executor as? SeleniumExecutor)?.let {
            webDriver.set(it.webDriver)
        }

        Global.localExecutor.set(executor)
        org.xpathqs.driver.constants.Global.localExecutor.set(executor)
        org.xpathqs.web.constants.Global.localExecutor.set(executor)
    }
}

object Global : SeleniumGlobalCls()