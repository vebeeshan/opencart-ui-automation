package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import utils.ConfigReader;
import utils.ScrollUtils;

public class ProductPage extends BasePage {

    private By productNameField =
            By.name("product_description[1][name]");

    private By metaTitleField =
            By.name("product_description[1][meta_title]");

    private By dataTab =
            By.cssSelector("a[href='#tab-data']");

    private By modelField =
            By.id("input-model");

    private By priceField =
            By.id("input-price");

    private By quantityField =
            By.id("input-quantity");

    private By stockStatus =
            By.id("input-stock-status");

    private By optionTab =
            By.xpath(
                    "//a[@href='#tab-option' and normalize-space()='Option']"
            );

    private By optionSearch =
            By.id("input-option");

    private By sizeSuggestion =
            By.xpath(
                    "//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='Size']"
            );

    private By addOptionValueButton =
            By.xpath(
                    "//button[contains(@class,'btn-primary') " +
                    "and contains(@title,'Add Option Value')]"
            );

    private By modalOptionValue =
            By.id("input-modal-option-value");

    private By modalQuantity =
            By.id("input-modal-quantity");

    private By modalSubtract =
            By.id("input-modal-subtract");

    private By modalPricePrefix =
            By.cssSelector(
                    "select[name='price_prefix']"
            );

    private By modalPrice =
            By.id("input-modal-price");

    private By saveOptionValue =
            By.id("button-save");

    private By seoTab =
            By.cssSelector("a[href='#tab-seo']");

    private By seoKeyword =
            By.id("input-keyword-0-1");

    private By saveButton =
            By.cssSelector(
                    "button[type='submit'][form='form-product']"
            );

    private By successMessage =
            By.cssSelector(".alert-success");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage enterProductName(String productName) {

        enterText(
                productNameField,
                productName
        );

        return this;
    }

    public ProductPage enterMetaTitle(String metaTitle) {

        enterText(
                metaTitleField,
                metaTitle
        );

        return this;
    }

    public ProductPage clickDataTab() {

        scrollIntoView(dataTab);
        click(dataTab);

        return this;
    }

    public ProductPage enterModel(String model) {

        enterText(
                modelField,
                model
        );

        return this;
    }

    public ProductPage enterPrice(String price) {

        enterText(
                priceField,
                price
        );

        return this;
    }

    public ProductPage enterQuantity(String quantity) {

        enterText(
                quantityField,
                quantity
        );

        return this;
    }

    public ProductPage selectStockStatus(String status) {

        selectByVisibleText(
                stockStatus,
                status
        );

        return this;
    }

