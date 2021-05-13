package org.xpathqs.web.selenium.factory

import io.github.bonigarcia.wdm.WebDriverManager
import io.github.bonigarcia.wdm.config.DriverManagerType
import org.openqa.selenium.WebDriver

object DriverFactory {
    fun create(type: DriverManagerType, version: String = "latest"): WebDriver {
        WebDriverManager.getInstance(type).driverVersion(version).setup()
        val driverClass = Class.forName(type.browserClass())

        return driverClass.newInstance() as WebDriver
    }
}