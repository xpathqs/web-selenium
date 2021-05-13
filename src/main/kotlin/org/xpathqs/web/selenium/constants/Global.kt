package org.xpathqs.web.selenium.constants

import org.xpathqs.driver.const.DriverGlobalProps
import org.xpathqs.driver.executor.IExecutor

open class GlobalCls: DriverGlobalProps() {
    fun init(executor: IExecutor) {
        this.executor = executor

        Global.executor = executor
        org.xpathqs.driver.const.Global.executor = executor
        org.xpathqs.web.constants.Global.executor = executor
    }
}

internal object Global : GlobalCls()