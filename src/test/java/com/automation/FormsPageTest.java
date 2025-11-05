package com.automation;

import com.automation.pages.home.HomePage;
import org.testng.annotations.Test;

public class FormsPageTest extends BaseTest{

    @Test
    public void verifyPracticeFormWithValidData() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnFormsMenuButton()
                .clickOnPracticeFormButton()
                .verifyPracticeFormWithValidData();
    }
}
