package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;
import utils.ScrollUtils;

public class CouponPage extends BasePage {

    private By closeButton =
            By.cssSelector("button.btn-close[data-bs-dismiss='modal']");

    private By marketingMenu =
            By.cssSelector("a[href='#collapse-7']");

    private By couponsMenu =
            By.cssSelector(
                    "#collapse-7 a[href*='route=marketing/coupon']"
            );

    private By addNewButton =
            By.cssSelector("a[title='Add New']");

    private By couponName =
            By.id("input-name");

    private By couponCode =
            By.id("input-code");

    private By couponType =
            By.id("input-type");

    private By discount =
            By.id("input-discount");

    private By totalAmount =
            By.id("input-total");

    private By customerLogin =
            By.id("input-logged");

    private By freeShipping =
            By.id("input-shipping");

    private By category =
            By.id("input-category");

    private By saveButton =
            By.cssSelector("button[form='form-coupon']");

    public CouponPage(WebDriver driver) {
        super(driver);
    }

    public CouponPage closePopup() {

        try {

            if (driver.findElement(closeButton).isDisplayed()) {
                driver.findElement(closeButton).click();
            }

        } catch (Exception e) {
        }

        return this;
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

    public CouponPage openCreateCouponPage() {

        closePopup();

        scrollAndClick(marketingMenu);

        scrollAndClick(couponsMenu);

        scrollAndClick(addNewButton);

        return this;
    }

    public CouponPage enterCouponName(String name) {

        enterText(couponName, name);

        return this;
    }

    public CouponPage enterCouponCode(String code) {

        enterText(couponCode, code);

        return this;
    }

    public CouponPage selectFixedAmount() {

        Select select =
                new Select(
                        driver.findElement(couponType)
                );

        select.selectByValue("F");

        return this;
    }

    public CouponPage enterDiscount(String value) {

        enterText(discount, value);

        return this;
    }

    public CouponPage enterMinimumOrder(String amount) {

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(totalAmount)
        );

        enterText(totalAmount, amount);

        return this;
    }

    public CouponPage enableCustomerLogin() {

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(customerLogin)
        );

        if (!driver.findElement(customerLogin).isSelected()) {
            driver.findElement(customerLogin).click();
        }

        return this;
    }

    public CouponPage disableFreeShipping() {

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(freeShipping)
        );

        if (driver.findElement(freeShipping).isSelected()) {
            driver.findElement(freeShipping).click();
        }

        return this;
    }

    public CouponPage selectCategory(String categoryName) {

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(category)
        );

        enterText(category, categoryName);

        driver.findElement(category)
                .sendKeys(Keys.ENTER);

        return this;
    }

    public CouponPage scrollToTopPage() {

        ScrollUtils.scrollToTop(driver);

        return this;
    }

    public CouponPage clickSave() {

        scrollAndClick(saveButton);

        return this;
    }
}