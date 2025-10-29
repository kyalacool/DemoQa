package com.automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyElementsMenuButtonNavigateElementsPage() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnElementsMenuButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/elements");
    }

    @Test
    public void verifyFormsMenuButtonNavigateFormsPage() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnFormsMenuButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/forms");
    }

    @Test
    public void verifyAlertsMenuButtonNavigateAlertsPage() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnAlertsMenuButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/alertsWindows");
    }

    @Test
    public void verifyWidgetsMenuButtonNavigateWidgetsPage() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnWidgetsMenuButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/widgets");
    }

    @Test
    public void verifyInteractionMenuButtonNavigateInteractionPage() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnInteractionMenuButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/interaction");
    }

    @Test
    public void verifyBookStoreApplicationMenuButtonNavigateBookStoreApplicationPage() {
        HomePage homePage = new HomePage(driver);
        homePage
                .clickOnBookStoreApplicationMenuButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/books");
    }
}
