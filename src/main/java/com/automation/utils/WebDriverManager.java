package com.automation.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.automation.utils.PropertyReader.getProperty;

@Slf4j
public class WebDriverManager {
    private static String waitingTime;
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

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
        WebDriver driver;
        String browser = getProperty("browser");
        String headless = getProperty("headless");
        String env = getProperty("env");
        waitingTime = getProperty("waitingtimeinsec");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", "/home/seluser/Downloads");

        Map<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("download.prompt_for_download", false);
        edgePrefs.put("profile.default_content_settings.popups", 0);
        edgePrefs.put("download.default_directory", "/home/seluser/Downloads");

        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                if (Objects.equals(headless, "true")) {
                    options.addArguments("--headless");
                }
                if (Objects.equals(getProperty("remote.driver"), "true")){
                    options.setExperimentalOption("prefs", prefs);
                    driver = new RemoteWebDriver(new URL("http://selenium-router:4444/wd/hub"),options);
                    log.info("Setup {} driver.", browser);
                    return driver;
                }
                driver = new ChromeDriver(options);
                driver.get(getUrl());
                log.info("Setup {} driver.", browser);
                return driver;
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                if (Objects.equals(headless, "true")) {
                    System.out.println("its true");
                    options.addArguments("headless");
                }
                if (Objects.equals(getProperty("remote.driver"), "true")){
                    options.setExperimentalOption("prefs", edgePrefs);
                    driver = new RemoteWebDriver(new URL("http://selenium-router:4444/wd/hub"),options);
                    log.info("Setup {} driver.", browser);
                    return driver;
                }
                driver = new EdgeDriver(options);
                driver.get(getUrl());
                log.info("Setup {} driver.", browser);
                return driver;
            }
            default -> {
                log.warn("Browser ({}) is null", browser);
                return null;
            }
        }
    }

    public static String getUrl() {
        switch (getProperty("env")) {
            case "local" -> {
                return urls.LOCAL.url;
            }
            default -> {
                log.warn("Environment doesn't find.");
                return "";
            }
        }
    }

    private enum urls {
        LOCAL("https://demoqa.com");

        final String url;

        urls(String url) {
            this.url = url;
        }
    }

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
        } catch (org.openqa.selenium.TimeoutException e) {
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
