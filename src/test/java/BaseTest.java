import java.net.MalformedURLException;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotExtension;
import utils.WebDriverFactory;

public abstract class BaseTest {

    protected static WebDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        ScreenshotExtension.captureScreenshot(String.valueOf(Instant.now().toEpochMilli()));
        WebDriverFactory.closeWebDriver(driver);
    }

    @AfterAll
    public static void tearDown() {
        WebDriverFactory.closeWebDriver(driver);
    }
}
