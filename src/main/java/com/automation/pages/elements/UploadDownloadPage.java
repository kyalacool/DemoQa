package com.automation.pages.elements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UselessFileDetector;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.automation.pages.BasePage;
import com.automation.utils.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.automation.utils.WebDriverManager.*;

@Slf4j
public class UploadDownloadPage extends BasePage {

    String fileName = "sampleFile.jpeg";
    String downloadPath = "C:\\Users\\bence.varga\\IdeaProjects\\demoqa\\Downloads\\" + fileName;
    String nodeDownloadPath = "/data/downloads/" + fileName;

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
//        ((RemoteWebDriver) getCurrentDriver()).setFileDetector(new LocalFileDetector());
        Path notDownloadedPath = Paths.get(nodeDownloadPath);
        Assert.assertFalse(Files.exists(notDownloadedPath));
        waitForElementVisibility(downloadButton);
        downloadButton.click();
        log.info("Download button clicked.");
        int round = 0;
        int waiting = 5;
        waitForFileExist(nodeDownloadPath);
        Path downloadedPath = Paths.get(nodeDownloadPath);
        Assert.assertTrue(Files.exists(downloadedPath));
        waitForElementVisibility(chooseFileButton);
        chooseFileButton.sendKeys(nodeDownloadPath);
        log.info("Upload button clicked.");
        Assert.assertTrue(uploadedFilePath.isDisplayed());
        Assert.assertEquals(uploadedFilePath.getText(), "C:\\fakepath\\" + fileName);
        File file = new File(nodeDownloadPath);
        Path path = Paths.get(nodeDownloadPath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        ((RemoteWebDriver) getCurrentDriver()).setFileDetector(new UselessFileDetector());
    }
}
