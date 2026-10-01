package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceCheckoutCompletePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //constructor
    public SauceCheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //locators
    private final By completeHeader = By.className("complete-header");
    private final By backHomeBtn = By.id("back-to-products");

    //actions
    public String getCompleteHeaderText() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        return elem.getText();
    }
    public void clickBackHome() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(backHomeBtn));
        try{
            btn.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", btn
            );
        }

    }
}
