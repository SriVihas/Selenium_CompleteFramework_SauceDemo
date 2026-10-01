package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceProductDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //constructor
    public SauceProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //locators
    private final By productName = By.className("inventory_details_name");
    private final By productPrice = By.className("inventory_details_price");
    private final By productDesc = By.className("inventory_details_desc");
    private final By addToCartBtn = By.id("add-to-cart");
    private final By backToProductsBtn = By.id("back-to-products");

    //action methods
    public String getProductName() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        return elem.getText();
    }
    public String getProductPrice() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        return elem.getText();
    }
    public String getProductDescription() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(productDesc));
        return elem.getText();
    }
    public void addToCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        btn.click();
    }
    public void clickBackToProducts() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(backToProductsBtn));
        btn.click();
    }




}
