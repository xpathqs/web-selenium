package org.xpathqs.web.selenium.factory

import io.github.bonigarcia.wdm.WebDriverManager
import io.github.bonigarcia.wdm.config.DriverManagerType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

class DriverFactory(
    val type: DriverManagerType = DriverManagerType.CHROME,
    val version: String = "latest",
    val capabilities: DesiredCapabilities = DesiredCapabilities.chrome()
) {

    fun create(): WebDriver {
        WebDriverManager.getInstance(type).driverVersion(version).setup()
        return if(type == DriverManagerType.CHROME) {
            ChromeDriver(capabilities)
        } else {
            val driverClass = Class.forName(type.browserClass())
            driverClass.getDeclaredConstructor().newInstance() as WebDriver
        }
    }

    companion object {
        val default: DriverFactory
            get() = DriverFactory(
                capabilities = getCapabilities()
            )

        private fun getCapabilities(): DesiredCapabilities {
            val caps = DesiredCapabilities.chrome()

            val options = ChromeOptions()
            options.addArguments("--allow-insecure-localhost")
            caps.setCapability(ChromeOptions.CAPABILITY, options)
            caps.setCapability("acceptInsecureCerts", true)

            return caps
        }
    }
}