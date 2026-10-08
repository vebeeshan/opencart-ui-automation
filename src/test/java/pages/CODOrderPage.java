package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import utils.ConfigReader;
import utils.ScrollUtils;

public class CODOrderPage extends BasePage {

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

    private By cartDropdown =
            By.xpath(
                    "//button[@data-bs-toggle='dropdown']" +
                    "[contains(normalize-space(.),'item(s)')]"
            );

    private By viewCart =
            By.xpath(
                    "//a[contains(@href,'route=checkout/cart')][.//strong]"
            );

    private By dropdownCheckout =
            By.xpath(
                    "//ul[contains(@class,'dropdown-menu')]" +
                    "//a[contains(@href,'route=checkout/checkout')]"
            );

    private By cartTable =
            By.cssSelector("#output-cart table");

    private By checkout =
            By.xpath(
                    "//a[contains(@href,'route=checkout/checkout')]"
            );

    private By firstName =
            By.id("input-firstname");

    private By lastName =
            By.id("input-lastname");

    private By email =
            By.id("input-email");

    private By address =
            By.id("input-shipping-address-1");

    private By city =
            By.id("input-shipping-city");

    private By postcode =
            By.id("input-shipping-postcode");

    private By country =
            By.id("input-shipping-country");

    private By zone =
            By.id("input-shipping-zone");

    private By password =
            By.id("input-password");

    private By newsletter =
            By.id("input-newsletter");

    private By agree =
            By.id("input-register-agree");

    private By registerContinue =
            By.id("button-register");

    // NEW: shipping address Continue
    private By shippingAddressContinue =
            By.id("button-shipping-address");

    private By shippingChoose =
            By.id("button-shipping-methods");

    private By shippingFlatRate =
            By.id("input-shipping-method-flat-flat");

    private By shippingContinue =
            By.id("button-shipping-method");

    private By paymentChoose =
            By.id("button-payment-methods");

    private By paymentContinue =
            By.id("button-payment-method");

    private By confirmOrder =
            By.id("button-confirm");

    private By orderSuccessHeading =
            By.cssSelector("#content h1");

    private By shippingModal =
            By.id("modal-shipping");

    private By paymentModal =
            By.id("modal-payment");

    private By modalBackdrop =
            By.cssSelector(".modal-backdrop");

    private By modalClose =
            By.cssSelector(
                    ".modal.show .btn-close[data-bs-dismiss='modal']"
            );

    private By alertClose =
            By.cssSelector("#alert .btn-close");

    private By alertMessage =
            By.cssSelector("#alert .alert");

    public CODOrderPage(WebDriver driver) {
        super(driver);
    }

