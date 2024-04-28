import enums.ShippingMethods;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.OrderConfirmationPage;
import pages.PDP;
import pages.RegistrationPage;

public class OrderPlacementTest extends BaseTest {
    private final PDP pdp;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;
    private final OrderConfirmationPage orderConfirmationPage;
    private final RegistrationPage registrationPage;

    public OrderPlacementTest() {
        this.pdp = new PDP();
        this.cartPage = new CartPage();
        this.checkoutPage = new CheckoutPage();
        this.orderConfirmationPage = new OrderConfirmationPage();
        this.registrationPage = new RegistrationPage();
    }

    @Test
    @Tag("regression") @Tag("checkout") @Tag("guest")
    public void guestPurchaseFixedRate() {
        pdp.open();
        pdp.consentPersonalDataUseIfVisible();
        pdp.addToCart();
        cartPage.open();
        cartPage.proceedToCheckout();
        checkoutPage.fillInGuestShippingAddress();
        checkoutPage.selectShippingMethod(ShippingMethods.FIXED);
        checkoutPage.proceedToReviewAndPaymentsStep();
        checkoutPage.confirmBillingAndShippingAreTheSame();
        checkoutPage.placeOrder();
        orderConfirmationPage.successMsgIsDisplayed();
    }

    @Test
    @Tag("regression") @Tag("checkout") @Tag("register")
     public void registeredUserPurchaseFixedRate() {
        registrationPage.open();
        registrationPage.consentPersonalDataUseIfVisible();
        registrationPage.createAccount();
        pdp.open();
        pdp.addToCart();
        cartPage.open();
        cartPage.proceedToCheckout();
        checkoutPage.fillInRegisteredShippingAddress();
        checkoutPage.selectShippingMethod(ShippingMethods.FIXED);
        checkoutPage.proceedToReviewAndPaymentsStep();
        checkoutPage.confirmBillingAndShippingAreTheSame();
        checkoutPage.placeOrder();
        orderConfirmationPage.successMsgIsDisplayed();
    }
}
