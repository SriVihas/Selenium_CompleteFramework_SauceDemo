import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceCartPage;
import pages.SauceInventoryPage;
import pages.SauceLoginPage;

public class TC13_CartRemovalAndBadgeUpdation extends BaseTest {
    @Test(description = "TC13-Remove items in cart and validate the badge updation")
    public void CartRemovalAndBadgeUpdation(){
        SauceLoginPage loginPage = new SauceLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        SauceInventoryPage inventoryPage= new SauceInventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        int items=Integer.parseInt(inventoryPage.getCartBadgeCount());
        Assert.assertEquals(items,2,"two items are added");
        inventoryPage.goToCart();
        SauceCartPage cartPage = new SauceCartPage(driver);
        cartPage.removeBackpackFromCart();
        Assert.assertEquals(cartPage.getCartItemCount(),1,"As item is removved so count=1");
        cartPage.clickContinueShopping();
        int items2=Integer.parseInt(inventoryPage.getCartBadgeCount());
        Assert.assertEquals(items2,1,"updated cart count is 1");
        System.out.println("sceanrio is passed");

    }
}
