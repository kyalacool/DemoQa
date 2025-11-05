package com.automation.pages.alerts;

import com.automation.pages.home.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FramesPage extends BasePage {

    @Getter
    @FindBy(xpath = "//h1[@class='text-center'][text()='Frames']")
    private WebElement maintTitle;

    public FramesPage(WebDriver driver) {
        super(driver);
    }
}
