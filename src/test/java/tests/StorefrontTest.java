package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.StorefrontPage;
import utils.ConfigReader;
import utils.ScreenshotUtils;

public class StorefrontTest extends BaseTest {

    @Test(
            groups = "storefrontTest",
            dependsOnGroups = "openCartTest"
    )
    public void openProductStorefront() {

        try {

            driver.get(
                    ConfigReader.get("storefrontUrl")
            );

            System.out.println(
                    "Storefront opened successfully."
            );

            StorefrontPage storefrontPage =
                    new StorefrontPage(driver);

            storefrontPage
                    .searchProduct(
                            ConfigReader.get("productName")
                    )
                    .openProduct()
                    .addProductToCart();

            System.out.println(
                    "Add to Cart clicked successfully."
            );

            if (storefrontPage.isSizeValidationDisplayed()) {

                System.out.println(
                        "Validation Error: " +
                        storefrontPage.getSizeValidationMessage()
                );

            } else {

                System.out.println(
                        "No size validation error displayed."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR OCCURRED: " +
                    e.getMessage()
            );

            ScreenshotUtils.takeScreenshot(
                    driver,
                    "StorefrontTest_Error"
            );

            throw new RuntimeException(e);
        }
    }
}