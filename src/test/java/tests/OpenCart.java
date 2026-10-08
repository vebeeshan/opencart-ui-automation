package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminDashboardPage;
import pages.AdminLoginPage;
import pages.ProductPage;
import utils.ConfigReader;

public class OpenCart extends BaseTest {

    @Test(groups = "openCartTest")
    public void createProduct() {

        AdminLoginPage loginPage =
                new AdminLoginPage(driver);

        AdminDashboardPage dashboardPage =
                loginPage.login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                );

        ProductPage productPage =
                dashboardPage.goToProducts();

        productPage
                .enterProductName(
                        ConfigReader.get("productName")
                )
                .enterMetaTitle(
                        ConfigReader.get("metaTitle")
                )
                .clickDataTab()
                .enterModel(
                        ConfigReader.get("model")
                )
                .enterPrice(
                        ConfigReader.get("price")
                )
                .enterQuantity(
                        ConfigReader.get("quantity")
                )
                .selectStockStatus(
                        ConfigReader.get("stockStatus")
                )
                .clickOptionTab()
                .searchOption(
                        ConfigReader.get("option")
                )
                .selectSize()
                .addSmallOption()
                .addLargeOption()
                .clickSeoTab()
                .enterSeoKeyword(
                        ConfigReader.get("seoKeyword")
                )
                .clickSave();
    }
}