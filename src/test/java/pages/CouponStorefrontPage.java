package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import utils.ConfigReader;
import utils.ScrollUtils;

public class CouponStorefrontPage extends BasePage {

    private By searchBox =
            By.cssSelector("input[name='search']");

    private By searchButton =
            By.cssSelector("button.btn.btn-light.btn-lg");

    private By productLink =
            By.xpath(
                    "//a[contains(normalize-space(),'" +
                    ConfigReader.get("productName") +
                    "')]"
            );

    private By smallOption =
            By.cssSelector(
                    "input[type='checkbox'][name^='option['][name$='[]']"
            );

    private By addToCart =
            By.id("button-cart");

    private By successMessage =
            By.cssSelector(".alert.alert-success");

    // header cart button, matched by its "item(s)" text
    private By cartDropdown =
            By.xpath(
                    "//button[@data-bs-toggle='dropdown']" +
                    "[contains(normalize-space(.),'item(s)')]"
            );

    private By openDropdownMenu =
            By.cssSelector(".dropdown-menu.show");

    private By viewCart =
            By.xpath(
                    "//a[contains(@href,'route=checkout/cart')]" +
                    "[.//strong or contains(normalize-space(.),'View Cart')]"
            );

    private By cartTable =
            By.cssSelector("#output-cart table");

    private By couponAccordion =
            By.xpath(
                    "//button[contains(normalize-space(),'Use Coupon Code')]"
            );

    private By couponInput =
            By.id("input-coupon");

    private By applyCoupon =
            By.cssSelector("button[form='form-coupon']");

    private By couponError =
            By.cssSelector(".alert.alert-danger");

    public CouponStorefrontPage(WebDriver driver) {
        super(driver);
    }

    private void scrollAndClick(By locator) {

        waitForVisibility(locator);

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(locator)
        );

        waitForClickable(locator);

        click(locator);
    }

    private void jsClick(By locator) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                driver.findElement(locator)
        );
    }

    public CouponStorefrontPage searchProduct(String productName) {

        enterText(searchBox, productName);

        click(searchButton);

        return this;
    }

    public CouponStorefrontPage openProduct() {

        scrollAndClick(productLink);

        return this;
    }

    public CouponStorefrontPage selectSmallOption() {

        scrollAndClick(smallOption);

        wait.until(
                d -> d.findElement(smallOption).isSelected()
        );

        return this;
    }

    public CouponStorefrontPage addProductToCart() {

        scrollAndClick(addToCart);

        waitForVisibility(successMessage);

        // let the header cart finish reloading
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ScrollUtils.scrollToTop(driver);

        return this;
    }

    public CouponStorefrontPage openCart() {

        ScrollUtils.scrollToTop(driver);

        // header cart re-renders after add to cart
        wait.until(
                ExpectedConditions.presenceOfElementLocated(cartDropdown)
        );

        // normal click first, JS click as fallback
        try {
            wait.until(
                    ExpectedConditions.elementToBeClickable(cartDropdown)
            ).click();
        } catch (Exception e) {
            jsClick(cartDropdown);
        }

        // menu still closed? JS click once more
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(openDropdownMenu)
            );
        } catch (Exception e) {
            jsClick(cartDropdown);
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(openDropdownMenu)
            );
        }

        wait.until(
                ExpectedConditions.elementToBeClickable(viewCart)
        ).click();

        waitForUrlContains("checkout/cart");

        waitForVisibility(cartTable);

        return this;
    }

    public CouponStorefrontPage openCouponSection() {

        scrollAndClick(couponAccordion);

        return this;
    }

    public CouponStorefrontPage enterCoupon(String couponCode) {

        waitForVisibility(couponInput);

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(couponInput)
        );

        enterText(couponInput, couponCode);

        return this;
    }

    public CouponStorefrontPage applyCoupon() {

        scrollAndClick(applyCoupon);

        return this;
    }

    public String getCouponError() {

        return waitForVisibility(couponError).getText();
    }

    public boolean isCouponErrorDisplayed() {

        return waitForVisibility(couponError).isDisplayed();
    }
}