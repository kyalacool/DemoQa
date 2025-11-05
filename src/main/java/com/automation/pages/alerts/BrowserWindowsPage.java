package com.automation.pages.alerts;

import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Set;

import static com.automation.utils.WebDriverManager.waitForElementPresence;
import static com.automation.utils.WebDriverManager.waitForElementVisibility;

@Slf4j
public class BrowserWindowsPage extends BasePage {

    private String messageStart = "Knowledge increases ";

    @Getter
    @FindBy(xpath = "//button[@id='tabButton']")
    WebElement newTabButton;

    @FindBy(xpath = "//button[@id='windowButton']")
    WebElement newWindowButton;

    @FindBy(xpath = "//button[@id='messageWindowButton']")
    WebElement newWindowMessageButton;

    public BrowserWindowsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyButtons(){
        SoftAssert softAssert = new SoftAssert();
        String originalWindow = driver.getWindowHandle();
        newTabButton.click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            if(!window.equals(originalWindow)){
                driver.switchTo().window(window);
                softAssert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/sample");
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalWindow);
        newWindowButton.click();
        allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            if (!window.equals(originalWindow)){
                driver.switchTo().window(window);
                softAssert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/sample");
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalWindow);
        newWindowMessageButton.click();
        allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            if(!window.equals(originalWindow)){
                driver.switchTo().window(window);
                //TODO : FIX THIS PROBLEM
                WebElement messageElement = driver.findElement(By.xpath("//body"));
                String message = messageElement.getAttribute("innerHTML");
                softAssert.assertTrue(message.startsWith(messageStart));
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalWindow);
        softAssert.assertAll();
    }
}
