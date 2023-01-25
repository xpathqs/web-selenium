package org.xpathqs.web.selenium.executor.e2e.github.pages.common.menu

import org.xpathqs.core.selector.extensions.plus
import org.xpathqs.core.selector.extensions.withAttribute
import org.xpathqs.core.util.SelectorFactory.tagSelector
import org.xpathqs.core.util.SelectorFactory.textContainsSelector
import org.xpathqs.driver.navigation.annotations.UI
import org.xpathqs.web.factory.HTML
import org.xpathqs.web.selenium.executor.e2e.github.base.Block
import org.xpathqs.web.selenium.executor.e2e.github.pages.features.Features

object WhyGithub: Block(
    base = tagSelector("details").withAttribute("open") + HTML.div(cls = "dropdown-menu")
) {
    //@UI.Nav.PathTo(byClick = Features::class)
    val features = textContainsSelector("Features")
}


