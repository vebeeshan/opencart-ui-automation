package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollUtils {

    private static final int STEP = 500;
    private static final int MAX_ATTEMPTS = 60;
    private static final int TOP_MARGIN = 120;    // sticky header space
    private static final int BOTTOM_MARGIN = 20;

    private ScrollUtils() {
    }

    private static JavascriptExecutor js(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    private static double scrollY(WebDriver driver) {
        return ((Number) js(driver).executeScript(
                "return window.pageYOffset || " +
                "document.documentElement.scrollTop || " +
                "document.body.scrollTop || 0;"
        )).doubleValue();
    }

    private static void scrollBy(WebDriver driver, int pixels) {
        js(driver).executeScript(
                "window.scrollBy({top: arguments[0], left: 0, behavior: 'instant'});",
                pixels
        );
    }

    public static void scrollDown(WebDriver driver, int pixels) {
        scrollBy(driver, pixels);
    }

    public static void scrollUp(WebDriver driver, int pixels) {
        scrollBy(driver, -pixels);
    }

    /** -1 = element above view, 1 = below view, 0 = visible */
    private static int direction(WebDriver driver, WebElement element) {
        return ((Number) js(driver).executeScript(
                "var r = arguments[0].getBoundingClientRect();" +
                "if (r.top < arguments[1]) return -1;" +
                "if (r.bottom > window.innerHeight - arguments[2]) return 1;" +
                "return 0;",
                element, TOP_MARGIN, BOTTOM_MARGIN
        )).intValue();
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {

        // 1. Browser scrolls the page AND inner containers (sidebar, modals)
        js(driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', behavior:'instant'});",
                element
        );

        // 2. Safety net: 500px steps if still hidden (e.g. under sticky header)
        for (int i = 0; i < MAX_ATTEMPTS; i++) {

            int dir = direction(driver, element);

            if (dir == 0) {
                return;
            }

            double before = scrollY(driver);

            scrollBy(driver, dir * STEP);

            if (scrollY(driver) == before) {
                return; // cannot scroll further
            }
        }
    }

    public static void scrollToTop(WebDriver driver) {

        for (int i = 0; i < MAX_ATTEMPTS; i++) {

            double before = scrollY(driver);

            if (before <= 0) {
                return;
            }

            scrollBy(driver, -STEP);

            if (scrollY(driver) == before) {
                return;
            }
        }
    }

    public static void scrollToBottom(WebDriver driver) {

        for (int i = 0; i < MAX_ATTEMPTS; i++) {

            boolean atBottom = (Boolean) js(driver).executeScript(
                    "return (window.innerHeight + window.pageYOffset) >= " +
                    "document.documentElement.scrollHeight - 5;"
            );

            if (atBottom) {
                return;
            }

            double before = scrollY(driver);

            scrollBy(driver, STEP);

            if (scrollY(driver) == before) {
                return;
            }
        }
    }

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        scrollToElement(driver, element);
    }

    public static void scrollElementToTop(WebDriver driver, WebElement element) {
        js(driver).executeScript(
                "arguments[0].scrollIntoView({block:'start', behavior:'instant'});",
                element
        );
    }

    public static void scrollElementToBottom(WebDriver driver, WebElement element) {
        js(driver).executeScript(
                "arguments[0].scrollIntoView({block:'end', behavior:'instant'});",
                element
        );
    }
}