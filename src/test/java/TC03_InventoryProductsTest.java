import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

import java.util.List;

public class TC03_InventoryProductsTest extends BaseTest {


    @Test(description = "TC03: Verify product count and valid product details")
    public void testInventoryProductList() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        int count = inventoryPage.getProductCount();
        Assert.assertEquals(count, 6, "Inventory page should display exactly 6 products!");

        List<Double> productPrices = inventoryPage.getAllProductPrices();
        Assert.assertEquals(productPrices.size(), 6);
        for (Double price : productPrices) {
            Assert.assertTrue(price > 0.0, "Product price should be greater than $0.00!");
        }
        System.out.println("✅ Testcase 1 of TC03 Inventory Products Test Passed!");
    }

}
