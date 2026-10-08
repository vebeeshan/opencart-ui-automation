package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class AdminDashboardPage extends BasePage {

    private By closeButton =
            By.cssSelector(".btn-close");

    private By catalogMenu =
            By.cssSelector("a[href='#collapse-1']");

    private By productsMenu =
            By.cssSelector(
                    "#collapse-1 a[href*='route=catalog/product']"
            );

    private By addNewButton =
            By.cssSelector(
                    "a.btn.btn-primary[title='Add New']"
            );

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
    }

    public AdminDashboardPage clickClose() {

        click(closeButton);

        return this;
    }

    public AdminDashboardPage clickCatalog() {

        click(catalogMenu);

        return this;
    }

    public AdminDashboardPage clickProducts() {

        click(productsMenu);

        return this;
    }

    public ProductPage clickAddNew() {

        click(addNewButton);

        return new ProductPage(driver);
    }

    public ProductPage goToProducts() {

        return clickClose()
                .clickCatalog()
                .clickProducts()
                .clickAddNew();
    }
}