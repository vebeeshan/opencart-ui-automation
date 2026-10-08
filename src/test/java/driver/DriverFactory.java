package driver;

import java.net.MalformedURLException;
import java.net.URI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ConfigReader;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initDriver(String browser) {

        String selectedBrowser = browser;

        if (selectedBrowser == null || selectedBrowser.isBlank()) {
            selectedBrowser = ConfigReader.get("browser");
        }

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getOrDefault("headless", "false")
        );

        boolean gridEnabled = Boolean.parseBoolean(
                ConfigReader.getOrDefault("grid.enabled", "false")
        );

        WebDriver driver;

        if (gridEnabled) {
            driver = createGridDriver(selectedBrowser, headless);
        } else {
            driver = createLocalDriver(selectedBrowser, headless);
        }

        DRIVER.set(driver);

        if (!headless) {
            driver.manage().window().maximize();
        }
    }

    private static WebDriver createLocalDriver(
            String browser,
            boolean headless) {

        if ("firefox".equalsIgnoreCase(browser)) {

            FirefoxOptions options = new FirefoxOptions();

            if (headless) {
                options.addArguments("-headless");
            }

            return new FirefoxDriver(options);
        }

        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return new ChromeDriver(options);
    }

    private static WebDriver createGridDriver(
            String browser,
            boolean headless) {

        String gridUrl = ConfigReader.getOrDefault(
                "grid.url",
                "http://localhost:4444"
        );

        try {

            if ("firefox".equalsIgnoreCase(browser)) {

                FirefoxOptions options = new FirefoxOptions();

                if (headless) {
                    options.addArguments("-headless");
                }

                return new RemoteWebDriver(
                        URI.create(gridUrl).toURL(),
                        options
                );
            }

            ChromeOptions options = new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            return new RemoteWebDriver(
                    URI.create(gridUrl).toURL(),
                    options
            );

        } catch (MalformedURLException e) {

            throw new RuntimeException(
                    "Invalid Selenium Grid URL: " + gridUrl,
                    e
            );
        }
    }

    public static WebDriver getDriver() {

        WebDriver driver = DRIVER.get();

        if (driver == null) {

            throw new IllegalStateException(
                    "WebDriver has not been initialized for this thread."
            );
        }

        return driver;
    }

    public static void quitDriver() {

        WebDriver driver = DRIVER.get();

        if (driver != null) {

            driver.quit();
            DRIVER.remove();
        }
    }
}