import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TC12_ProductSortingValidation extends BaseTest {

    public SauceInventoryPage commonPageLoaderForTests(){
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        return new SauceInventoryPage(driver);
    }
    @Test(description = "Tc12-For testing sorting functionality",priority = 1)
    public void testProductSorting(){
        SauceInventoryPage inventoryPage=commonPageLoaderForTests();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
inventoryPage.selectSortOption("az");
List<String> elems=inventoryPage.getAllProductNames();
List<String> copy=new ArrayList<>(elems);
copy.sort(null);
Assert.assertTrue(elems.equals(copy),"Both should be equal to validate that correct sorting happend");
System.out.println("Sceanrio 1 of Testcase 12 passed");
    }
    @Test(description = "Tc12-For testing sorting functionality",priority = -10)
    public void testProductSortingDescending(){
        SauceInventoryPage inventoryPage=commonPageLoaderForTests();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
        inventoryPage.selectSortOption("za");
        List<String> elems=inventoryPage.getAllProductNames();
        List<String> copy=new ArrayList<>(elems);
        copy.sort(Collections.reverseOrder());
        Assert.assertTrue(elems.equals(copy),"Both should be equal to validate that correct sorting happend");
        System.out.println("Sceanrio2 of Testcase 12 passed");


    }
}
