package com.automation.pages.elements;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;

import java.time.Duration;
import java.util.*;

import static com.automation.utils.WebDriverManager.waitForElementVisibility;

public class WebTablesPage extends BasePage {

    private final String COLUMN_FIRST_NAME = "First Name";
    private final String COLUMN_LAST_NAME = "Last Name";
    private final String COLUMN_EMAIL = "Email";
    private final String COLUMN_DEPARTMENT = "Department";
    private final String COLUMN_SALARY = "Salary";
    private final String COLUMN_AGE = "Age";
    private final String VALID_FIRST_NAME = "Bence";
    private final String VALID_LAST_NAME = "Varga";
    private final String VALID_EMAIL = "bence@example.com";
    private final String INVALID_EMAIL = "a";
    private final String VALID_AGE = "14";
    private final String INVALID_AGE = "a";
    private final String VALID_SALARY = "1400";
    private final String INVALID_SALARY = "a";
    private final String VALID_DEPARTMENT = "qa";
    private final String NEW_FIRST_NAME = "Lajos";

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Web Tables')]")
    private WebElement title;

    @FindBy(xpath = "//button[contains(text(),'Add')]")
    private WebElement addButton;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement addTable;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='userEmail']")
    private WebElement userEmailInput;

    @FindBy(xpath = "//input[@id='age']")
    private WebElement ageInput;

    @FindBy(xpath = "//input[@id='salary']")
    private WebElement salaryInput;

    @FindBy(xpath = "//input[@id='department']")
    private WebElement departmentInput;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='rt-tr']")
    private WebElement tableColumn;

    @FindBy(xpath = "//div[@class='rt-tbody']")
    private WebElement tableRow;

    @FindBy(xpath = "//input[@id='searchBox']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='close']")
    private WebElement closeFormButton;

    public WebTablesPage(WebDriver driver) {
        super(driver);
    }

    private List<Map<String, String>> getTable() {
        List<Map<String, String>> finalResult = new ArrayList<>();
        waitForElementVisibility(tableColumn);
        List<WebElement> allRowElement = driver.findElements(By.xpath("//div[@role='rowgroup']"));
        List<WebElement> columnHeaderList = driver.findElements(By.xpath("//div[@role='columnheader']"));
        for (WebElement row : allRowElement) {
            Map<String, String> tableMap = new HashMap<>();
            for (int i = 0; i < columnHeaderList.size() - 1; i++) {
                WebElement columnElement = driver.findElements(By.xpath("//div[@role='columnheader']")).get(i);
                String column = columnElement.getText();
                String result = row.findElement(By.xpath(".//div[@role='gridcell'][%d]".formatted(i + 1))).getText();
                if (!result.equals(" ")) {
                    tableMap.put(column, result);
                }
            }
            if (!tableMap.isEmpty()) {
                finalResult.add(tableMap);
            }
        }
        return finalResult;
    }

    private boolean isDataInTheTable(String columnName, String data) {
        List<Map<String, String>> myTable = getTable();
        boolean isItiN = false;
        for (int i = 0; i < myTable.size(); i++) {
            if (Objects.equals(myTable.get(i).get(columnName), data)) {
                isItiN = true;
            }
            ;
        }
        return isItiN;
    }

    private void deleteRowByEmail(String email) {
        WebElement deletedElement = driver.findElement(By
                .xpath("//span[@title='Delete' and ./../../preceding-sibling::div[contains(text(),'%s')]]".formatted(email)));
        WebDriverManager.waitForElementVisibility(deletedElement);
        deletedElement.click();
    }

    private void clickOnEditByEmail(String email) {
        WebElement deletedElement = driver.findElement(By
                .xpath("//span[@title='Edit' and ./../../preceding-sibling::div[contains(text(),'%s')]]".formatted(email)));
        deletedElement.click();
    }

    public WebTablesPage verifyAddFunctionalityWithInvalidData() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SoftAssert softAssert = new SoftAssert();
        addButton.click();
        waitForElementVisibility(addTable);
        String ageBoarderColorBefore = ageInput.getCssValue("border-color");
        String salaryBoarderColorBefore = salaryInput.getCssValue("border-color");
        String emailBoarderColorBefore = userEmailInput.getCssValue("border-color");
        firstNameInput.sendKeys(VALID_FIRST_NAME);
        lastNameInput.sendKeys(VALID_LAST_NAME);
        userEmailInput.sendKeys(INVALID_EMAIL);
        ageInput.sendKeys(INVALID_AGE);
        salaryInput.sendKeys(INVALID_SALARY);
        departmentInput.sendKeys(VALID_DEPARTMENT);
        submitButton.click();
        wait.until(d -> "rgb(220, 53, 69)".equals(ageInput.getCssValue("border-color")));
        String ageBoarderColorAfter = ageInput.getCssValue("border-color");
        String salaryBoarderColorAfter = salaryInput.getCssValue("border-color");
        String emailBoarderColorAfter = userEmailInput.getCssValue("border-color");
        softAssert.assertNotEquals(ageBoarderColorBefore, ageBoarderColorAfter);
        softAssert.assertNotEquals(salaryBoarderColorBefore, salaryBoarderColorAfter);
        softAssert.assertNotEquals(emailBoarderColorBefore, emailBoarderColorAfter);
        softAssert.assertAll();
        closeFormButton.click();
        driver.navigate().refresh();
        return this;
    }

    public WebTablesPage verifyAddFunctionalityWithValidData() {
        SoftAssert softAssert = new SoftAssert();
        addButton.click();
        waitForElementVisibility(addTable);
        firstNameInput.sendKeys(VALID_FIRST_NAME);
        lastNameInput.sendKeys(VALID_LAST_NAME);
        userEmailInput.sendKeys(VALID_EMAIL);
        ageInput.sendKeys(VALID_AGE);
        salaryInput.sendKeys(VALID_SALARY);
        departmentInput.sendKeys(VALID_DEPARTMENT);
        submitButton.click();
        softAssert.assertTrue(isDataInTheTable(COLUMN_FIRST_NAME, VALID_FIRST_NAME));
        softAssert.assertTrue(isDataInTheTable(COLUMN_LAST_NAME, VALID_LAST_NAME));
        softAssert.assertTrue(isDataInTheTable(COLUMN_AGE, VALID_AGE));
        softAssert.assertTrue(isDataInTheTable(COLUMN_EMAIL, VALID_EMAIL));
        softAssert.assertTrue(isDataInTheTable(COLUMN_DEPARTMENT, VALID_DEPARTMENT));
        softAssert.assertTrue(isDataInTheTable(COLUMN_SALARY, VALID_SALARY));
        softAssert.assertAll();
        return this;
    }

    public WebTablesPage verifyEditFunctionality() {
        clickOnEditByEmail(VALID_EMAIL);
        firstNameInput.clear();
        firstNameInput.sendKeys(NEW_FIRST_NAME);
        submitButton.click();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDataInTheTable(COLUMN_FIRST_NAME, NEW_FIRST_NAME));
        softAssert.assertFalse(isDataInTheTable(COLUMN_FIRST_NAME, VALID_FIRST_NAME));
        clickOnEditByEmail(VALID_EMAIL);
        firstNameInput.clear();
        firstNameInput.sendKeys(VALID_FIRST_NAME);
        submitButton.click();
        softAssert.assertFalse(isDataInTheTable(COLUMN_FIRST_NAME, NEW_FIRST_NAME));
        softAssert.assertTrue(isDataInTheTable(COLUMN_FIRST_NAME, VALID_FIRST_NAME));
        softAssert.assertAll();
        return this;
    }

    public WebTablesPage verifySearchFunctionality() {
        searchInput.sendKeys(VALID_FIRST_NAME);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDataInTheTable(COLUMN_FIRST_NAME, VALID_FIRST_NAME));
        searchInput.clear();
        searchInput.sendKeys(NEW_FIRST_NAME);
        softAssert.assertFalse(isDataInTheTable(COLUMN_FIRST_NAME, VALID_FIRST_NAME));
        softAssert.assertAll();
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public void verifyDeleteFuncionality() {
        WebDriverManager.waitForElementVisibility(tableColumn);
        deleteRowByEmail(VALID_EMAIL);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isDataInTheTable(COLUMN_FIRST_NAME, VALID_FIRST_NAME));
        softAssert.assertFalse(isDataInTheTable(COLUMN_LAST_NAME, VALID_LAST_NAME));
        softAssert.assertFalse(isDataInTheTable(COLUMN_AGE, VALID_AGE));
        softAssert.assertFalse(isDataInTheTable(COLUMN_EMAIL, VALID_EMAIL));
        softAssert.assertFalse(isDataInTheTable(COLUMN_DEPARTMENT, VALID_DEPARTMENT));
        softAssert.assertFalse(isDataInTheTable(COLUMN_SALARY, VALID_SALARY));
        softAssert.assertAll();
    }
}
