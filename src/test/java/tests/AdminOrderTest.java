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
import utils.ConfigReader;

public class AdminOrderTest extends BaseTest {

    @Test(
        groups = "adminOrderTest",
        dependsOnGroups = "codOrderTest"

    )
    public void verifyAdminOrder() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        // =========================================================
        // ADMIN LOGIN
        // =========================================================

        driver.get(ConfigReader.get("adminUrl"));

        Thread.sleep(2000);

        System.out.println("Admin login page opened.");

        // Username
        By username =
                By.name("username");

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        username
                )
        );

        usernameField.clear();

        usernameField.sendKeys(
                ConfigReader.get("username")
        );

        System.out.println(
                "Admin username entered."
        );

        // Password
        By password =
                By.name("password");

        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        password
                )
        );

        passwordField.clear();

        passwordField.sendKeys(
                ConfigReader.get("password")
        );

        System.out.println(
                "Admin password entered."
        );

        // Login
        By loginButton =
                By.cssSelector("button[type='submit']");

        WebElement login = wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        );

        login.click();

        System.out.println(
                "Admin login clicked."
        );

        Thread.sleep(3000);

        // =========================================================
        // ADMIN DASHBOARD
        // =========================================================

        AdminDashboardPage adminDashboard =
                new AdminDashboardPage(driver);

        adminDashboard.clickClose();

        System.out.println(
                "Dashboard close button clicked."
        );

        Thread.sleep(1000);

        // =========================================================
        // SMALL SCROLL
        // =========================================================

        js.executeScript(
                "window.scrollBy(0, 300);"
        );

        Thread.sleep(1000);

        // =========================================================
        // CLICK SALES
        // =========================================================

        By sales = By.xpath(
                "//a[@href='#collapse-5' " +
                "and contains(normalize-space(),'Sales')]"
        );

        WebElement salesMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        sales
                )
        );

        salesMenu.click();

        System.out.println(
                "Sales menu clicked."
        );

        Thread.sleep(1000);

        // =========================================================
        // CLICK ORDERS
        // =========================================================

        By orders = By.xpath(
                "//a[contains(@href,'route=sale/order') " +
                "and normalize-space()='Orders']"
        );

        WebElement ordersMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        orders
                )
        );

        ordersMenu.click();

        System.out.println(
                "Orders clicked."
        );

        Thread.sleep(3000);

        // =========================================================
        // CLICK VIEW ORDER
        // =========================================================

        By viewOrder = By.xpath(
                "//a[contains(@href,'route=sale/order.info') " +
                "and contains(@class,'btn-primary') " +
                "and @title='View']"
        );

        WebElement viewOrderButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        viewOrder
                )
        );

        viewOrderButton.click();

        System.out.println(
                "Order opened."
        );

        // Wait for order page to load
        Thread.sleep(7000);

        System.out.println(
                "Order page loaded."
        );

        // =========================================================
        // CAPTURE ADMIN ORDER PRODUCT ROW
        // =========================================================

        By adminProductRow =
                By.cssSelector("tbody#order-product tr");

        WebElement productRow = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        adminProductRow
                )
        );

        System.out.println(
                "Admin order product row loaded."
        );

        // =========================================================
        // CAPTURE PRODUCT
        // =========================================================

        String adminProductName = productRow.findElement(
                By.cssSelector("td:nth-child(1) a")
        ).getText().trim();

        System.out.println(
                "Admin Product: " +
                adminProductName
        );

        // =========================================================
        // CAPTURE MODEL
        // =========================================================

        String adminModel = productRow.findElement(
                By.xpath(
                        ".//small[contains(normalize-space(),'Model:')]"
                )
        ).getText().trim();

        System.out.println(
                "Admin Model: " +
                adminModel
        );

        // =========================================================
        // CAPTURE SIZE
        // =========================================================

        String adminSize = productRow.findElement(
                By.xpath(
                        ".//small[contains(normalize-space(),'Size:')]"
                )
        ).getText().trim();

        System.out.println(
                "Admin Size: " +
                adminSize
        );

        // =========================================================
        // CAPTURE QUANTITY
        // =========================================================

        String adminQuantityValue = productRow.findElement(
                By.cssSelector("td:nth-child(2)")
        ).getText().trim();

        System.out.println(
                "Admin Quantity: " +
                adminQuantityValue
        );

        // =========================================================
        // CAPTURE UNIT PRICE
        // =========================================================

        String adminUnitPriceValue = productRow.findElement(
                By.cssSelector("td:nth-child(3)")
        ).getText().trim();

        System.out.println(
                "Admin Unit Price: " +
                adminUnitPriceValue
        );

        // =========================================================
        // CAPTURE TOTAL
        // =========================================================

        String adminTotalValue = productRow.findElement(
                By.cssSelector("td:nth-child(4)")
        ).getText().trim();

        System.out.println(
                "Admin Total: " +
                adminTotalValue
        );

        // =========================================================
        // DISPLAY CAPTURED DATA
        // =========================================================

        System.out.println(
                "========================================"
        );

        System.out.println(
                "ADMIN ORDER DATA"
        );

        System.out.println(
                "Product     : " +
                adminProductName
        );

        System.out.println(
                "Model       : " +
                adminModel
        );

        System.out.println(
                "Size        : " +
                adminSize
        );

        System.out.println(
                "Quantity    : " +
                adminQuantityValue
        );

        System.out.println(
                "Unit Price  : " +
                adminUnitPriceValue
        );

        System.out.println(
                "Total       : " +
                adminTotalValue
        );

        System.out.println(
                "========================================"
        );

        // =========================================================
        // CHECK PRODUCT
        // =========================================================

        String expectedProduct =
                ConfigReader.get("productName");

        if (adminProductName.equals(expectedProduct)) {

            System.out.println(
                    "PASS - Product matches: " +
                    adminProductName
            );

        } else {

            System.out.println(
                    "WARNING - Product mismatch. Expected: " +
                    expectedProduct +
                    " | Actual: " +
                    adminProductName
            );
        }

        // =========================================================
        // CHECK MODEL
        // =========================================================

        String expectedModel =
                "- Model: " + ConfigReader.get("model");

        if (adminModel.equals(expectedModel)) {

            System.out.println(
                    "PASS - Model matches: " +
                    adminModel
            );

        } else {

            System.out.println(
                    "WARNING - Model mismatch. Expected: " +
                    expectedModel +
                    " | Actual: " +
                    adminModel
            );
        }

        // =========================================================
        // CHECK SIZE
        // =========================================================

        String expectedSize =
                "- Size: " + ConfigReader.get("smallOption");

        if (adminSize.equals(expectedSize)) {

            System.out.println(
                    "PASS - Size matches: " +
                    adminSize
            );

        } else {

            System.out.println(
                    "WARNING - Size mismatch. Expected: " +
                    expectedSize +
                    " | Actual: " +
                    adminSize
            );
        }

        // =========================================================
        // CHECK QUANTITY
        // =========================================================

        String expectedQuantity = "1";

        if (adminQuantityValue.equals(expectedQuantity)) {

            System.out.println(
                    "PASS - Quantity matches: " +
                    adminQuantityValue
            );

        } else {

            System.out.println(
                    "WARNING - Quantity mismatch. Expected: " +
                    expectedQuantity +
                    " | Actual: " +
                    adminQuantityValue
            );
        }

        // =========================================================
        // CHECK UNIT PRICE
        // =========================================================

        String expectedUnitPrice = "$90.00";

        if (adminUnitPriceValue.equals(expectedUnitPrice)) {

            System.out.println(
                    "PASS - Unit Price matches: " +
                    adminUnitPriceValue
            );

        } else {

            System.out.println(
                    "WARNING - Unit Price mismatch. Expected: " +
                    expectedUnitPrice +
                    " | Actual: " +
                    adminUnitPriceValue
            );
        }

        // =========================================================
        // CHECK TOTAL
        // =========================================================

        String expectedTotal = "$90.00";

        if (adminTotalValue.equals(expectedTotal)) {

            System.out.println(
                    "PASS - Total matches: " +
                    adminTotalValue
            );

        } else {

            System.out.println(
                    "WARNING - Total mismatch. Expected: " +
                    expectedTotal +
                    " | Actual: " +
                    adminTotalValue
            );
        }

        // =========================================================
        // VERIFICATION COMPLETED
        // =========================================================

        System.out.println(
                "========================================"
        );

        System.out.println(
                "ADMIN ORDER VERIFICATION COMPLETED"
        );

        System.out.println(
                "Mismatches."
        );

        System.out.println(
                "."
        );

        System.out.println(
                "========================================"
        );

        // =========================================================
        // MEDIUM SCROLL
        // =========================================================

        js.executeScript(
                "window.scrollBy(0, 500);"
        );

        Thread.sleep(1000);

        System.out.println(
                "Medium scroll completed."
        );

        // =========================================================
        // CLICK CONFIRM
        // =========================================================

        By confirmOrder =
                By.id("button-confirm");

        WebElement confirmButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmOrder
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                confirmButton
        );

        Thread.sleep(500);

        js.executeScript(
                "arguments[0].click();",
                confirmButton
        );

        System.out.println(
                "Confirm button clicked."
        );

        Thread.sleep(2000);

        System.out.println(
                "Admin order confirmed."
        );
    }
}