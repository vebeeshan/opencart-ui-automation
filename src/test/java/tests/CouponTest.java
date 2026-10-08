package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminLoginPage;
import pages.CouponPage;
import utils.ConfigReader;

public class CouponTest extends BaseTest {

    @Test(
            groups = "couponTest",
            dependsOnGroups = "storefrontTest"
    )
    public void createCoupon() {

        driver.get(
                ConfigReader.get("adminUrl")
        );

        System.out.println(
                "Admin page opened."
        );

        AdminLoginPage loginPage =
                new AdminLoginPage(driver);

        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        System.out.println(
                "Admin login completed."
        );

        CouponPage couponPage =
                new CouponPage(driver);

        couponPage
                .openCreateCouponPage()
                .enterCouponName(
                        ConfigReader.get("couponName")
                )
                .enterCouponCode(
                        ConfigReader.get("couponCode")
                )
                .selectFixedAmount()
                .enterDiscount(
                        ConfigReader.get("couponDiscount")
                )
                .enterMinimumOrder(
                        ConfigReader.get("couponMinimumOrder")
                )
                .enableCustomerLogin()
                .disableFreeShipping()
                .selectCategory(
                        ConfigReader.get("couponCategory")
                )
                .scrollToTopPage()
                .clickSave();

        System.out.println(
                "Coupon created successfully."
        );
    }
}