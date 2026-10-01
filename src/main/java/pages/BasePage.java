package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    // 'protected' so child page objects inherit driver and wait!
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Locators
    private final By menuBtn = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetAppStateLink = By.id("reset_sidebar_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void openSideMenu() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(menuBtn));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", btn
        );
    }

    public void clickLogout() {
        openSideMenu();
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(logoutLink));
        try{
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", link
            );
        }
    }

    public void clickResetAppState() {
        openSideMenu();
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(resetAppStateLink));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", link
        );
    }

    public String getCartBadgeCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
            return badge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void goToCart() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }
}