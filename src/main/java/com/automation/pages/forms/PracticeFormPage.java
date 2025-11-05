package com.automation.pages.forms;

import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PracticeFormPage extends BasePage {

    private String FIRSTNAME = "Bence";
    private String LASTNAME = "Varga";
    private String EMAIL = "bncvrg@google.com";
    private String MOBILE = "6365613165";
    private String SUBJECT = "a";
    private String CURRENT_ADDRESS = "Bp. 1178 Szalóka utca 14.";
    private String FILE_PATH = "C:\\Users\\bence.varga\\Downloads\\sampleFile.jpeg";
    private List<String> exceptedResultList = List.of(FIRSTNAME + " " + LASTNAME, EMAIL, "Male", MOBILE,
            "22 November,1991", "Arts", "Reading, Music", "sampleFile.jpeg", CURRENT_ADDRESS, "NCR Gurgaon");

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

    @FindBy(xpath = "//div[@id='subjectsContainer']")
    private WebElement subjectsAutoCompleteInput;

    @FindBy(xpath = "//input[@id='subjectsInput']")
    private WebElement subjectsAutoCompleteOpened;

    @FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
    private WebElement sportsButton;

    @FindBy(xpath = "//label[@for='hobbies-checkbox-2']")
    private WebElement readingButton;

    @FindBy(xpath = "//label[@for='hobbies-checkbox-3']")
    private WebElement musicButton;

    @FindBy(xpath = "//input[@id='uploadPicture']")
    private WebElement chooseFileButton;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    private WebElement currentAddressInput;

    @FindBy(xpath = "//div[@id='state']")
    private WebElement selectStateButton;

    @FindBy(xpath = "//div[@id='react-select-3-option-0']")
    private WebElement selectStateOption;

    @FindBy(xpath = "//div[@id='city']")
    private WebElement selectCityButton;

    @FindBy(xpath = "//div[@id='react-select-4-option-1']")
    private WebElement selectCityOption;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getTheResultOfTable(){
        List<String> actualResultList = new ArrayList<>();
        List<WebElement> tableResult = driver.findElements(By.xpath("//table/tbody//tr/td[2]"));
        for (WebElement element : tableResult){
            String cellValue = element.getAttribute("innerHTML");
            actualResultList.add(cellValue);
        }
        return actualResultList;
    }

    public void verifyPracticeFormWithValidData(){
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
        subjectsAutoCompleteOpened.sendKeys(SUBJECT);
        subjectsAutoCompleteOpened.sendKeys(Keys.ARROW_DOWN);
        subjectsAutoCompleteOpened.sendKeys(Keys.ARROW_DOWN);
        subjectsAutoCompleteOpened.sendKeys(Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", readingButton);
        readingButton.click();
        musicButton.click();
        chooseFileButton.sendKeys(FILE_PATH);
        currentAddressInput.sendKeys(CURRENT_ADDRESS);
        selectStateButton.click();
        selectStateOption.click();
        selectCityButton.click();
        selectCityOption.click();
        submitButton.click();
        WebDriverManager.waitForElementVisibility(driver.findElement(By.xpath("//body[@class='modal-open']")));
        getTheResultOfTable();
        Assert.assertEquals(getTheResultOfTable(),exceptedResultList);
    }
}
