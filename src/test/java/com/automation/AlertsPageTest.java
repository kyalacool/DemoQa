package com.automation;

import com.automation.pages.home.HomePage;
import org.testng.annotations.Test;

public class AlertsPageTest extends BaseTest{

    @Test
    public void verifyFirstTwoButtonOnBrowserWindows(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnAlertsMenuButton()
                .clickOnBrowserWindowsSubMenu()
                .verifyNewTabButton()
                .verifyNewWindowButton();
    }

    @Test(groups = "onlyFirefox")
    public void verifyLastButtonOnBrowserWindows(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnAlertsMenuButton()
                .clickOnBrowserWindowsSubMenu()
                .verifyNewWindowsMessageButton();
    }
}
