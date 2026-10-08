package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CouponStorefrontPage;
import utils.ConfigReader;

public class CouponStorefrontTest extends BaseTest {

    @Test(
            groups = "couponStorefrontTest",
            dependsOnGroups = "couponTest"
    )
    public void applyCoupon() {

        driver.get(
                ConfigReader.get("storefrontUrl")
        );

        CouponStorefrontPage couponStorefrontPage =
                new CouponStorefrontPage(driver);

        couponStorefrontPage
                .searchProduct(
                        ConfigReader.get("productName")
                )
                .openProduct()
                .selectSmallOption()
                .addProductToCart()
                .openCart()
                .openCouponSection()
                .enterCoupon(
                        ConfigReader.get("couponCode")
                )
                .applyCoupon();

        String couponError =
                couponStorefrontPage.getCouponError();

        System.out.println(
                "Coupon rejected: " +
                couponError
        );

        Assert.assertTrue(
                couponStorefrontPage.isCouponErrorDisplayed(),
                "Expected coupon rejection error was not displayed."
        );

        System.out.println(
                "PASS: Coupon rejection error displayed successfully."
        );
    }
}