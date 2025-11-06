package com.automation;

import com.automation.pages.home.HomePage;
import org.testng.annotations.Test;

public class AlertsPageTest extends BaseTest{

    @Test
    public void verifyNewTabAndNewWindowButtonsOnBrowserWindows(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnAlertsMenuButton()
                .clickOnBrowserWindowsSubMenu()
                .verifyNewTabAndNewWindowButton();
    }

    @Test(groups = "onlyFirefox")
    public void verifyNewWindowsMessageButtonOnBrowserWindows(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnAlertsMenuButton()
                .clickOnBrowserWindowsSubMenu()
                .verifyNewWindowsMessageButton();
    }
}
