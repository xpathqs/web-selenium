package org.xpathqs.web.selenium.executor

import org.xpathqs.core.selector.selector.Selector
import org.xpathqs.driver.actions.*
import org.xpathqs.driver.cache.ICache
import org.xpathqs.driver.executor.ActionExecLambda
import org.xpathqs.driver.executor.ActionExecMap
import org.xpathqs.driver.executor.CacheExecutor
import org.xpathqs.web.driver.IWebDriver

open class SeleniumCachedExecutor(
    driver: IWebDriver, cache: ICache
): CacheExecutor(driver, cache) {
    private val seleniumHandlerCache = ActionExecMap().apply {
        set(ClickAction(Selector()).name) {
            executeAction(it as ClickAction)
        }
        set(InputAction("", Selector()).name) {
            executeAction(it as InputAction)
        }
    }

    override fun hasActionHandler(action: IAction): Boolean {
        if (!seleniumHandlerCache.containsKey(action.name)) {
            return super.hasActionHandler(action)
        }
        return true
    }

    override fun getActionHandler(action: IAction): ActionExecLambda {
        return seleniumHandlerCache[action.name]
            ?: super.getActionHandler(action)
    }

    private fun executeAction(action: ClickAction) {
        driver.click(action.on)
    }

    private fun executeAction(action: InputAction) {
        driver.input(action.on, action.text)
    }
}