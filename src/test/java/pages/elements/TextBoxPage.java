package pages.elements;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import utils.WebDriverManager;

public class TextBoxPage extends BasePage {

    private final String CORRECT_NAME ="Bence Varga";
    private final String CORRECT_EMAIL = "example@example.com";
    private final String INCORRECT_EMAIL = "incorrectemail";
    private final String CORRECT_CURRENT_ADDRESS = "Budapest Example street 36";
    private final String CORRECT_PERMANENT_ADDRESS = "Szolnok Example street 3";

    @Getter
    @FindBy(xpath ="//input[@id='userName']")
    private WebElement fullNameInput;

    @FindBy(xpath ="//input[@id='userEmail']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='userEmail' and contains(@class, 'field-error')]")
    private WebElement invalidEmailInput;

    @FindBy(xpath ="//textarea[@id='currentAddress']")
    private WebElement currentAddressInput;

    @FindBy(xpath ="//textarea[@id='permanentAddress']")
    private WebElement permanentAddressInput;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//p[@id='name']")
    private WebElement nameResultElement;

    @FindBy(xpath = "//p[@id='email']")
    private WebElement emailResultElement;

    @FindBy(xpath = "//p[@id='currentAddress']")
    private WebElement currentAddressResultElement;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    private WebElement permanentAddressResultElement;

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    public void verifyTextBoxWithCorrectData(){
        fullNameInput.sendKeys(CORRECT_NAME);
        emailInput.sendKeys(CORRECT_EMAIL);
        currentAddressInput.sendKeys(CORRECT_CURRENT_ADDRESS);
        permanentAddressInput.sendKeys(CORRECT_PERMANENT_ADDRESS);
        submitButton.click();
        WebDriverManager.waitForElementVisibility(nameResultElement);
        SoftAssert soft = new SoftAssert();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String nameResult = (String)
                js.executeScript("return arguments[0].childNodes[1].textContent", nameResultElement);
        String emailResult = (String)
                js.executeScript("return arguments[0].childNodes[1].textContent", emailResultElement);
        String currentAddressResult = (String)
                js.executeScript("return arguments[0].childNodes[1].textContent", currentAddressResultElement);
        String permanentAddressResult = (String)
                js.executeScript("return arguments[0].childNodes[1].textContent", permanentAddressResultElement);
        soft.assertEquals(nameResult, CORRECT_NAME);
        soft.assertEquals(emailResult, CORRECT_EMAIL);
        soft.assertEquals(currentAddressResult, CORRECT_CURRENT_ADDRESS);
        soft.assertEquals(permanentAddressResult, CORRECT_PERMANENT_ADDRESS);
        soft.assertAll();
    }

    public void verifyTextBoxWithIncorrectEmail(){
        fullNameInput.sendKeys(CORRECT_NAME);
        emailInput.sendKeys(INCORRECT_EMAIL);
        currentAddressInput.sendKeys(CORRECT_CURRENT_ADDRESS);
        permanentAddressInput.sendKeys(CORRECT_PERMANENT_ADDRESS);
        submitButton.click();
        Assert.assertTrue(invalidEmailInput.isDisplayed());
    }
}
