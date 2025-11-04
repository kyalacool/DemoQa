package com.automation.pages.home;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InteractionPage extends BasePage {

    @Getter
    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Sortable')]")
    private WebElement sortableMenuButton;

    public InteractionPage(WebDriver driver) {
        super(driver);
    }
}
