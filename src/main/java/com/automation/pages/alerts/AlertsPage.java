package com.automation.pages.alerts;

import com.automation.pages.home.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends BasePage {

    @Getter
    @FindBy(xpath = "//button[@id='alertButton']")
    WebElement clickButtonToSeeAlertButton;

    public AlertsPage(WebDriver driver) {
        super(driver);
    }
}