    private void sleep(int seconds) {

        try {

            Thread.sleep(seconds * 1000L);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    private void closeAlerts() {

        try {

            for (WebElement btn :
                    driver.findElements(alertClose)) {

                if (!btn.isDisplayed()) {
                    continue;
                }

                try {

                    btn.click();

                } catch (Exception e) {

                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "arguments[0].click();",
                                    btn
                            );
                }
            }

            wait.until(
                    d -> d.findElements(
                            alertMessage
                    ).isEmpty()
            );

        } catch (Exception ignored) {
            // alert already gone
        }
    }

    // not used in the shipping/payment flow any more
    private void closeModal() {

        try {

            WebElement x =
                    wait.until(
                            ExpectedConditions
                                    .presenceOfElementLocated(
                                            modalClose
                                    )
                    );

            try {

                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        modalClose
                                )
                ).click();

            } catch (Exception e) {

                ((JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].click();",
                                x
                        );
            }

        } catch (Exception ignored) {
            // modal already closed
        }
    }

    // not used in the shipping/payment flow any more
    private void closeModalIfOpen() {

        List<WebElement> buttons =
                driver.findElements(modalClose);

        if (buttons.isEmpty()) {
            return;
        }

        try {

            buttons.get(0).click();

        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            buttons.get(0)
                    );
        }
    }

    private void scrollToMiddle() {

        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo({" +
                "top: document.documentElement.scrollHeight / 2," +
                "left: 0," +
                "behavior: 'instant'});"
        );
    }

    private void scrollAndClick(By locator) {

        closeAlerts();

        waitForVisibility(locator);

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(locator)
        );

        waitForClickable(locator);

        click(locator);
    }

    // waits up to 30s for the button, scrolls to it,
    // clicks (JS click as fallback)
    private void clickReliably(By locator) {

        WebElement el =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                ).until(
                        ExpectedConditions
                                .presenceOfElementLocated(
                                        locator
                                )
                );

        ScrollUtils.scrollToElement(driver, el);

        try {

            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(30)
            ).until(
                    ExpectedConditions
                            .elementToBeClickable(
                                    locator
                            )
            ).click();

        } catch (Exception e) {

            jsClick(locator);
        }
    }

    private void jsClick(By locator) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                driver.findElement(locator)
        );
    }

    // waits only, never clicks the X
    private void waitForModalToClose(By modal) {

        try {

            wait.until(
                    ExpectedConditions
                            .invisibilityOfElementLocated(
                                    modal
                            )
            );

            wait.until(
                    ExpectedConditions
                            .invisibilityOfElementLocated(
                                    modalBackdrop
                            )
            );

        } catch (Exception ignored) {
            // modal still open, next step handles it
        }
    }

    // all zone option texts joined, used to detect the ajax reload
    private String zoneSignature() {

        return new Select(driver.findElement(zone))
                .getOptions()
                .stream()
                .map(o -> o.getText().trim())
                .collect(Collectors.joining("|"));
    }

    public CODOrderPage searchProduct(
            String productName) {

        enterText(
                searchBox,
                productName
        );

        click(searchButton);

        return this;
    }

    public CODOrderPage openProduct() {

        scrollAndClick(productLink);

        return this;
    }

    public CODOrderPage selectSmallOption() {

        scrollAndClick(smallOption);

        wait.until(
                d -> d.findElement(
                        smallOption
                ).isSelected()
        );

        return this;
    }

    public CODOrderPage addProductToCart() {

        scrollAndClick(addToCart);

        waitForVisibility(successMessage);

        sleep(5);

        closeAlerts();

        ScrollUtils.scrollToTop(driver);

        return this;
    }

    public CODOrderPage openCartAndCheckout() {

        closeAlerts();

        ScrollUtils.scrollToTop(driver);

        wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(
                                cartDropdown
                        )
        );

        try {

            wait.until(
                    ExpectedConditions
                            .elementToBeClickable(
                                    cartDropdown
                            )
            ).click();

        } catch (Exception e) {

            jsClick(cartDropdown);
        }

        By target =
                driver.findElements(
                        dropdownCheckout
                ).isEmpty()
                        ? checkout
                        : dropdownCheckout;

        wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(
                                target
                        )
        );

        try {

            wait.until(
                    ExpectedConditions
                            .elementToBeClickable(
                                    target
                            )
            ).click();

        } catch (Exception e) {

            jsClick(target);
        }

        waitForUrlContains(
                "checkout/checkout"
        );

        waitForVisibility(firstName);

        return this;
    }

    public CODOrderPage openCart() {

        closeAlerts();

        ScrollUtils.scrollToTop(driver);

        wait.until(
                ExpectedConditions
                        .presenceOfElementLocated(
                                viewCart
                        )
        );

        String href =
                driver.findElement(
                        viewCart
                ).getAttribute("href");

        try {

            wait.until(
                    ExpectedConditions
                            .elementToBeClickable(
                                    cartDropdown
                            )
            ).click();

            wait.until(
                    ExpectedConditions
                            .elementToBeClickable(
                                    viewCart
                            )
            ).click();

        } catch (Exception e) {

            try {

                jsClick(viewCart);

            } catch (Exception ex) {

                driver.get(href);
            }
        }

        waitForUrlContains(
                "checkout/cart"
        );

        waitForVisibility(cartTable);

        return this;
    }

    public String getProductNameFromCart() {

        WebElement table =
                waitForVisibility(cartTable);

        return table.findElement(
                By.cssSelector(
                        "tbody tr:first-child td:nth-child(2)"
                )
        ).getText().trim();
    }

    public String getQuantityFromCart() {

        WebElement table =
                waitForVisibility(cartTable);

        return table.findElement(
                By.cssSelector(
                        "tbody tr:first-child td:nth-child(3) input"
                )
        ).getAttribute("value").trim();
    }

    public String getUnitPriceFromCart() {

        WebElement table =
                waitForVisibility(cartTable);

        return table.findElement(
                By.cssSelector(
                        "tbody tr:first-child td:nth-child(4)"
                )
        ).getText().trim();
    }

    public String getProductTotalFromCart() {

        WebElement table =
                waitForVisibility(cartTable);

        return table.findElement(
                By.cssSelector(
                        "tbody tr:first-child td:nth-child(5)"
                )
        ).getText().trim();
    }

    public String getSubTotalFromCart() {

        WebElement table =
                waitForVisibility(cartTable);

        return table.findElement(
                By.xpath(
                        ".//td[strong[normalize-space()='Sub-Total']]" +
                        "/following-sibling::td[1]"
                )
        ).getText().trim();
    }

    public String getTotalFromCart() {

        WebElement table =
                waitForVisibility(cartTable);

        return table.findElement(
                By.xpath(
                        ".//td[strong[normalize-space()='Total']]" +
                        "/following-sibling::td[1]"
                )
        ).getText().trim();
    }

    public CODOrderPage openCheckout() {

        scrollAndClick(checkout);

        waitForUrlContains(
                "checkout/checkout"
        );

        waitForVisibility(firstName);

        return this;
    }

    public CODOrderPage enterCustomerDetails() {

        enterText(
                firstName,
                ConfigReader.get("firstName")
        );

        enterText(
                lastName,
                ConfigReader.get("lastName")
        );

        enterText(
                email,
                ConfigReader.get("email")
        );

        enterText(
                address,
                ConfigReader.get("address")
        );

        enterText(
                city,
                ConfigReader.get("city")
        );

        enterText(
                postcode,
                ConfigReader.get("postcode")
        );

        return this;
    }

    public CODOrderPage selectCountry() {

        String target =
                ConfigReader.get("country");

        String current =
                new Select(
                        driver.findElement(country)
                ).getFirstSelectedOption()
                        .getText()
                        .trim();

        // already selected, no ajax reload will happen
        if (current.equals(target)) {
            return this;
        }

        String before = zoneSignature();

        selectByVisibleText(
                country,
                target
        );

        // wait for the zone list to change and be enabled
        wait.until(
                d -> {

                    try {

                        WebElement z =
                                d.findElement(zone);

                        return z.isEnabled()
                                && new Select(z)
                                        .getOptions()
                                        .size() > 1
                                && !zoneSignature()
                                        .equals(before);

                    } catch (StaleElementReferenceException e) {

                        return false;
                    }
                }
        );

        return this;
    }

    public CODOrderPage selectZone() {

        for (int i = 0; i < 3; i++) {

            try {

                wait.until(
                        d -> {

                            try {

                                WebElement z =
                                        d.findElement(zone);

                                return z.isEnabled()
                                        && new Select(z)
                                                .getOptions()
                                                .size() > 1;

                            } catch (StaleElementReferenceException e) {

                                return false;
                            }
                        }
                );

                selectByVisibleText(
                        zone,
                        ConfigReader.get("zone")
                );

                return this;

            } catch (UnsupportedOperationException
                     | StaleElementReferenceException e) {

                // reload still running, try again
                sleep(2);
            }
        }

        selectByVisibleText(
                zone,
                ConfigReader.get("zone")
        );

        return this;
    }

    public String getSelectedZone() {

        return new Select(
                waitForVisibility(zone)
        )
                .getFirstSelectedOption()
                .getText()
                .trim();
    }

    public CODOrderPage enterPassword() {

        enterText(
                password,
                ConfigReader.get("password1")
        );

        return this;
    }

    public CODOrderPage selectNewsletter() {

        closeAlerts();

        waitForVisibility(newsletter);

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(newsletter)
        );

        WebElement checkbox =
                waitForClickable(newsletter);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        return this;
    }

    public CODOrderPage selectAgree() {

        closeAlerts();

        waitForVisibility(agree);

        ScrollUtils.scrollToElement(
                driver,
                driver.findElement(agree)
        );

        WebElement checkbox =
                waitForClickable(agree);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        return this;
    }

    public boolean isAgreeSelected() {

        return waitForVisibility(
                agree
        ).isSelected();
    }

    // Continue -> shipping address Continue (if shown) -> scroll up
    public CODOrderPage continueRegistration() {

        scrollAndClick(
                registerContinue
        );

        sleep(3);

        if (!driver.findElements(
                shippingAddressContinue
        ).isEmpty()
                && driver.findElement(
                        shippingAddressContinue
                ).isDisplayed()) {

            clickReliably(
                    shippingAddressContinue
            );

            sleep(3);
        }

        ScrollUtils.scrollToTop(driver);

        return this;
    }

    // payment Choose -> shipping Choose -> wait 10 sec -> Flat Rate
    public CODOrderPage openShippingMethod() {

        clickReliably(paymentChoose);

        sleep(2);

        clickReliably(shippingChoose);

        sleep(10);

        WebElement flatRate =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(15)
                ).until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        shippingFlatRate
                                )
                );

        if (!flatRate.isSelected()) {
            flatRate.click();
        }

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(
                d -> d.findElement(
                        shippingFlatRate
                ).isSelected()
        );

        System.out.println(
                "Flat Rate shipping selected."
        );

        return this;
    }

    // Continue -> wait 12 sec for load
    public CODOrderPage continueShipping() {

        clickReliably(shippingContinue);

        sleep(12);

        waitForModalToClose(shippingModal);

        System.out.println(
                "Shipping method continued."
        );

        return this;
    }

    // payment Choose was already clicked; click again only if needed
    public CODOrderPage openPaymentMethod() {

        if (driver.findElements(paymentContinue).isEmpty()
                || !driver.findElement(
                        paymentContinue
                ).isDisplayed()) {

            clickReliably(paymentChoose);
        }

        new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        ).until(
                ExpectedConditions
                        .elementToBeClickable(
                                paymentContinue
                        )
        );

        return this;
    }

    // Continue -> wait 12 sec for load
    public CODOrderPage continuePayment() {

        clickReliably(paymentContinue);

        sleep(12);

        waitForModalToClose(paymentModal);

        return this;
    }

    // scroll to middle -> Confirm Order -> wait 5 sec
    public CODOrderPage confirmOrder() {

        scrollToMiddle();

        clickReliably(confirmOrder);

        sleep(5);

        waitForUrlContains(
                "checkout/success"
        );

        return this;
    }

    public CODOrderPage completeShippingAndPayment() {

        openShippingMethod();

        continueShipping();

        openPaymentMethod();

        continuePayment();

        confirmOrder();

        return this;
    }

    public String getOrderSuccessMessage() {

        return getText(
                orderSuccessHeading
        ).trim();
    }
}