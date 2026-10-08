package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CODOrderPage;
import utils.ConfigReader;
import utils.ScreenshotUtils;

public class CODOrdertest extends BaseTest {

    @Test(
            groups = "codOrderTest",
            dependsOnGroups = "couponStorefrontTest"
    )
    public void placeCashOnDeliveryOrder() {

        try {

            driver.get(
                    ConfigReader.get("storefrontUrl")
            );

            System.out.println(
                    "Storefront opened."
            );

            CODOrderPage codOrderPage =
                    new CODOrderPage(driver);

            codOrderPage
                    .searchProduct(
                            ConfigReader.get("productName")
                    )
                    .openProduct()
                    .selectSmallOption()
                    .addProductToCart()
                    .openCart();

            String capturedProduct =
                    codOrderPage.getProductNameFromCart();

            String capturedQuantity =
                    codOrderPage.getQuantityFromCart();

            String capturedUnitPrice =
                    codOrderPage.getUnitPriceFromCart();

            String capturedProductTotal =
                    codOrderPage.getProductTotalFromCart();

            String capturedSubTotal =
                    codOrderPage.getSubTotalFromCart();

            String capturedTotal =
                    codOrderPage.getTotalFromCart();

            System.out.println(
                    "================================="
            );

            System.out.println(
                    "CAPTURED CART DETAILS"
            );

            System.out.println(
                    "================================="
            );

            System.out.println(
                    "Product       : " +
                    capturedProduct
            );

            System.out.println(
                    "Quantity      : " +
                    capturedQuantity
            );

            System.out.println(
                    "Unit Price    : " +
                    capturedUnitPrice
            );

            System.out.println(
                    "Product Total : " +
                    capturedProductTotal
            );

            System.out.println(
                    "Sub-Total     : " +
                    capturedSubTotal
            );

            System.out.println(
                    "Total         : " +
                    capturedTotal
            );

            System.out.println(
                    "================================="
            );

            codOrderPage
                    .openCheckout()
                    .enterCustomerDetails()
                    .selectCountry()
                    .selectZone();

            String selectedZone =
                    codOrderPage.getSelectedZone();

            Assert.assertEquals(
                    selectedZone,
                    ConfigReader.get("zone"),
                    "Tamil Nadu was not selected."
            );

            System.out.println(
                    ConfigReader.get("zone") +
                    " selected successfully."
            );

            codOrderPage
                    .enterPassword()
                    .selectNewsletter()
                    .selectAgree();

            Assert.assertTrue(
                    codOrderPage.isAgreeSelected(),
                    "Agree checkbox was not selected."
            );

            System.out.println(
                    "Agree checkbox selected."
            );

            codOrderPage
                    .continueRegistration()
                    .openShippingMethod()
                    .continueShipping()
                    .openPaymentMethod()
                    .continuePayment()
                    .confirmOrder();

            String successMessage =
                    codOrderPage.getOrderSuccessMessage();

            System.out.println(
                    "First line: " +
                    successMessage
            );

            Assert.assertEquals(
                    successMessage,
                    "Your order has been placed!",
                    "Order success message is incorrect."
            );

            System.out.println(
                    "PASS: Order placed successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "================================="
            );

            System.out.println(
                    "COD ORDER TEST FAILED"
            );

            System.out.println(
                    "================================="
            );

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );

            ScreenshotUtils.takeScreenshot(
                    driver,
                    "CODOrderTest_FAILED"
            );

            System.out.println(
                    "Failure screenshot captured."
            );

            // Important:
            // Re-throw the exception so TestNG marks this test as FAILED.
            throw e;
        }
    }
}