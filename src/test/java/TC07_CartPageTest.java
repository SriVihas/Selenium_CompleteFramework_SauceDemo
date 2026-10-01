import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceCartPage;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

import java.util.List;

public class TC07_CartPageTest extends BaseTest {
    @Test(description = "TC07: Verify Cart Page contents, item removal, and Continue Shopping navigation")
    public void testCartPageValidation() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "2");
        inventoryPage.goToCart();
        SauceCartPage cartPage = new SauceCartPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart.html"), "Should be on '/cart.html'!");
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart should contain 2 items!");
        List<String> itemNames = cartPage.getCartItemNames();
        Assert.assertTrue(itemNames.contains("Sauce Labs Backpack"));
        Assert.assertTrue(itemNames.contains("Sauce Labs Bike Light"));
        cartPage.removeBackpackFromCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain 1 item after removal!");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Should return to '/inventory.html'!");
        System.out.println("✅ TC07 Cart Page Test Passed!");
    }
}
