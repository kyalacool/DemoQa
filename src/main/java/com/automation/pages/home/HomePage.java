package com.automation.pages.home;

import com.automation.pages.alerts.MainAlertsPage;
import com.automation.pages.elements.MainElementsPage;
import com.automation.pages.forms.MainFormsPage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.automation.utils.WebDriverManager.waitForElementVisibility;


@Slf4j
public class HomePage extends BasePage {

    @Getter
    @FindBy(xpath = "//div[@class='category-cards']")
    private WebElement categoriesWebElement;

    @FindBy(xpath = "//div[@class='category-cards']")
    private List<WebElement> categoriesWebElementList;

    @Getter
    @FindBy(xpath = "//div[@class='card-body']/h5[contains(text(),'Elements')]/../..")
    private WebElement elementsMenuButton;

    @FindBy(xpath = "//div[@class='card-body']/h5[contains(text(),'Forms')]/../..")
    private WebElement formsMenuButton;

    @FindBy(xpath = "//div[@class='card-body']/h5[contains(text(),'Alerts, Frame & Windows')]/../..")
    private WebElement alertsFrameWindowsMenuButton;

    @FindBy(xpath = "//div[@class='card-body']/h5[contains(text(),'Widgets')]/../..")
    private WebElement widgetsMenuButton;

    @FindBy(xpath = "//div[@class='card-body']/h5[contains(text(),'Interactions')]/../..")
    private WebElement interactionsMenuButton;

    @FindBy(xpath = "//div[@class='card-body']/h5[contains(text(),'Book Store Application')]/../..")
    private WebElement bookStoreApplicationMenuButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage printCategories() {
        for (WebElement i : categoriesWebElementList) {
            System.out.println(i.getText());
        }
        return this;
    }

    public MainElementsPage clickOnElementsMenuButton() {
        elementsMenuButton.click();
        MainElementsPage elementsPage = new MainElementsPage(driver);
        waitForElementVisibility(elementsPage.getWebTablesMenuButton());
        return elementsPage;
    }

    public MainFormsPage clickOnFormsMenuButton() {
        formsMenuButton.click();
        MainFormsPage formsPage = new MainFormsPage(driver);
        waitForElementVisibility(formsPage.getPracticeFormButton());
        log.info("User is on the FORMS page");
        return formsPage;
    }

    public MainAlertsPage clickOnAlertsMenuButton() {
        alertsFrameWindowsMenuButton.click();
        MainAlertsPage alertsPage = new MainAlertsPage(driver);
        waitForElementVisibility(alertsPage.getAlertsMenuButton());
        log.info("User is on the ALERTS, FRAME & WINDOWS page");
        return alertsPage;
    }

    public WidgetsPage clickOnWidgetsMenuButton() {
        widgetsMenuButton.click();
        WidgetsPage widgetsPage = new WidgetsPage(driver);
        waitForElementVisibility(widgetsPage.getAccordianMenuButton());
        log.info("User is on the WIDGETS page");
        return widgetsPage;
    }

    public InteractionPage clickOnInteractionMenuButton() {
        interactionsMenuButton.click();
        InteractionPage interactionPage = new InteractionPage(driver);
        waitForElementVisibility(interactionPage.getSortableMenuButton());
        log.info("User is on the INTERACTIONS page");
        return interactionPage;
    }

    public BookStoreApplicationPage clickOnBookStoreApplicationMenuButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", bookStoreApplicationMenuButton);
        bookStoreApplicationMenuButton.click();
        BookStoreApplicationPage bookStoreApplicationPage = new BookStoreApplicationPage(driver);
        js.executeScript("arguments[0].scrollIntoView();", bookStoreApplicationPage.getBookStoreMenuButton());
        waitForElementVisibility(bookStoreApplicationPage.getBookStoreMenuButton());
        log.info("User is on the BOOK STORE APPLICATION page");
        return bookStoreApplicationPage;
    }
}
