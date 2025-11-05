package com.automation;

import com.automation.pages.home.HomePage;
import org.testng.annotations.Test;

public class AlertsPageTest extends BaseTest{

    @Test
    public void verifyBrowserWindow(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickOnAlertsMenuButton()
                .clickOnBrowserWindowsSubMenu()
                .verifyButtons();
    }
}
