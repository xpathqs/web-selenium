package org.xpathqs.web.selenium.executor.e2e.github.base

import org.xpathqs.driver.navigation.annotations.UI
import org.xpathqs.driver.navigation.base.*
import org.xpathqs.driver.navigation.impl.Loadable
import org.xpathqs.driver.navigation.impl.NavigableDetermination

import org.xpathqs.web.WebPage
import org.xpathqs.web.selenium.executor.e2e.github.pages.common.menu.Header

//@UI.Nav.PathTo(Header::class)
open class Page(
    url: String = "/",
    baseUrl: String = "https://github.com"
): WebPage(
    url = "$baseUrl$url"
), INavigableDeterminationDelegate, ILoadableDelegate {
    override val nav: INavigableDetermination
        = NavigableDetermination(GitHubNavigator, this)

    override val loadable: ILoadable
         = Loadable(this)

    override fun afterReflectionParse() {
        GitHubNavigator.register(this)
    }
}