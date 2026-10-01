import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

public class TC010_LogoutTest extends BaseTest {
    @Test(description = "TC10: Verify Hamburger menu opening and successful user logout")
    public void testUserLogout() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "Should be logged in on /inventory.html");
        inventoryPage.clickLogout();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/inventory.html"), "User should no longer be on /inventory.html!");
        Assert.assertTrue(currentUrl.endsWith("/") || currentUrl.contains("saucedemo.com"),
                "User should be redirected back to Login page!");
    }
}
