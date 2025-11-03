package com.automation;

import com.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyElementsMenuButtonNavigateElementsPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickOnElementsMenuButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/elements");
    }

    @Test
    public void verifyFormsMenuButtonNavigateFormsPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickOnFormsMenuButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/forms");
    }

    @Test
    public void verifyAlertsMenuButtonNavigateAlertsPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickOnAlertsMenuButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/alertsWindows");
    }

    @Test
    public void verifyWidgetsMenuButtonNavigateWidgetsPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickOnWidgetsMenuButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/widgets");
    }

    @Test
    public void verifyInteractionMenuButtonNavigateInteractionPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickOnInteractionMenuButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/interaction");
    }

    @Test
    public void verifyBookStoreApplicationMenuButtonNavigateBookStoreApplicationPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage
                .clickOnBookStoreApplicationMenuButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://demoqa.com/books");
    }
}
