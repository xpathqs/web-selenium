package org.xpathqs.web.selenium.factory

import io.github.bonigarcia.wdm.WebDriverManager
import io.github.bonigarcia.wdm.config.DriverManagerType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.xpathqs.driver.log.Log

class DriverFactory(
    val type: DriverManagerType = DriverManagerType.CHROME,
    val version: String = "104.0.5112.79",
    val capabilities: DesiredCapabilities = DesiredCapabilities.chrome()
) {

    fun create(): WebDriver {
        try {
            WebDriverManager.getInstance(type).driverVersion(version).setup()
        } catch (e: Exception) {
            Log.error(e.message ?: e.toString())
        }
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