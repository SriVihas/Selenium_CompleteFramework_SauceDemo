import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceLoginPage;

public class TC01_ValidLoginTest extends BaseTest {

    @Test(description = "TC01: Verify valid login redirects to inventory page")
    public void testValidLogin() {
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        // Assertion 1: Verify current URL contains '/inventory.html'
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/inventory.html"),
                "URL should contain '/inventory.html' after valid login!");
        // Assertion 2: Verify page title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title should be 'Swag Labs'!");
        System.out.println("✅ TC01 Valid Login Test Passed!");
    }

}
