package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceCheckoutStepTwoPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By subtotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");
    private final By finishBtn = By.id("finish");
    private final By cancelBtn = By.id("cancel");

    // Constructor
    public SauceCheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public double getItemSubtotal() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalLabel));
        // Extracts '29.99' from "Item total: $29.99"
        String text = elem.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(text);
    }

    public double getTaxAmount() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(taxLabel));
        // Extracts '2.40' from "Tax: $2.40"
        String text = elem.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(text);
    }

    public double getTotalAmount() {
        WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(totalLabel));
        // Extracts '32.39' from "Total: $32.39"
        String text = elem.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(text);
    }

    public void clickFinish() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(finishBtn));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", btn
        );
    }
}