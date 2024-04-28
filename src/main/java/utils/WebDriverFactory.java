package utils;

import java.net.MalformedURLException;
import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver createWebDriver() throws MalformedURLException {
        WebDriver driver = driverThreadLocal.get();
        boolean headless = Boolean.parseBoolean(PropertiesLoader.getProperty("driver.headless"));
        String driverMode = PropertiesLoader.getProperty("driver.mode");
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless");
            if (driverMode.equals("remote")) {
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-application-cache");
                options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Firefox/109.0 Edg/109.0.1518.52");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                logger.info("Remote web driver created!");
            } else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                logger.info("Web driver created!");
            }
            driverThreadLocal.set(driver);
        }
        return driver;
    }

    public static void closeWebDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            logger.info("Web driver closed successfully");
            driverThreadLocal.remove();
        }
    }
}
