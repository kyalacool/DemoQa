package tests;

import org.testng.annotations.Test;
import pages.HomePage;

public class ElementsPageTest extends BaseTest{

    @Test
    void verifyTextBoxWithValidData(){
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnTextBoxMenuButton()
                .verifyTextBoxWithCorrectData();
    }

    @Test
    void verifyTextBoxWithInvalidEmail(){
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnTextBoxMenuButton()
                .verifyTextBoxWithIncorrectEmail();
    }

    @Test
    void verifyCheckBoxFunctionality(){
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnCheckBoxMenuButton()
                .verifyCheckBoxAndToggleFunctionality();
    }

    @Test
    void verifyRadioButtonFunctionality(){
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnRadioButtonMenuButton()
                .verifyRadioButtonsFunctionality();
    }

    @Test
    void verifyWebTablesFunctionality(){
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton()
                .clickOnWebTablesMenuButton()
                .verifyAddFunctionalityWithValidData()
                .verifyEditFunctionality()
                .verifySearchFunctionality()
                .verifyDeleteFuncionality();
    }
}
