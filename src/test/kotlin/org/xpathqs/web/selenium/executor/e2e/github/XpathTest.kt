package org.xpathqs.web.selenium.executor.e2e.github

import org.junit.jupiter.api.Test
import org.xpathqs.core.constants.CoreGlobalProps
import org.xpathqs.core.reflection.PackageScanner
import org.xpathqs.web.selenium.executor.e2e.github.pages.MainPage
import org.xpathqs.web.selenium.executor.e2e.github.pages.common.menu.WhyGithub

class XpathTest {

    init {
        org.xpathqs.core.constants.Global.update(
            CoreGlobalProps("configDefault.yml")
        )
        PackageScanner(this.javaClass.packageName)
            .scan()
    }

    @Test
    fun t1() {
        println(MainPage.Landing.SignUp.button.toXpath())
    }

    @Test
    fun t2() {
        println(MainPage.Landing.SignUp.title.toXpath())
    }

    @Test
    fun t3() {
        println(WhyGithub.features.toXpath())
    }
}