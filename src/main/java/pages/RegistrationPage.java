package pages;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.PropertiesLoader;

public class RegistrationPage extends BasePage {
    private final By createAccountBtn = By.cssSelector(".customer-account-create .submit");
    private final By firstName = By.cssSelector("#firstname");
    private final By lastName = By.cssSelector("#lastname");
    private final By email = By.cssSelector("#email_address");
    private final By passwordInput = By.cssSelector("#password");
    private final By passwordConfirmationInput = By.cssSelector("#password-confirmation");
    private final By registrationPageContainer = By.cssSelector(".customer-account-create");
    private final By registerSuccessMsg = By.xpath("//div[contains(text(), 'Thank you for registering')]");

    private static final Logger logger = LogManager.getLogger(RegistrationPage.class);


    public void createAccount() {
        Faker faker = new Faker();
        enterText(firstName, faker.address().firstName());
        enterText(lastName, faker.address().lastName());
        enterText(email, faker.internet().emailAddress());
        String password = faker.internet().password(8, 12, true, true, true);
        enterText(passwordInput, password);
        enterText(passwordConfirmationInput, password);
        waitAndClick(createAccountBtn);
        logger.info("Clicked on register button");
        waitUntilElementIsVisible(registerSuccessMsg, 15);
    }

    public void open() {
        openPage(PropertiesLoader.getProperty("environment") + "customer/account/create");
        waitUntilElementIsVisible(registrationPageContainer, 15);
    }
}