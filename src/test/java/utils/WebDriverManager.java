package utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static tests.BaseTest.getCurrentDriver;

@Slf4j
public class WebDriverManager {
    private static String waitingTime;

    public static WebDriver getDriver() {
        PropertyReader.getInstance();
        WebDriver driver;
        String browser = PropertyReader.getProperty("browser");
        String headless = PropertyReader.getProperty("headless");
        String env = PropertyReader.getProperty("env");
        waitingTime =  PropertyReader.getProperty("waitingtimeinsec");

        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (Objects.equals(headless, "true")){
                    options.addArguments("--headless");
                }
                options.addArguments("--window-size=1920,1080");
                driver= new ChromeDriver(options);
                driver.get(getUrl());
                log.info("Return {} driver.", browser);
                return driver;
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                System.out.println(headless);
                if (Objects.equals(headless, "true")) {
                    System.out.println("its true");
                    options.addArguments("headless");
                }
                options.addArguments("--window-size=1920,1080");
                driver = new EdgeDriver(options);
                driver.get(getUrl());
                log.info("Return {} driver.", browser);
                return driver;
            }
            default -> {
                log.warn("Browser ({}) is null", browser);
                return null;}
        }
    }

    public static String getUrl(){
        switch (PropertyReader.getProperty("env")){
            case "local" -> {
                return urls.LOCAL.url;
            }
            default -> {
                log.warn("Environment doesn't find.");
                return "";
            }
        }
    }
    
    private enum urls{
        LOCAL("https://demoqa.com");

        final String url;
        urls(String url) {
            this.url = url;
        }
    }

    public static void waitForElementVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(Long.parseLong(waitingTime)));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementPresence(By route){
        WebDriverWait wait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(Long.parseLong(waitingTime)));
        wait.until(ExpectedConditions.presenceOfElementLocated(route));
    }
}
