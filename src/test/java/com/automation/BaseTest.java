package com.automation;

import com.automation.utils.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

import static com.automation.utils.WebDriverManager.*;

@Slf4j
public class BaseTest {
    public WebDriver driver;

    @BeforeMethod
    void setup() throws MalformedURLException {
        driver = setDriver();
        driver.get(page_url);
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
        removeThreadLocalDriver();
    }
}
