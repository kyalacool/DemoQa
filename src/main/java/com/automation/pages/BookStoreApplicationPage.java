package com.automation.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookStoreApplicationPage extends BasePage {

    @Getter
    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Book Store')]")
    private WebElement bookStoreMenuButton;

    public BookStoreApplicationPage(WebDriver driver) {
        super(driver);
    }
}
