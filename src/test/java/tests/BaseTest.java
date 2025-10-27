package tests;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

import static utils.WebDriverManager.*;

@Slf4j
public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    void setup() throws MalformedURLException {
        driver = setDriver();
    }

    @BeforeMethod
    void startFromHomePage() {
        driver.get(getUrl());
    }

    @AfterClass
    void tearDown() {
        driver.quit();
        removeThreadLocalDriver();
    }
}
