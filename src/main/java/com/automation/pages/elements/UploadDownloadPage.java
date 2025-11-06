package com.automation.pages.elements;

import com.automation.utils.PropertyReader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.automation.pages.home.BasePage;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.automation.utils.PropertyReader.getProperty;
import static com.automation.utils.WebDriverManager.*;

@Slf4j
public class UploadDownloadPage extends BasePage {

    String fileName = "sampleFile.jpeg";
    String downloadPath = "C:\\Users\\bence.varga\\IdeaProjects\\demoqa\\Downloads\\" + fileName;
    //TODO : ci vs local (filepath)
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

    private String getFilePath(){
        PropertyReader.getInstance();
        String env = getProperty("env");
        if (Objects.equals(env, "local")){
            return "C:\\Users\\bence.varga\\Downloads\\" + fileName;
        } else if (Objects.equals(env,"ci")) {
            return "/data/downloads/" + fileName;
        }
        else {
            log.warn("Environment and File path does not found.");
            return null;
        }
    }

    public void verifyDownloadFunctionality() {
        SoftAssert softAssert = new SoftAssert();
        Path notDownloadedPath = Paths.get(getFilePath());
        softAssert.assertFalse(Files.exists(notDownloadedPath));
        waitForElementVisibility(downloadButton);
        downloadButton.click();
        log.info("Download button clicked.");
        waitForFileExist(getFilePath());
        Path downloadedPath = Paths.get(getFilePath());
        softAssert.assertTrue(Files.exists(downloadedPath));
        log.info("Downloaded file asserted.");
        waitForElementVisibility(chooseFileButton);
        log.info(" chooseFileButton is visibile");
        chooseFileButton.sendKeys(getFilePath());
        log.info("Upload button clicked.");
        softAssert.assertTrue(uploadedFilePath.isDisplayed());
        softAssert.assertEquals(uploadedFilePath.getText(), "C:\\fakepath\\" + fileName);
        File file = new File(getFilePath());
        Path path = Paths.get(getFilePath());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        softAssert.assertAll();
    }
}
