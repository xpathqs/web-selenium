package org.xpathqs.web.selenium.util

import org.openqa.selenium.OutputType
import org.openqa.selenium.Rectangle
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import java.awt.BasicStroke
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

class Screenshot(
    private val driver: WebDriver
) {
    fun take(): BufferedImage {
        val bytes = (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)

        val input = ByteArrayInputStream(bytes)
        return ImageIO.read(input)
    }

    fun take(rect: Rectangle): BufferedImage {
        val newBi = take()

        val driverSize = driver.manage().window().size
        val d = newBi.width / driverSize.width

        val g = newBi.createGraphics()
        g.stroke = BasicStroke(20F);
        g.color = Color.BLACK
        g.drawRect(
            (rect.x * d),
            (rect.y * d),
            (rect.width * d+1),
            (rect.height * d+1)
        )

        g.stroke = BasicStroke(15F);
        g.color = Color.MAGENTA
        g.drawRect(
            (rect.x * d),
            (rect.y * d),
            (rect.width * d),
            (rect.height * d)
        )

        g.stroke = BasicStroke(3F);
        g.color = Color.WHITE
        g.drawRect(
            (rect.x * d),
            (rect.y * d),
            (rect.width * d),
            (rect.height * d)
        )

        return newBi
    }
}