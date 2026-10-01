import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC04_ProductSortingTest extends BaseTest {

private SauceInventoryPage loginAndGetInventoryPage(){
    SauceLoginPage loginPage=new SauceLoginPage(driver);
    loginPage.login("standard_user", "secret_sauce");
    return new SauceInventoryPage(driver);
}
    @Test(description = "Verify Sorting by Price: Low to High")
    public void testSortPriceLowToHigh() {
        // Select 'Price (low to high)' -> value="lohi"
        SauceInventoryPage inventoryPage = loginAndGetInventoryPage();
        inventoryPage.selectSortOption("lohi");
        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        // Verify prices are sorted in ascending order
        for (int i = 0; i < actualPrices.size() - 1; i++) {
            Assert.assertTrue(actualPrices.get(i) <= actualPrices.get(i + 1),
                    "Price at index " + i + " should be <= price at index " + (i + 1));
        }
        System.out.println("✅ Price Low to High Sorting Passed!");
    }
    @Test(description = "Verify Sorting by Price: High to Low")
    public void testSortPriceHighToLow() {
        // Select 'Price (high to low)' -> value="hilo"
        SauceInventoryPage inventoryPage = loginAndGetInventoryPage();
        inventoryPage.selectSortOption("hilo");
        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        // Verify prices are sorted in descending order
        for (int i = 0; i < actualPrices.size() - 1; i++) {
            Assert.assertTrue(actualPrices.get(i) >= actualPrices.get(i + 1),
                    "Price at index " + i + " should be >= price at index " + (i + 1));
        }
        System.out.println("✅ Price High to Low Sorting Passed!");
    }
    @Test(description = "Verify Sorting by Name: Z to A")
    public void testSortNameZtoA() {
        // Select 'Name (Z to A)' -> value="za"
        SauceInventoryPage inventoryPage = loginAndGetInventoryPage();
        inventoryPage.selectSortOption("za");
        List<String> actualNames = inventoryPage.getAllProductNames();
        // Create expected list sorted in reverse alphabetical order
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());
        Assert.assertEquals(actualNames, expectedNames, "Names should be sorted from Z to A!");
        System.out.println("✅ Name Z to A Sorting Passed!");
    }

}
