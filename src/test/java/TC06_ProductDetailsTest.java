import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;
import pages.SauceProductDetailsPage;

public class TC06_ProductDetailsTest extends BaseTest {
    @Test(description = "TC06: Verify Product Details Page details and Back to Products button navigation")
    public void testProductDetailsPageNavigation() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);

        String targetProduct = "Sauce Labs Backpack";
        inventoryPage.clickProductByName(targetProduct);
        SauceProductDetailsPage detailsPage = new SauceProductDetailsPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory-item.html"),
                "URL should contain '/inventory-item.html'!");
        Assert.assertEquals(detailsPage.getProductName(), targetProduct, "Product title mismatch!");
        Assert.assertEquals(detailsPage.getProductPrice(), "$29.99", "Product price mismatch!");
        Assert.assertFalse(detailsPage.getProductDescription().isEmpty(), "Product description should not be empty!");
        detailsPage.addToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart badge should display 1 item!");
        detailsPage.clickBackToProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Should return to '/inventory.html'!");
        System.out.println("✅ TC06 Product Details Test Passed!");
    }
}
