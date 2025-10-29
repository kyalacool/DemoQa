package com.automation.pages.elements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.automation.pages.BasePage;
import com.automation.utils.WebDriverManager;

import java.io.File;

@Slf4j
public class UploadDownloadPage extends BasePage {

    String fileName = "sampleFile.jpeg";
    String downloadPath = "C:\\Users\\bence.varga\\Downloads\\" + fileName;

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Upload and Download')]")
    private WebElement title;

    @FindBy(xpath = "//a[@id='downloadButton']")
    private WebElement downloadButton;

    @FindBy(xpath = "//input[@id='uploadFile']")
    private WebElement chooseFileButton;

    @FindBy(xpath = "//p[@id='uploadedFilePath']")
    private WebElement uploadedFilePath;

    public UploadDownloadPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDownloadFunctionality() {
        File downloadedFile = new File(downloadPath);
        downloadedFile.delete();
        Assert.assertFalse(downloadedFile.exists());
        WebDriverManager.waitForElementVisibility(downloadButton);
        downloadButton.click();
        log.info("Download button clicked.");
        int round = 0;
        int waiting = 5;
        while (round < waiting) {
            if (downloadedFile.exists()) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            waiting++;
        }
        Assert.assertTrue(downloadedFile.exists());
        WebDriverManager.waitForElementVisibility(chooseFileButton);
        chooseFileButton.sendKeys(downloadPath);
        log.info("Upload button clicked.");
        Assert.assertTrue(uploadedFilePath.isDisplayed());
        Assert.assertEquals(uploadedFilePath.getText(), "C:\\fakepath\\" + fileName);
    }
}
