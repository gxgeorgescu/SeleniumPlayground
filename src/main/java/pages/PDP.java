package pages;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.PropertiesLoader;

public class PDP extends BasePage {
    private final By size = By.cssSelector(".size .swatch-option");
    private final By color = By.cssSelector(".color .swatch-option");
    private final By addToCartBtn = By.cssSelector("#product-addtocart-button");
    private final By mainContainer = By.cssSelector(".product-info-main");

    private static final String TEST_SKU = "radiant-tee";

    private static final Logger logger = LogManager.getLogger(PDP.class);

    public void open() {
        openPage(PropertiesLoader.getProperty("environment") + TEST_SKU + ".html");
        waitUntilElementIsVisible(mainContainer, 20);
    }

    public void selectSize() {
        Random random = new Random();
        List<WebElement> availableSizes = driver.findElements(size);
        WebElement randomSize = availableSizes.get(random.nextInt(availableSizes.size() - 1));
        randomSize.click();
        logger.info("Selected size");
    }

    public void selectColor() {
        Random random = new Random();
        List<WebElement> availableColors = driver.findElements(color);
        WebElement randomColor = availableColors.get(random.nextInt(availableColors.size() - 1));
        randomColor.click();
        logger.info("Selected color");
    }

    public void addToCart() {
        selectSize();
        selectColor();
        waitAndClick(addToCartBtn);
        waitUntilElementIsVisible(getSuccessMsgLocator(), 15);
        logger.info("Item was successfully added to cart");
    }
}
