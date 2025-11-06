package com.automation.pages.alerts;

import com.automation.pages.home.BasePage;
import com.automation.utils.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.automation.utils.WebDriverManager.waitForElementVisibility;

@Slf4j
public class MainAlertsPage extends BasePage {

    @Getter
    @FindBy(xpath = "//span[@class='text'][text() = 'Alerts']")
    private WebElement alertsMenuButton;

    @FindBy(xpath = "//span[@class='text'][text() = 'Browser Windows']")
    private WebElement browserWindowsMenuButton;

    @FindBy(xpath = "//span[@class='text'][text()='Frames']")
    private WebElement framesMenuButton;

    @FindBy(xpath = "//span[@class='text'][text()='Nested Frames']")
    private WebElement nestedFramesMenuButton;

        @FindBy(xpath = "//span[@class='text'][text() = 'Modal Dialogs']")
    private WebElement modalDialogsMenuButton;

    public MainAlertsPage(WebDriver driver) {
        super(driver);
    }

    public BrowserWindowsPage clickOnBrowserWindowsSubMenu(){
        browserWindowsMenuButton.click();
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(driver);
        waitForElementVisibility(browserWindowsPage.getNewTabButton());
        log.info("User is on the Browser Windows page.");
        return browserWindowsPage;
    }

    public AlertsPage clickOnAlertsSubMenu(){
        alertsMenuButton.click();
        AlertsPage alertsPage = new AlertsPage(driver);
        waitForElementVisibility(alertsPage.getClickButtonToSeeAlertButton());
        return alertsPage;
    }

    public FramesPage clickOnFramesSubMenu(){
        framesMenuButton.click();
        FramesPage framesPage = new FramesPage(driver);
        waitForElementVisibility(framesPage.getMaintTitle());
        return framesPage;
    }

    public NestedFramesPage clickOnNestedFramesSubMenu(){
        nestedFramesMenuButton.click();
        NestedFramesPage nestedFramesPage = new NestedFramesPage(driver);
        waitForElementVisibility(nestedFramesPage.getMainTitle());
        return  nestedFramesPage;
    }

    public ModalDialogsPage clickOnModalDialogsSubMenu(){
        modalDialogsMenuButton.click();
        ModalDialogsPage modalDialogsPage = new ModalDialogsPage(driver);
        waitForElementVisibility(modalDialogsPage.getSmallModalButton());
        return modalDialogsPage;
    }

}
