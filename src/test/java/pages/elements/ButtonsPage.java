package pages.elements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.BasePage;
import utils.WebDriverManager;

@Slf4j
public class ButtonsPage extends BasePage {

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Buttons')]")
    private WebElement title;

    @FindBy(xpath = "//button[@id='doubleClickBtn']")
    private WebElement doubleClickButton;

    @FindBy(xpath = "//button[@id='rightClickBtn']")
    private WebElement rightClickButton;

    @FindBy(xpath = "//button[starts-with(text(), 'Click')]")
    private WebElement clickMeButton;

    @FindBy(xpath = "//p[@id='doubleClickMessage']")
    private WebElement doubleClickMessage;

    @FindBy(xpath = "//p[@id='rightClickMessage']")
    private WebElement rightClickMessage;

    @FindBy(xpath = "//p[@id='dynamicClickMessage']")
    private WebElement dynamicClickMessage;

    public ButtonsPage(WebDriver driver) {
        super(driver);
    }

    public ButtonsPage clickOnDoubleClickButton() {
        WebDriverManager.waitForElementVisibility(doubleClickButton);
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).perform();
        log.info("Double click button clicked.");
        WebDriverManager.waitForElementVisibility(doubleClickMessage);
        Assert.assertTrue(doubleClickMessage.isDisplayed());
        return this;
    }

    public ButtonsPage clickOnRightClickButton() {
        WebDriverManager.waitForElementVisibility(rightClickButton);
        Actions actions = new Actions(driver);
        actions.contextClick(rightClickButton).perform();
        log.info("Right click button clicked.");
        WebDriverManager.waitForElementVisibility(rightClickMessage);
        actions.sendKeys(Keys.ESCAPE).perform();
        Assert.assertTrue(rightClickMessage.isDisplayed());
        return this;
    }

    public void clickOnClickMeButton() {
        WebDriverManager.waitForElementVisibility(clickMeButton);
        Actions actions = new Actions(driver);
        actions.click(clickMeButton).perform();
        log.info("Dynamic button clicked.");
        WebDriverManager.waitForElementVisibility(dynamicClickMessage);
        Assert.assertTrue(dynamicClickMessage.isDisplayed());
    }
}
