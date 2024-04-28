package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;

public class OrderConfirmationPage extends BasePage {
    private final By pageTitle = By.cssSelector(".page-title .base");
    private final By successPage = By.cssSelector(".checkout-success");

    public void successMsgIsDisplayed() {
        waitUntilElementIsVisible(pageTitle, 15);
        waitUntilElementIsVisible(successPage, 15);
        Assert.isTrue(driver.findElement(pageTitle).getText().equals("Thank you for your purchase!"),
                      "Order success message missing from order confirmation page");
    }
}
