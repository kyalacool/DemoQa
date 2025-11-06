package com.automation.pages.alerts;

import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Set;

import static com.automation.utils.WebDriverManager.getCurrentDriver;
import static com.automation.utils.WebDriverManager.waitForElementPresence;

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

    public BrowserWindowsPage verifyNewTabButton() {
        String originalWindow = driver.getWindowHandle();
        newTabButton.click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                waitForElementPresence(By.id("sampleHeading"));
                Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/sample");
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalWindow);
        return this;
    }

    public BrowserWindowsPage verifyNewWindowButton() {
        String originalWindow = driver.getWindowHandle();
        newWindowButton.click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                waitForElementPresence(By.id("sampleHeading"));
                Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/sample");
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalWindow);
        return this;
    }

    public BrowserWindowsPage verifyNewWindowsMessageButton() {
        String originalWindow = driver.getWindowHandle();
        newWindowMessageButton.click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                WebElement element = driver.findElement(By.tagName("body"));
                Assert.assertTrue(element.getText().startsWith("Knowledge"),
                        "The body of the new window does not start with 'Knowledge'");
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalWindow);
        return this;
    }
}


    /*  DEAD-LOCK
        public void verifyNewWindowsMessageButton(){
        String originalWindow = driver.getWindowHandle();
        newWindowMessageButton.click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            if(!window.equals(originalWindow)){
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                log.info("Here we are...");
                driver.switchTo().window(window);
                try {
                    Thread.sleep(100);
                    log.warn("Kényszerített szünet 1: 100ms a switchTo() után.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                try {
                    driver.findElements(By.tagName("html"));
                    log.info("HTML elem sikeresen lekérdezve.");
                } catch (Exception e) {
                    log.error("Az element find is elakadt, a dead-lock megerősítve.", e);
                }
                String currentTitle = driver.getTitle(); // <-- EZT HOZZUK IDE, FELTÉTELEZVE, HOGY MÁR FUTHAT!
                log.info("Új ablak címe lekérdezve: {}", currentTitle); // Ha ez megjelenik, nyertünk!

                // 2. JSE Ping (Driver állapot frissítése)
                try {
                    ((JavascriptExecutor) driver).executeScript("return 0;");
                    log.info("JSE Ping sikeres.");
                } catch (Exception e) {
                    // Ha a Ping elakad, ez kivétel lesz. Kezeljük ezt inkább a Thread.sleep 2-vel
                    log.error("JSE Ping elakadt, a dead-lock fennáll.", e);
                }

                // 3. Kényszerített szünet 2 (Rövid szünet a Ping után)
                try {
                    Thread.sleep(100);
                    log.warn("Kényszerített szünet 2: 100ms a Ping után.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.info("original window ID : {}", originalWindow);
                log.info("new window ID : {}", window);
                log.info("Current window : {}", driver.getWindowHandle());
                try{
                    wait.until((ExpectedCondition<Boolean>) webDriver -> {
                        // 1. Ellenőrizzük a Page Load State-et
                        String readyState = (String) js.executeScript("return document.readyState");

                        // 2. Ellenőrizzük a tartalom meglétét
                        boolean contentPresent = (Boolean) js.executeScript("return document.body.innerHTML.includes('Knowledge');");

                        // Visszatérünk TRUE értékkel, ha már 'complete' VAGY ha a tartalom megvan.
                        // A második feltétel a biztonsági háló, ha a 'complete' state-et a driver kihagyja.
                        return readyState.equals("complete") || contentPresent;
                    });

                    log.info("VÁRAKOZÁS SIKERES: A böngésző állapota 'complete' VAGY a tartalom megjelent.");

                } catch (TimeoutException e){
                    log.error("TIMEOUT: A 'complete' állapot és a tartalom sem jelent meg 15 másodpercen belül.", e);
                } catch (ClassCastException e){
                    log.error("CLASSCAST: Hiba a JS visszatérési értékének konvertálásakor.",e);
                }
                log.info("Page source : {}", driver.getPageSource());
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

     */

