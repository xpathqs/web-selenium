package org.xpathqs.web.selenium.executor.e2e.github.pages.common.menu


import org.xpathqs.core.util.SelectorFactory.textContainsSelector
import org.xpathqs.driver.navigation.annotations.UI
import org.xpathqs.web.factory.HtmlTags.HEADER
import org.xpathqs.web.selenium.executor.e2e.github.base.Block


object Header: Block(base = HEADER) {

    //@UI.Nav.PathTo(byClick = WhyGithub::class)
    val whyGithub = textContainsSelector("Why GitHub")
}