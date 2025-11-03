package com.automation.pages.elements;

import com.automation.utils.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.automation.pages.BasePage;
import com.automation.pages.HomePage;

import java.util.ArrayList;
import java.util.List;

import static com.automation.utils.WebDriverManager.*;

@Slf4j
public class LinksPage extends BasePage {

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Links')]")
    private WebElement title;

    @FindBy(xpath = "//a[@id='simpleLink']")
    private WebElement homeLink;

    @FindBy(xpath = "//a[@id='dynamicLink']")
    private WebElement dynamicLink;

    @FindBy(xpath = "//a[@id='created']")
    private WebElement createdLink;

    @FindBy(xpath = "//a[@id='no-content']")
    private WebElement noContentLink;

    @FindBy(xpath = "//a[@id='moved']")
    private WebElement movedLink;

    @FindBy(xpath = "//a[@id='bad-request']")
    private WebElement badRequestLink;

    @FindBy(xpath = "//a[@id='unauthorized']")
    private WebElement unauthorizedLink;

    @FindBy(xpath = "//a[@id='forbidden']")
    private WebElement forbiddenLink;

    @FindBy(xpath = "//a[@id='invalid-url']")
    private WebElement notFoundLink;

    @FindBy(xpath = "//p[@id='linkResponse']")
    private WebElement responseMessage;

    public LinksPage(WebDriver driver) {
        super(driver);
    }

    public LinksPage clickOnHomeLink() {
        String originalTab = driver.getWindowHandle();
        waitForElementVisibility(homeLink);
        homeLink.click();
        log.info("Home link clicked");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        HomePage homePage = new HomePage(driver);
        waitForElementVisibility(homePage.getCategoriesWebElement());
        Assert.assertEquals(driver.getCurrentUrl(), page_url + "/");
        driver.close();
        driver.switchTo().window(originalTab);
        Assert.assertTrue(title.isDisplayed());
        return this;
    }

    public LinksPage clickOnDynamicHomeLink() {
        String originalTab = driver.getWindowHandle();
        waitForElementVisibility(dynamicLink);
        dynamicLink.click();
        log.info("Dynamic link clicked");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        HomePage homePage = new HomePage(driver);
        waitForElementVisibility(homePage.getCategoriesWebElement());
        Assert.assertEquals(driver.getCurrentUrl(), page_url + "/");
        driver.close();
        driver.switchTo().window(originalTab);
        Assert.assertTrue(title.isDisplayed());
        return this;
    }

    public void verifyApiCallLinks() {
        waitForElementVisibility(createdLink);
        createdLink.click();
        waitForElementVisibility(responseMessage);
        By messageRoute = By.xpath("//p[@id='linkResponse']");
        SoftAssert softAssert = new SoftAssert();
        waitUntilTextToBePresent(messageRoute, "201");
        softAssert.assertEquals(responseMessage.getText(), "Link has responded with staus 201 and status text Created");
        scrollTo(noContentLink);
        waitForElementVisibility(noContentLink);
        noContentLink.click();
        waitUntilTextToBePresent(messageRoute, "204");
        softAssert.assertEquals(responseMessage.getText(), "Link has responded with staus 204 and status text No Content");
        scrollTo(movedLink);
        waitForElementVisibility(movedLink);
        movedLink.click();
        waitUntilTextToBePresent(messageRoute, "301");
        softAssert.assertEquals(responseMessage.getText(), "Link has responded with staus 301 and status text Moved Permanently");
        scrollTo(badRequestLink);
        waitForElementVisibility(badRequestLink);
        badRequestLink.click();
        waitUntilTextToBePresent(messageRoute, "400");
        softAssert.assertEquals(responseMessage.getText(), "Link has responded with staus 400 and status text Bad Request");
        scrollTo(unauthorizedLink);
        waitForElementVisibility(unauthorizedLink);
        unauthorizedLink.click();
        waitUntilTextToBePresent(messageRoute, "401");
        softAssert.assertEquals(responseMessage.getText(), "Link has responded with staus 401 and status text Unauthorized");
        scrollTo(forbiddenLink);
        waitForElementVisibility(forbiddenLink);
        forbiddenLink.click();
        waitUntilTextToBePresent(messageRoute, "403");
        softAssert.assertEquals(responseMessage.getText(), "Link has responded with staus 403 and status text Forbidden");
        scrollTo(notFoundLink);
        waitForElementVisibility(notFoundLink);
        notFoundLink.click();
        waitUntilTextToBePresent(messageRoute, "404");
        softAssert.assertEquals(responseMessage.getText(), "Link has responded with staus 404 and status text Not Found");
        softAssert.assertAll();
    }
}
