package pages.elements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.BasePage;
import utils.WebDriverManager;

import java.util.List;

@Slf4j
public class DynamicPropertiesPage extends BasePage {

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Dynamic Properties')]")
    private WebElement title;

    @FindBy(xpath = "//p[starts-with(text(),'This text has random Id')]")
    private WebElement randomIdText;

    @FindBy(xpath = "//button[@id='enableAfter']")
    private WebElement enableAfterButton;

    @FindBy(xpath = "//button[@id='colorChange']")
    private WebElement colorChangeButton;

    @FindBy(xpath = "//button[@id='visibleAfter']")
    private WebElement visibleAfterButton;

    public DynamicPropertiesPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDynamicPropertiesFunctionality() {
        List<WebElement> hiddedButtons = driver.findElements(By.xpath("//button[@id='visibleAfter']"));
        Assert.assertTrue(hiddedButtons.isEmpty());
        String colorOfButtonBefore = colorChangeButton.getCssValue("color");
        WebDriverManager.waitForElementClickable(enableAfterButton);
        enableAfterButton.click();
        log.info("Clicked the button which is enabled later.");
        String colorOfButtonAfter = colorChangeButton.getCssValue("color");
        Assert.assertNotEquals(colorOfButtonBefore, colorOfButtonAfter);
        WebDriverManager.waitForElementVisibility(visibleAfterButton);
        visibleAfterButton.click();
        log.info("Clicked the button which is visible later.");
        Assert.assertTrue(visibleAfterButton.isDisplayed());
    }
}
