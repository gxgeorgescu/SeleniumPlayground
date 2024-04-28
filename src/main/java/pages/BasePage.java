package pages;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

public abstract class BasePage {
    private final By consentForm = By.cssSelector(".fc-consent-root");
    private final By acceptConsentForm = By.cssSelector("button.fc-cta-consent");
    private final By successMsg = By.cssSelector(".messages .message-success");

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        try {
            this.driver = WebDriverFactory.createWebDriver();
        } catch (MalformedURLException e) {
            logger.info("Invalid url used for driver creation");
        }
    }

    public void consentPersonalDataUseIfVisible() {
        try {
            waitUntilElementIsVisible(consentForm, 15);
            waitAndClick(acceptConsentForm);
            logger.info("Accepted consent popup!");
        } catch (TimeoutException e) {
            logger.info("Consent popup did not appear in 15 seconds");
        }
    }

    public void waitUntilElementIsVisible(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void implicitWait(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementClickable(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilElementIsNotVisible(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitUntilElementIsEnabled(By locator, long timeout) {
        int increment = 0;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = driver.findElement(locator);
        while (!element.isEnabled() && increment < timeout) {
            increment++;
            try {
                wait.wait(timeout * 1000);
            } catch (InterruptedException e) {
                logger.info(String.format("Element with locator %s is not enabled!", locator.toString()));
            }
        }
    }

    public void waitAndClick(By locator) {
        waitUntilElementIsVisible(locator, 15);
        waitUntilElementClickable(locator, 15);
        driver.findElement(locator).click();
    }

    public void enterText(By locator, String text) {
        waitUntilElementIsVisible(locator, 15);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void selectRandomOptionFromDropdown(By locator) {
        Random random = new Random();
        Select select = new Select(driver.findElement(locator));
        if (select.getOptions().size() > 1) {
            select.selectByIndex(random.nextInt(select.getOptions().size() - 1));
        }
    }
    public void openPage(String loginPageUrl) {
        driver.get(loginPageUrl);
    }

    public By getSuccessMsgLocator() {
        return successMsg;
    }
}
