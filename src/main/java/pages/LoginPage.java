package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import utils.PropertiesLoader;

public class LoginPage extends BasePage {

    private final By emailInput = By.cssSelector("#login-form #email");
    private final By passwordInput = By.cssSelector("#login-form #pass");
    private final By signInBtn = By.cssSelector("#login-form #send2");
    private final By loginPageContainer = By.cssSelector(".customer-account-login");

    public void login() {
        Faker faker = new Faker();
        waitUntilElementIsVisible(emailInput, 15);
        enterText(emailInput, faker.internet().emailAddress());
        enterText(passwordInput, faker.internet().password());
        waitAndClick(signInBtn);
    }

    public void open() {
        openPage(PropertiesLoader.getProperty("environment") + "customer/account/login");
        waitUntilElementIsVisible(loginPageContainer, 15);
    }
}