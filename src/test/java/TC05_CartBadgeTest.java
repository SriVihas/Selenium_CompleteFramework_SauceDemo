import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

public class TC05_CartBadgeTest extends BaseTest {


    @Test(description = "TC05: Verify cart badge counter increments/decrements and button toggles")
    public void testCartBadgeAndButtonToggle() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "0", "Cart should initially be empty!");
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart badge should display 1!");
        Assert.assertTrue(inventoryPage.isBackpackRemoveButtonDisplayed(), "'Remove' button should be displayed!");
        inventoryPage.addBikeLightToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "2", "Cart badge should display 2!");
        inventoryPage.removeBackpackFromCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart badge should decrement to 1!");
        inventoryPage.removeBikeLightFromCart();
        // Assert cart badge is empty/0 again
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "0", "Cart badge should disappear when empty!");
        System.out.println("✅ TC05 Cart Badge & Button Toggle Test Passed!");
    }

    }