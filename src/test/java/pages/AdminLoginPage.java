package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import base.BasePage;


public class AdminLoginPage extends BasePage {

    private By usernameField =
            By.name("username");

    private By passwordField =
            By.name("password");

    private By loginButton =
            By.cssSelector("button[type='submit']");

    // Confirms that login was successful
    private By dashboardHeading =
            By.xpath("//h1[normalize-space()='Dashboard']");

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public AdminDashboardPage login(
            String username,
            String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();

        // Wait until Dashboard appears
        waitForVisibility(dashboardHeading);

        System.out.println(
                "Admin login completed successfully."
        );

        return new AdminDashboardPage(driver);
    }
}