    public ProductPage clickOptionTab() {

        WebElement option =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                optionTab
                        )
                );

        ScrollUtils.scrollIntoView(
                driver,
                option
        );

        option.click();

        System.out.println(
                "Option tab opened."
        );

        return this;
    }

    public ProductPage searchOption(String optionName) {

        enterText(
                optionSearch,
                optionName
        );

        System.out.println(
                "Option searched: " +
                optionName
        );

        return this;
    }

    public ProductPage selectSize() {

        WebElement size =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                sizeSuggestion
                        )
                );

        ScrollUtils.scrollIntoView(
                driver,
                size
        );

        size.click();

        System.out.println(
                "Size option selected."
        );

        waitForAddOptionButton();

        return this;
    }

    private WebElement waitForAddOptionButton() {

        WebDriverWait optionWait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        WebElement button =
                optionWait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                addOptionValueButton
                        )
                );

        System.out.println(
                "Add Option Value button is present."
        );

        return button;
    }

    public ProductPage clickAddOptionValue() {

        System.out.println(
                "Searching for Add Option Value button..."
        );

        WebDriverWait optionWait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        WebElement button =
                optionWait.until(
                        ExpectedConditions.elementToBeClickable(
                                addOptionValueButton
                        )
                );

        System.out.println(
                "Add Option Value button found."
        );

        ScrollUtils.scrollIntoView(
                driver,
                button
        );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button
                );

        System.out.println(
                "Add Option Value button clicked."
        );

        optionWait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        modalOptionValue
                )
        );

        System.out.println(
                "Option Value popup opened."
        );

        return this;
    }

    public ProductPage selectModalOptionValue(
            String value) {

        selectByValue(
                modalOptionValue,
                value
        );

        System.out.println(
                "Option value selected: " +
                value
        );

        return this;
    }

    public ProductPage enterModalQuantity(
            String quantity) {

        enterText(
                modalQuantity,
                quantity
        );

        System.out.println(
                "Quantity entered: " +
                quantity
        );

        return this;
    }

    public ProductPage selectSubtractYes() {

        selectByVisibleText(
                modalSubtract,
                "Yes"
        );

        System.out.println(
                "Subtract selected: Yes"
        );

        return this;
    }

    public ProductPage selectPricePrefix(
            String prefix) {

        selectByVisibleText(
                modalPricePrefix,
                prefix
        );

        System.out.println(
                "Price prefix selected: " +
                prefix
        );

        return this;
    }

    public ProductPage enterModalPrice(
            String price) {

        enterText(
                modalPrice,
                price
        );

        System.out.println(
                "Option price entered: " +
                price
        );

        return this;
    }

    public ProductPage clickSaveOptionValue() {

        WebElement save =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                saveOptionValue
                        )
                );

        save.click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        modalOptionValue
                )
        );

        System.out.println(
                "Option value saved successfully."
        );

        return this;
    }

    public ProductPage addSmallOption() {

        System.out.println(
                "========== ADDING SMALL =========="
        );

        clickAddOptionValue();

        selectModalOptionValue("46");

        enterModalQuantity("1");

        selectSubtractYes();

        selectPricePrefix("+");

        enterModalPrice("10");

        clickSaveOptionValue();

        System.out.println(
                "Small option added successfully."
        );

        return this;
    }

    public ProductPage addLargeOption() {

        System.out.println(
                "========== ADDING LARGE =========="
        );

        clickAddOptionValue();

        selectModalOptionValue("48");

        enterModalQuantity("2");

        selectSubtractYes();

        selectPricePrefix("-");

        enterModalPrice("10");

        clickSaveOptionValue();

        System.out.println(
                "Large option added successfully."
        );

        return this;
    }

    public ProductPage clickSeoTab() {

        WebElement element =
                waitForVisibility(seoTab);

        ScrollUtils.scrollIntoView(
                driver,
                element
        );

        element.click();

        System.out.println(
                "SEO tab opened."
        );

        return this;
    }

    public ProductPage enterSeoKeyword(
            String keyword) {

        enterText(
                seoKeyword,
                keyword
        );

        return this;
    }

    public ProductPage clickSave() {

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                saveButton
                        )
                );

        ScrollUtils.scrollIntoView(
                driver,
                element
        );

        element.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        successMessage
                )
        );

        String message =
                getText(successMessage);

        System.out.println(
                "Product Created Successfully!"
        );

        System.out.println(
                "Message: " +
                message
        );

        return this;
    }

    public ProductPage createProductWithOptions() {

        enterProductName(
                ConfigReader.get("productName")
        );

        enterMetaTitle(
                ConfigReader.get("metaTitle")
        );

        clickDataTab();

        enterModel(
                ConfigReader.get("model")
        );

        enterPrice(
                ConfigReader.get("price")
        );

        enterQuantity(
                ConfigReader.get("quantity")
        );

        selectStockStatus(
                ConfigReader.get("stockStatus")
        );

        clickOptionTab();

        searchOption(
                ConfigReader.get("option")
        );

        selectSize();

        addSmallOption();

        addLargeOption();

        clickSeoTab();

        enterSeoKeyword(
                ConfigReader.get("seoKeyword")
        );

        clickSave();

        return this;
    }
}