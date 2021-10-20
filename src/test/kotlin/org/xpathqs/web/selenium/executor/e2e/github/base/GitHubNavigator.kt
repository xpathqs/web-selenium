package org.xpathqs.web.selenium.executor.e2e.github.base

import org.xpathqs.driver.navigation.Navigator as DriverNavigator

object GitHubNavigator: DriverNavigator() {
    override val currentPage: Page
        get() = super.currentPage as Page
}