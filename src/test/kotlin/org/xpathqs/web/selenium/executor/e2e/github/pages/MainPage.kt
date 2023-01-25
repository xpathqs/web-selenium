package org.xpathqs.web.selenium.executor.e2e.github.pages

import org.xpathqs.core.selector.block.Block
import org.xpathqs.core.selector.extensions.contains
import org.xpathqs.core.selector.extensions.text
import org.xpathqs.driver.navigation.annotations.DeterminationType
import org.xpathqs.driver.navigation.annotations.NavOrderType
import org.xpathqs.driver.navigation.annotations.UI
import org.xpathqs.web.factory.HTML
import org.xpathqs.web.factory.HtmlTags.DIV
import org.xpathqs.web.factory.HtmlTags.H1
import org.xpathqs.web.factory.HtmlTags.P
import org.xpathqs.web.selenium.executor.e2e.github.base.Page
import org.xpathqs.web.selenium.executor.e2e.github.pages.common.menu.Header

//@UI.Nav.PathTo(contains = [Header::class])
@UI.Nav.Order(type = NavOrderType.LOW)
object MainPage: Page() {

    @UI.Nav.DeterminateBy(DeterminationType.EXIST_ALL)
    object Landing: Block() {
        object SignUp: Block(DIV contains H1.text("Where the world builds software", contains = true)) {
            val title = H1
            val label = P.text("Millions of developers and companies build,", contains = true)
            val input = HTML.input(id = "user_email")
            val button = HTML.submit(textContains = "Sign up for GitHub")
        }
    }
}