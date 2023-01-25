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
    val options: ChromeOptions = ChromeOptions()
) {

    fun create(): WebDriver {
        try {
            WebDriverManager.getInstance(type).driverVersion(version).setup()
        } catch (e: Exception) {
            Log.error(e.message ?: e.toString())
        }
        return if(type == DriverManagerType.CHROME) {
            ChromeDriver(options)
        } else {
            val driverClass = Class.forName(type.browserClass())
            driverClass.getDeclaredConstructor().newInstance() as WebDriver
        }
    }

    companion object {
        val default: DriverFactory
            get() = DriverFactory(
                options = getCapabilities()
            )

        private fun getCapabilities(): ChromeOptions {

            val options = ChromeOptions()
            options.addArguments("--allow-insecure-localhost")
            options.setCapability(ChromeOptions.CAPABILITY, options)
            options.setCapability("acceptInsecureCerts", true)

            return options
        }
    }
}