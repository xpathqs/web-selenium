package org.xpathqs.web.selenium.executor.e2e.github.pages.common.menu

import org.xpathqs.core.selector.extensions.arg
import org.xpathqs.core.selector.extensions.childCount
import org.xpathqs.core.selector.extensions.normalizedTextNotEmpty
import org.xpathqs.core.selector.extensions.plus
import org.xpathqs.core.util.SelectorFactory.xpathSelector
import org.xpathqs.driver.navigation.annotations.UI
import org.xpathqs.web.factory.HTML
import org.xpathqs.web.factory.HtmlTags
import org.xpathqs.web.factory.HtmlTags.DIV
import org.xpathqs.web.factory.HtmlTags.HEADER
import org.xpathqs.web.selenium.executor.e2e.github.base.Block

object Search : Block(base = HEADER) {
    val input = HtmlTags.INPUT.arg("@aria-label", "Search GitHub")

    @UI.Nav.PathTo(byInvoke = Results::class)
    fun search(input: String = "search") {

    }

    object Results: Block(base
        = HTML.form(action = "/search") + xpathSelector("//li/a")
    ) {
   //     @PageLoadSelector
        val label = DIV.normalizedTextNotEmpty().childCount(0)
    }
}