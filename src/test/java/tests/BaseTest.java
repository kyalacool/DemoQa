package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverManager;

public class BaseTest {
    public WebDriver driver;
    ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @BeforeClass
    void setup(){
        threadLocalDriver.set(WebDriverManager.getDriver());
        driver = threadLocalDriver.get();
    }

    @BeforeMethod
    void startFromHomePage(){
        driver.get(WebDriverManager.getUrl());
    }

    @AfterClass
    void tearDown(){
        driver.quit();
        threadLocalDriver.remove();
    }
}
