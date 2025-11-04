package com.automation.pages.forms;

import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class PracticeFormPage extends BasePage {

    private String FIRSTNAME = "Bence";
    private String LASTNAME = "Varga";
    private String EMAIL = "bncvrg@google.com";
    private String MOBILE = "6365613165";
    private String SUBJECT = "a";
    private String CURRENT_ADDRESS = "Bp. 1178 Szalóka utca 14.";
    private String FILE_PATH = "\"C:\\Users\\bence.varga\\Downloads\\sampleFile.jpeg\"";


    @Getter
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='userEmail']")
    private WebElement userEmailInput;

    @FindBy(xpath = "//input[@value='Male']/..")
    private WebElement maleButton;

    @FindBy(xpath = "//input[@id='userNumber']")
    private WebElement mobileInput;

    @FindBy(xpath = "//input[@id='dateOfBirthInput']")
    private WebElement dateOfBirthButton;

    @FindBy(xpath = "//select[@class='react-datepicker__month-select']/option[@value='10']")
    private WebElement monthChoose;

    @FindBy(xpath = "//select[@class='react-datepicker__year-select']/option[@value='1991']")
    private WebElement yearChoose;

    @FindBy(xpath = "//div[@class='react-datepicker__week']/div[contains(text(),'22')]")
    private WebElement dayChoose;

    @FindBy(xpath = "//div[@id='subjectsContainer']/div")
    private WebElement subjectsAutoCompleteInput;

    @FindBy(xpath = "//div[@class='subjects-auto-complete__menu']")
    private WebElement subjectsOpenedMenu;

    @FindBy(xpath = "//input[@value='1']")
    private WebElement sportsButton;

    @FindBy(xpath = "//input[@value='2']")
    private WebElement readingButton;

    @FindBy(xpath = "//input[@value='3']")
    private WebElement musicButton;

    @FindBy(xpath = "//input[@id='uploadPicture']")
    private WebElement chooseFileButton;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    private WebElement currentAddressInput;

    @FindBy(xpath = "//div[contains(text(), 'Select State')]")
    private WebElement selectStateButton;

    @FindBy(xpath = "//div[contains(text(), 'Select City')]")
    private WebElement selectCityButton;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPracticeFormWithValidData() throws InterruptedException {
        firstNameInput.sendKeys(FIRSTNAME);
        lastNameInput.sendKeys(LASTNAME);
        userEmailInput.sendKeys(EMAIL);
        maleButton.click();
        mobileInput.sendKeys(MOBILE);
        dateOfBirthButton.click();
        monthChoose.click();
        yearChoose.click();
        dayChoose.click();
        subjectsAutoCompleteInput.click();
        log.warn("test failed here");
        Thread.sleep(5000);
        subjectsAutoCompleteInput.sendKeys(SUBJECT);
        WebDriverManager.waitForElementVisibility(subjectsOpenedMenu);
        subjectsAutoCompleteInput.sendKeys(Keys.ARROW_DOWN);
        subjectsAutoCompleteInput.sendKeys(Keys.ARROW_DOWN);
        subjectsAutoCompleteInput.sendKeys(Keys.ENTER);
        log.info("test after subject 2");
        readingButton.click();
        musicButton.click();
        chooseFileButton.sendKeys(FILE_PATH);
        currentAddressInput.sendKeys(CURRENT_ADDRESS);
        selectStateButton.sendKeys(Keys.ARROW_DOWN);
        selectStateButton.sendKeys(Keys.ARROW_DOWN);
        selectStateButton.sendKeys(Keys.ENTER);
        selectCityButton.sendKeys(Keys.ARROW_DOWN);
        selectCityButton.sendKeys(Keys.ENTER);
        submitButton.click();
    }
}
