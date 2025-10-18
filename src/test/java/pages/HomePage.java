package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.ElementsPage;

import java.util.List;

import static utils.WebDriverManager.waitForElementVisibility;

@Slf4j
public class HomePage extends BasePage{

    @FindBy(xpath = "//div[@class='category-cards']")
    private List<WebElement> categories;

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

    public HomePage printCategories(){
        for(WebElement i : categories){
            System.out.println(i.getText());
        }
        return this;
    }

    public ElementsPage clickOnElementsMenuButton(){
        elementsMenuButton.click();
        ElementsPage elementsPage = new ElementsPage(driver);
        waitForElementVisibility(elementsPage.getWebTablesMenuButton());
        return elementsPage;
    }

    public FormsPage clickOnFormsMenuButton(){
        formsMenuButton.click();
        FormsPage formsPage = new FormsPage(driver);
        waitForElementVisibility(formsPage.getPracticeFormMenuButton());
        return formsPage;
    }

    public AlertsPage clickOnAlertsMenuButton(){
        alertsFrameWindowsMenuButton.click();
        AlertsPage alertsPage = new AlertsPage(driver);
        waitForElementVisibility(alertsPage.getAlertsMenuButton());
        return alertsPage;
    }

    public WidgetsPage clickOnWidgetsMenuButton(){
        widgetsMenuButton.click();
        WidgetsPage widgetsPage = new WidgetsPage(driver);
        waitForElementVisibility(widgetsPage.getAccordianMenuButton());
        return widgetsPage;
    }

    public InteractionPage clickOnInteractionMenuButton(){
        interactionsMenuButton.click();
        InteractionPage interactionPage = new InteractionPage(driver);
        waitForElementVisibility(interactionPage.getSortableMenuButton());
        return interactionPage;
    }

    public BookStoreApplicationPage clickOnBookStoreApplicationMenuButton(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", bookStoreApplicationMenuButton);
        bookStoreApplicationMenuButton.click();
        BookStoreApplicationPage bookStoreApplicationPage = new BookStoreApplicationPage(driver);
        js.executeScript("arguments[0].scrollIntoView();", bookStoreApplicationPage.getBookStoreMenuButton());
        waitForElementVisibility(bookStoreApplicationPage.getBookStoreMenuButton());
        return bookStoreApplicationPage;
    }


}
