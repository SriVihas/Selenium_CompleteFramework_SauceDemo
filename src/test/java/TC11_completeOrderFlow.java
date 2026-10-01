import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class TC11_completeOrderFlow extends BaseTest {

    @Test(description = "TC11_performing complete order flow and logout")
    public void testCompleteFlow() throws InterruptedException {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.addBikeLightToCart();
        inventoryPage.addBackpackToCart();
        Assert.assertTrue(inventoryPage.isBackpackRemoveButtonDisplayed(),"Remove CTA should be displayed for backpack item");
        inventoryPage.goToCart();

        SauceCartPage cartPage = new SauceCartPage(driver);
        cartPage.clickCheckout();

        SauceCheckoutStepOnePage stepOnePage = new SauceCheckoutStepOnePage(driver);
        stepOnePage.fillShippingInfo("ben", "tennison", "90210");
        SauceCheckoutStepTwoPage stepTwoPage = new SauceCheckoutStepTwoPage(driver);
        double subtotal=stepTwoPage.getItemSubtotal();
        double tax=stepTwoPage.getTaxAmount();
        double total=stepTwoPage.getTotalAmount();
        Assert.assertEquals(total,subtotal+tax,0.01);
        stepTwoPage.clickFinish();

        SauceCheckoutCompletePage completePage = new SauceCheckoutCompletePage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout-complete.html"));
        completePage.clickBackHome();
        inventoryPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"));
        driver.navigate().back();
        System.out.println(loginPage.getErrorMessageText());


    }
}
