package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import utils.ConfigReader;
import utils.ScrollUtils;

public class StorefrontPage extends BasePage {

    private By searchField =
            By.name("search");

    private By searchButton =
            By.cssSelector(
                    "button[type='submit'].btn.btn-light.btn-lg"
            );

    private By productLink =
            By.xpath(
                    "//a[normalize-space()='" +
                    ConfigReader.get("productName") +
                    "']"
            );

    private By addToCartButton =
            By.id("button-cart");

    private By sizeError =
            By.cssSelector(
                    "div.invalid-feedback.d-block"
            );

    public StorefrontPage(WebDriver driver) {
        super(driver);
    }

    public StorefrontPage searchProduct(
            String productName) {

        enterText(
                searchField,
                productName
        );

        click(searchButton);

        return this;
    }

    public StorefrontPage openProduct() {

        waitForVisibility(productLink);

        ScrollUtils.scrollDown(
                driver,
                900
        );

        waitForClickable(productLink);

        click(productLink);

        return this;
    }

    public StorefrontPage addProductToCart() {

        waitForVisibility(addToCartButton);

        ScrollUtils.scrollDown(
                driver,
                700
        );

        waitForClickable(addToCartButton);

        click(addToCartButton);

        return this;
    }

    public boolean isSizeValidationDisplayed() {

        return isDisplayed(sizeError);
    }

    public String getSizeValidationMessage() {

        return getText(sizeError);
    }
}