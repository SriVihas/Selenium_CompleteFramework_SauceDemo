import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceCartPage;
import pages.SauceCheckoutStepOnePage;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

public class TC08_CheckoutFormValidationTest extends BaseTest {

    // Helper method to navigate to Checkout Step One
    private SauceCheckoutStepOnePage navigateToCheckoutStepOne() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.goToCart();
        SauceCartPage cartPage = new SauceCartPage(driver);
        cartPage.clickCheckout();
        return new SauceCheckoutStepOnePage(driver);
    }

    @Test(description = "TC08 - Test 1: Validate error messages for missing shipping fields")
    public void test01_CheckoutFormNegativeValidations() {
        SauceCheckoutStepOnePage stepOnePage = navigateToCheckoutStepOne();
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout-step-one.html"));
        // 1. Leave First Name blank -> Assert 'First Name is required'
        stepOnePage.clickContinue();
        Assert.assertEquals(stepOnePage.getErrorMessageText(), "Error: First Name is required");
        // 2. Enter First Name, leave Last Name blank -> Assert 'Last Name is required'
        stepOnePage.enterFirstName("John");
        stepOnePage.clickContinue();
        Assert.assertEquals(stepOnePage.getErrorMessageText(), "Error: Last Name is required");
        // 3. Enter Last Name, leave Postal Code blank -> Assert 'Postal Code is required'
        stepOnePage.enterLastName("Doe");
        stepOnePage.clickContinue();
        Assert.assertEquals(stepOnePage.getErrorMessageText(), "Error: Postal Code is required");
        System.out.println("✅ Test 1: Negative Form Validations Passed!");
    }
    @Test(description = "TC08 - Test 2: Validate successful form submission and navigation to Step Two")
    public void test02_CheckoutFormSuccessfulSubmission() {
        SauceCheckoutStepOnePage stepOnePage = navigateToCheckoutStepOne();
        // Fill all shipping fields and submit
        stepOnePage.fillShippingInfo("John", "Doe", "90210");
        // Assert redirect to Step Two (/checkout-step-two.html)
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout-step-two.html"),
                "Should navigate to '/checkout-step-two.html'!");
        System.out.println("✅ Test 2: Successful Form Submission Passed!");
    }
}
