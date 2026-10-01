package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceCheckoutStepOnePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SauceCheckoutStepOnePage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //locators
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By cancelBtn = By.id("cancel");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    //action methods
    public void clickContinue() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        btn.click();
        //wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));

    }
    public void clickCancel() {
        driver.findElement(cancelBtn).click();
    }
    public void enterFirstName(String firstName) {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        elem.clear();
        elem.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        elem.clear();
        elem.sendKeys(lastName);
    }
    public void enterPostalCode(String postalCode) {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput));
        elem.clear();
        elem.sendKeys(postalCode);
    }
    public void fillShippingInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }
    public String getErrorMessageText() {
        WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return err.getText();
    }
}
