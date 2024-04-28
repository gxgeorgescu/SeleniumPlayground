package pages;

import org.openqa.selenium.By;
import utils.PropertiesLoader;

public class CartPage extends BasePage {

    private final By cartContainer = By.cssSelector(".cart-container");
    private final By proceedToCheckoutBtn = By.cssSelector("[data-role='proceed-to-checkout']");

    public void open() {
        openPage(PropertiesLoader.getProperty("environment") + "checkout/cart/");
        waitUntilElementIsVisible(cartContainer, 15);
    }

    public void proceedToCheckout() {
        waitAndClick(proceedToCheckoutBtn);
    }
}
