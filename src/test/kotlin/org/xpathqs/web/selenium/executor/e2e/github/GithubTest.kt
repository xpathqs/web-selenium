package org.xpathqs.web.selenium.executor.e2e.github

import org.junit.jupiter.api.Test
import org.xpathqs.core.reflection.scanPackage
import org.xpathqs.driver.extensions.click
import org.xpathqs.driver.extensions.text
import org.xpathqs.web.selenium.executor.e2e.github.base.GitHubNavigator
import org.xpathqs.web.selenium.executor.e2e.github.pages.MainPage
import org.xpathqs.web.selenium.executor.e2e.github.pages.features.Features

class GithubTest: BaseUiTest() {

    @Test
    fun test() {
        setup()
        scanPackage(this)
        GitHubNavigator.initNavigations()
        MainPage.open()

        println(
            Features.desc.text
        )
        println(
            GitHubNavigator.currentPage.name
        )

    }

    @Test
    fun navTest() {
        scanPackage(this)
        GitHubNavigator.initNavigations()

       /* println(
            GitHubNavigator.findPath(MainPage, Features)
        )*/
    }
}