package com.automation.pages.elements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;

@Slf4j
public class ElementsPage extends BasePage {

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Text Box')]")
    private WebElement textBoxMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Check Box')]")
    private WebElement checkBoxMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Radio Button')]")
    private WebElement radioButtonMenuButton;

    @Getter
    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Web Tables')]")
    private WebElement webTablesMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Buttons')]")
    private WebElement buttonsMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Links')]")
    private WebElement linksMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Broken Links - Images')]")
    private WebElement brokenLinksMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Upload and Download')]")
    private WebElement uploadAndDownloadMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Dynamic Properties')]")
    private WebElement dynamicPropertiesMenuButton;

    public ElementsPage(WebDriver driver) {
        super(driver);
    }

    public TextBoxPage clickOnTextBoxMenuButton() {
        WebDriverManager.waitForElementVisibility(textBoxMenuButton);
        textBoxMenuButton.click();
        TextBoxPage textBoxPage = new TextBoxPage(driver);
        WebDriverManager.waitForElementVisibility(textBoxPage.getFullNameInput());
        log.info("User is on the Text Box subpage.");
        return textBoxPage;
    }

    public CheckBoxPage clickOnCheckBoxMenuButton() {
        WebDriverManager.waitForElementVisibility(checkBoxMenuButton);
        checkBoxMenuButton.click();
        CheckBoxPage checkBoxPage = new CheckBoxPage(driver);
        WebDriverManager.waitForElementVisibility(checkBoxPage.getTitle());
        log.info("User is on the Check Box subpage.");
        return checkBoxPage;
    }

    public RadioButtonPage clickOnRadioButtonMenuButton() {
        WebDriverManager.waitForElementVisibility(radioButtonMenuButton);
        radioButtonMenuButton.click();
        RadioButtonPage radioButtonPage = new RadioButtonPage(driver);
        WebDriverManager.waitForElementVisibility(radioButtonPage.getTitle());
        log.info("User is on the Radio Button subpage.");
        return radioButtonPage;
    }

    public WebTablesPage clickOnWebTablesMenuButton() {
        WebDriverManager.waitForElementVisibility(webTablesMenuButton);
        webTablesMenuButton.click();
        WebTablesPage webTablesPage = new WebTablesPage(driver);
        WebDriverManager.waitForElementVisibility(webTablesPage.getTitle());
        log.info("User is on the Web Tables subpage.");
        return webTablesPage;
    }

    public ButtonsPage clickOnButtonsMenuButton() {
        WebDriverManager.waitForElementVisibility(buttonsMenuButton);
        buttonsMenuButton.click();
        ButtonsPage buttonsPage = new ButtonsPage(driver);
        WebDriverManager.waitForElementVisibility(buttonsPage.getTitle());
        log.info("User is on the Buttons subpage.");
        return buttonsPage;
    }

    public LinksPage clickOnLinksMenuButton() {
        WebDriverManager.waitForElementVisibility(linksMenuButton);
        linksMenuButton.click();
        LinksPage linksPage = new LinksPage(driver);
        WebDriverManager.waitForElementVisibility(linksPage.getTitle());
        log.info("User is on the Links subpage.");
        return linksPage;
    }

    public BrokenLinksImagesPage clickOnBrokenLinksImagesMenuButton() {
        WebDriverManager.waitForElementVisibility(brokenLinksMenuButton);
        brokenLinksMenuButton.click();
        BrokenLinksImagesPage brokenLinksImagesPage = new BrokenLinksImagesPage(driver);
        WebDriverManager.waitForElementVisibility(brokenLinksImagesPage.getTitle());
        log.info("User is on the Broken Links and Images subpage.");
        return brokenLinksImagesPage;
    }

    public UploadDownloadPage clickOnUploadDownloadMenuButton() {
        WebDriverManager.waitForElementVisibility(uploadAndDownloadMenuButton);
        uploadAndDownloadMenuButton.click();
        UploadDownloadPage uploadDownloadPage = new UploadDownloadPage(driver);
        WebDriverManager.waitForElementVisibility(uploadDownloadPage.getTitle());
        log.info("User is on the Upload and Download subpage.");
        return uploadDownloadPage;
    }

    public DynamicPropertiesPage clickOnDynamicPropertiesMenuButton() {
        WebDriverManager.waitForElementVisibility(dynamicPropertiesMenuButton);
        dynamicPropertiesMenuButton.click();
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage(driver);
        WebDriverManager.waitForElementVisibility(dynamicPropertiesPage.getTitle());
        log.info("User is on the Dynamic Properties subpage.");
        return dynamicPropertiesPage;
    }
}
