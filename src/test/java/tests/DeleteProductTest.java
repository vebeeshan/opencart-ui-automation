package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminDashboardPage;
import pages.AdminLoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtils;
import utils.ScrollUtils;

public class DeleteProductTest extends BaseTest {

    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    private void sleep(int seconds) {

        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void deleteProduct() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(15)
                    );

            driver.get(
                    ConfigReader.get("adminUrl")
            );

            System.out.println(
                    "Admin page opened successfully."
            );

            AdminLoginPage loginPage =
                    new AdminLoginPage(driver);

            AdminDashboardPage dashboard =
                    loginPage.login(
                            ConfigReader.get("username"),
                            ConfigReader.get("password")
                    );

            dashboard.clickClose();
            dashboard.clickCatalog();
            dashboard.clickProducts();

            System.out.println(
                    "Products page opened."
            );

            By productNameField =
                    By.id("input-name");

            WebElement productName =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    productNameField
                            )
                    );

            productName.clear();

            productName.sendKeys(
                    ConfigReader.get("productName")
            );

            System.out.println(
                    "Product name entered: " +
                    ConfigReader.get("productName")
            );

            // 1. scroll down, then bring Filter into view
            By filterButton =
                    By.id("button-filter");

            ScrollUtils.scrollToBottom(driver);

            WebElement filter =
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    filterButton
                            )
                    );

            ScrollUtils.scrollToElement(driver, filter);

            // 2. click Filter
            try {

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                filterButton
                        )
                ).click();

            } catch (Exception e) {

                jsClick(driver.findElement(filterButton));
            }

            System.out.println(
                    "Filter button clicked."
            );

            // results reload via ajax
            sleep(3);

            // 3. scroll up
            ScrollUtils.scrollToTop(driver);

            System.out.println(
                    "Scrolled to the top."
            );

            // 4. click the product checkbox
            By productCheckbox =
                    By.xpath(
                            "//tr[.//td[normalize-space()='" +
                            ConfigReader.get("productName") +
                            "']]//input[@name='selected[]']"
                    );

            WebElement checkbox =
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    productCheckbox
                            )
                    );

            ScrollUtils.scrollToElement(driver, checkbox);

            try {

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                productCheckbox
                        )
                ).click();

            } catch (Exception e) {

                jsClick(driver.findElement(productCheckbox));
            }

            System.out.println(
                    "Product checkbox selected."
            );

            // 5. click Delete
            By deleteButton =
                    By.cssSelector(
                            "button.btn.btn-danger[title='Delete']"
                    );

            WebElement delete =
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    deleteButton
                            )
                    );

            ScrollUtils.scrollToElement(driver, delete);

            try {

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                deleteButton
                        )
                ).click();

            } catch (Exception e) {

                jsClick(driver.findElement(deleteButton));
            }

            System.out.println(
                    "Delete button clicked."
            );

            wait.until(
                    ExpectedConditions.alertIsPresent()
            );

            driver.switchTo()
                    .alert()
                    .accept();

            System.out.println(
                    "Delete confirmation accepted."
            );

            System.out.println(
                    "Product deletion completed."
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR OCCURRED: " +
                    e.getMessage()
            );

            ScreenshotUtils.takeScreenshot(
                    driver,
                    "DeleteProductTest_Error"
            );

            throw new RuntimeException(e);
        }
    }
}