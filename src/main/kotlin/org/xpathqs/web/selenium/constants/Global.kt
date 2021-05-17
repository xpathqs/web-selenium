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

    internal lateinit var webDriver: WebDriver

    fun init(executor: IExecutor) {
        this.executor = executor

        (executor as? SeleniumExecutor)?.let {
            Global.webDriver = it.webDriver
        }

        Global.executor = executor
        org.xpathqs.driver.constants.Global.executor = executor
        org.xpathqs.web.constants.Global.executor = executor
    }
}

object Global : SeleniumGlobalCls()