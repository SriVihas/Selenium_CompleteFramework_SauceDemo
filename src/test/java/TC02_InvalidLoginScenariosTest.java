import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SauceLoginPage;

public class TC02_InvalidLoginScenariosTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][]{
                // { Username, Password, Expected Error Message, Scenario Name }
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out.", "Locked Out User"},
                {"standard_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service", "Invalid Password"},
                {"invalid_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service", "Invalid Username"},
                {"", "secret_sauce", "Epic sadface: Username is required", "Empty Username"},
                {"standard_user", "", "Epic sadface: Password is required", "Empty Password"}
        };
    }

    @Test(dataProvider = "invalidLoginData", description = "TC02: Validate all negative login error messages")
    public void testInvalidLoginScenarios(String username, String password, String expectedError, String scenarioName) {
        System.out.println("▶️ Testing Negative Scenario: " + scenarioName);
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login(username, password);
        // Verify error message matches expected error
        String actualError = loginPage.getErrorMessageText();
        Assert.assertEquals(actualError, expectedError,
                "Error message mismatch for scenario: " + scenarioName);
        // Verify URL remains on home login page
        Assert.assertFalse(driver.getCurrentUrl().contains("/inventory.html"),
                "User should NOT be redirected to inventory page!");
        System.out.println("✅ " + scenarioName + " PASSED!");
    }

}

