package org.xpathqs.web.selenium.util

import org.openqa.selenium.*
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
       /* val bytes = driver.findElement(By.xpath("//")).getScreenshotAs(OutputType.BYTES)

        val input = ByteArrayInputStream(bytes)*/
        val newBi =  take()//ImageIO.read(input)

        val driverSize = driver.manage().window().size
        val dw = newBi.width.toDouble() / driverSize.width.toDouble()
        val dh = dw//newBi.height.toDouble() / driverSize.height.toDouble()

        val g = newBi.createGraphics()
        g.stroke = BasicStroke(10F);
        g.color = Color.BLACK
        g.drawRect(
            (rect.x * dw).toInt(),
            (rect.y * dh).toInt(),
            (rect.width * dw+1).toInt(),
            (rect.height * dh+1).toInt()
        )

        g.stroke = BasicStroke(5F);
        g.color = Color.MAGENTA
        g.drawRect(
            (rect.x * dw).toInt(),
            (rect.y * dh).toInt(),
            (rect.width * dw).toInt(),
            (rect.height * dh).toInt()
        )

        g.stroke = BasicStroke(2F);
        g.color = Color.WHITE
        g.drawRect(
            (rect.x * dw).toInt(),
            (rect.y * dh).toInt(),
            (rect.width * dw).toInt(),
            (rect.height * dh).toInt()
        )

        return newBi
    }
}