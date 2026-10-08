package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import driver.DriverFactory;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(
            @Optional("") String browser) {

        String selectedBrowser = browser;

        if (selectedBrowser == null
                || selectedBrowser.isBlank()) {

            selectedBrowser =
                    ConfigReader.get("browser");
        }

        DriverFactory.initDriver(
                selectedBrowser
        );

        driver = DriverFactory.getDriver();

        driver.get(
                ConfigReader.get("adminUrl")
        );
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}