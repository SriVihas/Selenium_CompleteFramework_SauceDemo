package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    //locators
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");
    //constructors
    public SauceLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //action methods
    public void navigateToLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }
    public void enterUsername(String username) {
        WebElement userElem = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        userElem.clear();
        userElem.sendKeys(username);
    }
    public void enterPassword(String password) {
        WebElement passElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passElem.clear();
        passElem.sendKeys(password);
    }
    public void clickLogin() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }
    public void login(String username, String password) {
        navigateToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    public String getErrorMessageText() {
        WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return err.getText();
    }
}
