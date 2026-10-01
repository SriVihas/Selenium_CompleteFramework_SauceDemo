import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TC09_CheckoutCompleteFlowTest extends BaseTest {

    @Test(description = "TC09: Validate complete end-to-end checkout, price math calculation, and order completion")
    public void testEndToEndCheckoutFlow() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();

        SauceCartPage cartPage = new SauceCartPage(driver);
        cartPage.clickCheckout();

        SauceCheckoutStepOnePage stepOnePage = new SauceCheckoutStepOnePage(driver);
        stepOnePage.fillShippingInfo("John", "Doe", "90210");

        SauceCheckoutStepTwoPage stepTwoPage = new SauceCheckoutStepTwoPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout-step-two.html"));
        double subtotal = stepTwoPage.getItemSubtotal();
        double tax = stepTwoPage.getTaxAmount();
        double total = stepTwoPage.getTotalAmount();
        System.out.println("Subtotal: $" + subtotal + " | Tax: $" + tax + " | Total: $" + total);
        Assert.assertEquals(total, subtotal + tax, 0.01, "Total price must equal Subtotal + Tax!");
        stepTwoPage.clickFinish();

        SauceCheckoutCompletePage completePage = new SauceCheckoutCompletePage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout-complete.html"));
        Assert.assertEquals(completePage.getCompleteHeaderText(), "Thank you for your order!",
                "Header should confirm completed order!");
        completePage.clickBackHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Should return to '/inventory.html'!");
    }
}
