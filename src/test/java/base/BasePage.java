package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void enterText(By locator, String text) {
        WebElement element =
                waitForVisibility(locator);

        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator) {

        try {
            return waitForVisibility(locator).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    protected void selectByVisibleText(
            By locator,
            String text) {

        WebElement dropdown =
                waitForVisibility(locator);

        Select select =
                new Select(dropdown);

        select.selectByVisibleText(text);
    }

    protected void selectByValue(
            By locator,
            String value) {

        WebElement dropdown =
                waitForVisibility(locator);

        Select select =
                new Select(dropdown);

        select.selectByValue(value);
    }

    protected void selectByIndex(
            By locator,
            int index) {

        WebElement dropdown =
                waitForVisibility(locator);

        Select select =
                new Select(dropdown);

        select.selectByIndex(index);
    }

    protected void waitForUrl(String url) {

        wait.until(
                ExpectedConditions.urlToBe(url)
        );
    }

    protected void waitForUrlContains(String value) {

        wait.until(
                ExpectedConditions.urlContains(value)
        );
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected void scrollDown(int pixels) {

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "window.scrollBy(0, arguments[0]);",
                        pixels
                );
    }

    protected void scrollToTop() {

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "window.scrollTo(0, 0);"
                );
    }

    protected void scrollIntoView(By locator) {

        WebElement element =
                waitForVisibility(locator);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});",
                        element
                );
    }
}