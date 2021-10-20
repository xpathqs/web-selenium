package org.xpathqs.web.selenium.executor.e2e.github.pages.features

import org.xpathqs.driver.navigation.annotations.UI
import org.xpathqs.web.factory.HTML
import org.xpathqs.web.selenium.executor.e2e.github.base.Page

object Features: Page() {
    val desc = HTML.h1(textContains = "The tools you need to build what you want.")
}