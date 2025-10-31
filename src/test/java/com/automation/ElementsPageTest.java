package com.automation;

import org.testng.annotations.Test;
import com.automation.pages.HomePage;

public class ElementsPageTest extends BaseTest {

    @Test
    void verifyTextBoxWithValidData() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnTextBoxMenuButton()
                .verifyTextBoxWithCorrectData();
    }

    @Test
    void verifyTextBoxWithInvalidEmail() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnTextBoxMenuButton()
                .verifyTextBoxWithIncorrectEmail();
    }

    @Test
    void verifyCheckBoxFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnCheckBoxMenuButton()
                .verifyCheckBoxAndToggleFunctionality();
    }

    @Test
    void verifyRadioButtonFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnRadioButtonMenuButton()
                .verifyRadioButtonsFunctionality();
    }

    @Test
    void verifyWebTablesFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnWebTablesMenuButton()
                .verifyAddFunctionalityWithInvalidData()
                .verifyAddFunctionalityWithValidData()
                .verifyEditFunctionality()
                .verifySearchFunctionality()
                .verifyDeleteFuncionality();
    }

    @Test
    public void verifyButtonsFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnButtonsMenuButton()
                .clickOnDoubleClickButton()
                .clickOnRightClickButton()
                .clickOnClickMeButton();
    }

    @Test
    public void verifyLinksFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnLinksMenuButton()
                .clickOnHomeLink()
                .clickOnDynamicHomeLink()
                .verifyApiCallLinks();
    }

    @Test
    public void verifyBrokenLinksImagesPage() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnBrokenLinksImagesMenuButton()
                .verifyImages()
                .verifyLinks();
    }

    @Test(groups = "download")
    public void verifyDownloadUploadPageFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnUploadDownloadMenuButton()
                .verifyDownloadFunctionality();
    }

    @Test
    public void verifyDynamicPropertiesPageFunctionality() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnDynamicPropertiesMenuButton()
                .verifyDynamicPropertiesFunctionality();
    }
}
