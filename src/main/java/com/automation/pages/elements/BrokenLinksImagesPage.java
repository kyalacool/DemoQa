package com.automation.pages.elements;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;

public class BrokenLinksImagesPage extends BasePage {

    @Getter
    @FindBy(xpath = "//h1[@class='text-center' and contains(text(),'Broken Links - Images')]")
    private WebElement title;

    @FindBy(xpath = "//p[contains(text(),'Valid Link')]/following-sibling::a")
    private WebElement validLink;

    @FindBy(xpath = "//p[contains(text(),'Broken Link')]/following-sibling::a")
    private WebElement brokenLink;

    @FindBy(xpath = "//p[contains(text(),'Valid image')]/following-sibling::img")
    private WebElement validImage;

    @FindBy(xpath = "//p[contains(text(),'Broken image')]/following-sibling::img")
    private WebElement brokenImage;

    public BrokenLinksImagesPage(WebDriver driver) {
        super(driver);
    }

    public BrokenLinksImagesPage verifyImages() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverManager.waitForElementVisibility(validImage);
        SoftAssert softAssert = new SoftAssert();
        boolean validResult = (Boolean) js.executeScript(
                "return arguments[0].complete && typeof arguments[0] != " +
                        "'undefine' && arguments[0].naturalWidth > 0", validImage);
        softAssert.assertTrue(validResult);
        boolean invalidResult = (Boolean) js.executeScript(
                "return arguments[0].complete && typeof arguments[0] !=" +
                        " 'undefined' && arguments[0].naturalWidth > 0", brokenImage);
        softAssert.assertFalse(invalidResult);
        softAssert.assertAll();
        return this;
    }

    public void verifyLinks() {
        SoftAssert softAssert = new SoftAssert();
        Response validResponse = RestAssured.head(validLink.getAttribute("href"));
        Response brokenResponse = RestAssured.head(brokenLink.getAttribute("href"));
        softAssert.assertTrue(200 <= validResponse.statusCode() && validResponse.statusCode() <= 300);
        softAssert.assertFalse(200 <= brokenResponse.statusCode() && brokenResponse.getStatusCode() <= 300);
        softAssert.assertAll();
    }
}
