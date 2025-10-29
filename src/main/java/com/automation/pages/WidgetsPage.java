package com.automation.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WidgetsPage extends BasePage {
    @Getter
    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Accordian')]")
    private WebElement accordianMenuButton;

    public WidgetsPage(WebDriver driver) {
        super(driver);
    }
}
