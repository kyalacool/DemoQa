package com.automation.pages.elements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import com.automation.pages.BasePage;

@Slf4j
public class RadioButtonPage extends BasePage {

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Radio Button')]")
    private WebElement title;

    @FindBy(xpath = "//label[@for='yesRadio']")
    private WebElement yesRadioButton;

    @FindBy(xpath = "//label[@for='impressiveRadio']")
    private WebElement impressiveRadioButton;

    @FindBy(xpath = "//label[@for='noRadio']")
    private WebElement noRadioButton;

    @FindBy(xpath = "//span[@class='text-success']")
    private WebElement choosedResult;

    public RadioButtonPage(WebDriver driver) {
        super(driver);
    }

    private boolean isResultIsNull() {
        return driver.findElements(By.xpath("//p[@class='mt-3']")).isEmpty();
    }

    private boolean isChoosen(String expectedResult) {
        return choosedResult.getText().equals(expectedResult);
    }

    public void verifyRadioButtonsFunctionality() {
        log.info("Checking the radio buttons functionality.");
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(isResultIsNull());
        noRadioButton.click();
        soft.assertTrue(isResultIsNull());
        yesRadioButton.click();
        soft.assertTrue(isChoosen("Yes"));
        impressiveRadioButton.click();
        soft.assertTrue(isChoosen("Impressive"));
        soft.assertAll();
    }
}
