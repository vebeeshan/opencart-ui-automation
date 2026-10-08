package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties =
            new Properties();

    static {

        try (InputStream input =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     "config/config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "config.properties not found in classpath"
                );
            }

            properties.load(input);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config.properties",
                    e
            );
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {

        // Environment variables use OPENCART_ prefix
        // Example:
        // browser -> OPENCART_BROWSER
        // password -> OPENCART_PASSWORD

        String environmentKey =
                "OPENCART_"
                        + key
                        .toUpperCase()
                        .replace(".", "_");

        String environmentValue =
                System.getenv(environmentKey);

        if (environmentValue != null
                && !environmentValue.isBlank()) {

            return environmentValue;
        }

        return properties.getProperty(key);
    }

    public static String getOrDefault(
            String key,
            String defaultValue) {

        String value = get(key);

        if (value == null || value.isBlank()) {
            return defaultValue;
        }

        return value;
    }
}