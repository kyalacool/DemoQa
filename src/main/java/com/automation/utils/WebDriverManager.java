package com.automation.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

import static com.automation.utils.PropertyReader.getProperty;

@Slf4j
public class WebDriverManager {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public final static String page_url = "https://demoqa.com";
    private final static String remote_url = "http://selenium-router:4444/wd/hub";
    private static String waitingTime;

    public static WebDriver setDriver() throws MalformedURLException {
        threadLocalDriver.set(getDriver());
        return threadLocalDriver.get();
    }

    public static void removeThreadLocalDriver(){
        threadLocalDriver.remove();
        }

    public static WebDriver getCurrentDriver() {
        return threadLocalDriver.get();
    }

    public static WebDriver getDriver() throws MalformedURLException {
        PropertyReader.getInstance();
        WebDriver driver = null;
        waitingTime = getProperty("waitingtimeinsec");
        String env = getProperty("env");
        String browser = getProperty("browser");
        String headless = getProperty("headless");
        Capabilities capabilities;

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                if (Objects.equals(headless, "true")) {
                    options.addArguments("--headless");
                }
                if (Objects.equals(env, "ci")) {
                    options.addArguments("--no-sandbox");
                }
                capabilities = options;
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--window-size=1920,1080");
                if (Objects.equals(headless, "true")) {
                    options.addArguments("headless");
                }
                if (Objects.equals(env, "ci")) {
                    options.addArguments("--no-sandbox");
                }
                capabilities = options;
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
                if (Objects.equals(headless, "true")) {
                    options.addArguments("-headless");
                }
                if (Objects.equals(env, "ci")) {
                    options.addArguments("--no-sandbox");
                }
                capabilities = options;
            }
            default -> throw new IllegalArgumentException("Unknown browser: " + browser);
        }

        if (Objects.equals(env, "ci")) {
            driver = new RemoteWebDriver(new URL(remote_url), capabilities);
            driver.get(page_url);
        }
        else if (Objects.equals(env, "local")) {
            driver = switch (browser.toLowerCase()) {
                case "chrome" -> new ChromeDriver((ChromeOptions) capabilities);
                case "firefox" -> new FirefoxDriver((FirefoxOptions) capabilities);
                case "edge" -> new EdgeDriver((EdgeOptions) capabilities);
                default -> throw new IllegalStateException("Unknown browser: " + browser);
            };
            driver.get(page_url);
        }
        else {
            log.error("Unknown or missing environment property: {}", env);
            throw new IllegalArgumentException("Unknown or missing environment property: " + env);
        }
        log.info("Setup {} driver, in {} environment.", browser, env);
        return driver;
    }

//    --- WEBDRIVER WAIT METHODS ---

    public static void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(Long.parseLong(waitingTime)));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean waitForFileExist(String route){
        WebDriverWait wait = new WebDriverWait(getCurrentDriver(),Duration.ofSeconds(Long.parseLong(waitingTime)));
        Path path = Paths.get(route);
        log.info("Looking for the downloaded file at path : {}", route);
        try {
            wait.until((Function<WebDriver, Boolean>) d -> {
                return Files.exists(path);
            });
            log.info("File found successfully at path : {}", route);
            return true;
        } catch (TimeoutException e) {
            log.warn("File was NOT found at path: {} within {} seconds.", route, waitingTime);
            return false;
        }
    }

    public static void waitForElementPresence(By route) {
        WebDriverWait wait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(Long.parseLong(waitingTime)));
        wait.until(ExpectedConditions.presenceOfElementLocated(route));
    }

    public static void waitForElementClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(Long.parseLong(waitingTime)));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollTo(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getCurrentDriver();
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void waitUntilTextToBePresent(By route, String text) {
        WebDriverWait wait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(Long.parseLong(waitingTime)));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(route, text));
    }
}
