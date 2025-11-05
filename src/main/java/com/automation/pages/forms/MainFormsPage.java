package com.automation.pages.forms;

import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainFormsPage extends BasePage {

    @Getter
    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Practice')]")
    private WebElement practiceFormButton;

    public MainFormsPage(WebDriver driver) {
        super(driver);
    }

    public PracticeFormPage clickOnPracticeFormButton(){
        practiceFormButton.click();
        PracticeFormPage practiceFormPage = new PracticeFormPage(driver);
        WebDriverManager.waitForElementVisibility(practiceFormPage.getFirstNameInput());
        return practiceFormPage;
    }
}
