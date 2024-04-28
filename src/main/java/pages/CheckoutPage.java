package pages;

import com.github.javafaker.Faker;
import enums.ShippingMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private final By fixedShippingMethodSelector = By.cssSelector(".radio[value='flatrate_flatrate']");
    private final By tableRateShippingMethodSelector = By.cssSelector(".radio[value='tablerate_bestway']");
    private final By emailInput = By.cssSelector("#customer-email");
    private final By firstNameInput = By.cssSelector("input[name='firstname']");
    private final By lastNameInput = By.cssSelector("input[name='lastname']");
    private final By streetNameInput = By.cssSelector("input[name='street[0]']");
    private final By cityInput = By.cssSelector("input[name='city']");
    private final By stateSelector = By.cssSelector("select[name='region_id']");
    private final By zipCode = By.cssSelector("input[name='postcode']");
    private final By countrySelector = By.cssSelector("select[name='country_id']");
    private final By phoneNumber = By.cssSelector("input[name='telephone']");
    private final By proceedToReviewAndPaymentsStepBtn = By.cssSelector(".primary button.continue");
    private final By billingSameAsShippingCheckbox = By.cssSelector("#billing-address-same-as-shipping-checkmo");
    private final By placeOrderBtn = By.cssSelector(".checkout");
    private final By paymentForm = By.cssSelector("#co-payment-form");
    private final By loader = By.cssSelector(".loader");

    private static final Logger logger = LogManager.getLogger(CheckoutPage.class);


    public void selectShippingMethod(ShippingMethods shippingMethod) {
        if (shippingMethod.equals(ShippingMethods.FIXED)) waitAndClick(fixedShippingMethodSelector);
        else if (shippingMethod.equals(ShippingMethods.TABLE_RATE)) waitAndClick(tableRateShippingMethodSelector);
        else throw new UnsupportedOperationException("Unknown shipping method!");
    }

    public void fillInGuestShippingAddress() {
        Faker faker = new Faker();

        waitUntilElementIsVisible(emailInput, 15);
        enterText(emailInput, faker.internet().emailAddress());
        enterText(firstNameInput, faker.address().firstName());
        enterText(lastNameInput, faker.address().lastName());
        selectRandomOptionFromDropdown(countrySelector);
        enterText(streetNameInput, faker.address().streetName());
        enterText(cityInput, faker.address().city());
        selectRandomOptionFromDropdown(stateSelector);
        enterText(zipCode, faker.address().zipCode());
        enterText(phoneNumber, faker.phoneNumber().cellPhone());
        logger.info("Filled in shipping address for guest user");
    }

    public void fillInRegisteredShippingAddress() {
        Faker faker = new Faker();

        waitUntilElementIsVisible(firstNameInput, 15);
        selectRandomOptionFromDropdown(countrySelector);
        enterText(streetNameInput, faker.address().streetName());
        enterText(cityInput, faker.address().city());
        selectRandomOptionFromDropdown(stateSelector);
        enterText(zipCode, faker.address().zipCode());
        enterText(phoneNumber, faker.phoneNumber().cellPhone());
        logger.info("Filled in shipping address for existing user");
    }

    public void proceedToReviewAndPaymentsStep() {
        waitAndClick(proceedToReviewAndPaymentsStepBtn);
        waitUntilElementIsNotVisible(loader, 15);
        waitUntilElementIsVisible(paymentForm, 15);
    }

    public void confirmBillingAndShippingAreTheSame() {
        waitUntilElementIsNotVisible(loader, 15);
        waitUntilElementIsVisible(billingSameAsShippingCheckbox, 15);
        if (!driver.findElement(billingSameAsShippingCheckbox).isSelected()) {
            driver.findElement(billingSameAsShippingCheckbox).click();
        } else {
            logger.info("Billing same as shipping already checked");
        }
        waitUntilElementIsNotVisible(loader, 15);
    }

    public void placeOrder() {
        implicitWait(1);
        waitUntilElementIsEnabled(placeOrderBtn, 15);
        waitAndClick(placeOrderBtn);
        logger.info("Clicked on place order button");
    }
}
