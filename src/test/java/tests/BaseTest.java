package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static utils.WebDriverManager.getDriver;
import static utils.WebDriverManager.getUrl;

public class BaseTest {
    public WebDriver driver;
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @BeforeClass
    void setup(){
        threadLocalDriver.set(getDriver());
        driver = threadLocalDriver.get();
    }

    public static WebDriver getCurrentDriver(){
        return threadLocalDriver.get();
    }

    @BeforeMethod
    void startFromHomePage(){
        driver.get(getUrl());
    }

    @AfterClass
    void tearDown(){
        driver.quit();
        threadLocalDriver.remove();
    }
}
