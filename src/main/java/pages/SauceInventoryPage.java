package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SauceInventoryPage extends BasePage{

    public SauceInventoryPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By inventoryItems = By.className("inventory_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");
    private final By sortDropdown = By.className("product_sort_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By addToCartBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpackBtn = By.id("remove-sauce-labs-backpack");
    private final By addToCartBikeLightBtn = By.id("add-to-cart-sauce-labs-bike-light");
    private final By removeBikeLightBtn = By.id("remove-sauce-labs-bike-light");


    //Action methods
    public int getProductCount() {
        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inventoryItems));
        return items.size();
    }
    // Click a product by its exact name string (e.g. "Sauce Labs Backpack")
    // Updated method in SauceInventoryPage.java
    public void clickProductByName(String productName) {
        By productLink = By.xpath("//div[text()='" + productName + "']");
        WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(productLink));
        try{
            elem.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", elem
            );
        }

    }

    public void goToCart(){
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        try {
            link.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }

    public List<String> getAllProductNames() {
        List<WebElement> nameElements = driver.findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement elem : nameElements) {
            names.add(elem.getText());
        }
        return names;
    }
    public List<Double> getAllProductPrices() {
        List<WebElement> priceElements = driver.findElements(itemPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement elem : priceElements) {
            // Remove '$' symbol and parse to Double
            String priceText = elem.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public void selectSortOption(String optionValue) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select=new Select(dropdown);
        select.selectByValue(optionValue);
    }

    public void addBackpackToCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpackBtn));
        btn.click();
    }
    public void removeBackpackFromCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(removeBackpackBtn));
        btn.click();
    }
    public boolean isBackpackRemoveButtonDisplayed() {
        WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(removeBackpackBtn));
        return ele.isDisplayed();
    }
    public void addBikeLightToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBikeLightBtn)).click();
    }
    public void removeBikeLightFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBikeLightBtn)).click();
    }
    public String getCartBadgeCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
            return badge.getText();
        } catch (Exception e) {
            return "0"; // Badge is not present when cart is empty
        }
    }
}
