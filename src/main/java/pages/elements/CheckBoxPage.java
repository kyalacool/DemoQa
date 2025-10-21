package pages.elements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import pages.BasePage;

@Slf4j
public class CheckBoxPage extends BasePage {

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Check Box')]")
    private WebElement title;


    private WebElement getClickableCheckBox(String directoryName) {
        return driver.findElement(By
                .xpath("//span[@class='rct-checkbox' and ./following-sibling::span[contains(text(),'%s')]]"
                        .formatted(directoryName)));
    }

    private boolean isCheckBoxUnchecked(String directoryName) {
        return driver.findElement(By
                        .xpath(("//*[local-name()='svg' and contains(@class,'rct-icon-uncheck')" +
                                " and ../following-sibling::span[contains(text(),'%s')]]")
                                .formatted(directoryName)))
                .isDisplayed();
    }

    private boolean isCheckBoxChecked(String directoryName) {
        return driver.findElement(By
                        .xpath(("//*[local-name()='svg' and contains(@class,'rct-icon-check')" +
                                " and ../following-sibling::span[contains(text(),'%s')]]")
                                .formatted(directoryName)))
                .isDisplayed();
    }

    private WebElement getToogleButton(String directoryName) {
        return driver.findElement(By
                .xpath("//button[@title='Toggle' and ./following-sibling::label//*[contains(text(),'%s')]]"
                        .formatted(directoryName)));
    }

    private boolean isToogleClosed(String directoryName) {
        return driver.findElement(By
                        .xpath("//li[contains(@class,'rct-node-collapsed') and .//span[contains(text(),'%s')]]"
                                .formatted(directoryName)))
                .isDisplayed();
    }

    private boolean isToogleOpened(String directoryName) {
        return driver.findElement(By
                        .xpath("//li[contains(@class,'rct-node-expanded') and .//span[contains(text(),'%s')]]"
                                .formatted(directoryName)))
                .isDisplayed();
    }

    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCheckBoxAndToggleFunctionality() {
        log.info("Checking the radio buttons functionality.");
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(isCheckBoxUnchecked("Home"));
        getClickableCheckBox("Home").click();
        soft.assertTrue(isCheckBoxChecked("Home"));
        soft.assertTrue(isToogleClosed("Home"));
        getToogleButton("Home").click();
        soft.assertTrue(isToogleOpened("Home"));
        soft.assertTrue(isCheckBoxChecked("Desktop"));
        soft.assertTrue(isCheckBoxChecked("Documents"));
        soft.assertTrue(isCheckBoxChecked("Downloads"));
        getClickableCheckBox("Home").click();
        soft.assertTrue(isCheckBoxUnchecked("Home"));
        soft.assertTrue(isCheckBoxUnchecked("Desktop"));
        soft.assertTrue(isCheckBoxUnchecked("Documents"));
        soft.assertTrue(isCheckBoxUnchecked("Downloads"));
        soft.assertAll();
    }
}
