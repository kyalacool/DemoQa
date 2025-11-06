package com.automation;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

import static com.automation.utils.WebDriverManager.*;

@Slf4j
public class BaseTest {

    protected WebDriver getDriver(){
        return getCurrentDriver();
    }

    @BeforeClass
    void setup() throws MalformedURLException {
        WebDriver driver = setDriver();
        driver.get(page_url);
    }

    @BeforeMethod
    void reloadHomePage() {
        WebDriver driver = getCurrentDriver();
        driver.get(page_url);
    }

    @AfterClass
    void tearDown() {
        getCurrentDriver().quit();
        log.info("Driver quit.");
        removeThreadLocalDriver();
    }
}
