package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtils {

    public static void takeScreenshot(
            WebDriver driver,
            String fileName) {

        TakesScreenshot screenshot =
                (TakesScreenshot) driver;

        File source =
                screenshot.getScreenshotAs(
                        OutputType.FILE
                );

        File destination =
                new File(
                        "screenshots/"
                        + fileName
                        + ".png"
                );

        destination.getParentFile().mkdirs();

        try {

            FileHandler.copy(
                    source,
                    destination
            );

            System.out.println(
                    "Screenshot saved: "
                    + destination.getAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(
                    "Unable to save screenshot: "
                    + e.getMessage()
            );
        }
    }
}