package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class ScreenshotExtension {
    private static final Logger logger = LogManager.getLogger(ScreenshotExtension.class);

    public static void captureScreenshot(String fileName) {
        try {
            WebDriver driver = WebDriverFactory.createWebDriver();
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File("target/screenshots/" + fileName + ".png");
            FileUtils.copyFile(source, destination);
        } catch (IOException | WebDriverException e) {
            logger.info("screenshot failed:" + e.getMessage());
        }
    }
}
