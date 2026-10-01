package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SauceCartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    //locators
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By checkoutBtn = By.id("checkout");
    private final By removeBackpackBtn = By.id("remove-sauce-labs-backpack");
    private final By cartItems = By.className("cart_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");
    private final By removeBikeLightBtn = By.id("remove-sauce-labs-bike-light");

    //constructors
    public SauceCartPage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //action methods
    public int getCartItemCount() {
        try {
            List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItems));
            return items.size();
        } catch (Exception e) {
            return 0; // If cart is genuinely empty and times out
        }
    }

    public List<String> getCartItemNames() {
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(itemNames));

        List<String> names = new ArrayList<>();
        for (WebElement elem : elements) {
            names.add(elem.getText());
        }
        return names;
    }

    public void removeBackLightFromCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(removeBikeLightBtn));
        btn.click();
    }
    public void removeBackpackFromCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(removeBackpackBtn));
        btn.click();
    }
    public void clickContinueShopping() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn));
        btn.click();
    }
    public void clickCheckout() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutBtn));
        try {
            btn.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", btn);
        }


    }
}
