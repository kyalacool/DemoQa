package pages.elements;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.WebDriverManager;

public class ElementsPage extends BasePage {

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Text Box')]")
    private WebElement textBoxMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Check Box')]")
    private WebElement checkBoxMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Radio Button')]")
    private WebElement radioButtonMenuButton;

    @Getter
    @FindBy (xpath = "//span[@class='text'][contains(text(), 'Web Tables')]")
    private WebElement webTablesMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Buttons')]")
    private WebElement buttonsMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Links')]")
    private WebElement linksMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Broken Links - Images')]")
    private WebElement brokenLinksMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Upload and Download')]")
    private WebElement uploadAndDownloadMenuButton;

    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Dynamic Properties')]")
    private WebElement dynamicPropertiesMenuButton;

    public ElementsPage(WebDriver driver) {
        super(driver);
    }

    public TextBoxPage clickOnTextBoxMenuButton(){
        WebDriverManager.waitForElementVisibility(textBoxMenuButton);
        textBoxMenuButton.click();
        TextBoxPage textBoxPage = new TextBoxPage(driver);
        WebDriverManager.waitForElementVisibility(textBoxPage.getFullNameInput());
        return textBoxPage;
    }

    public CheckBoxPage clickOnCheckBoxMenuButton(){
        WebDriverManager.waitForElementVisibility(checkBoxMenuButton);
        checkBoxMenuButton.click();
        CheckBoxPage checkBoxPage = new CheckBoxPage(driver);
        WebDriverManager.waitForElementVisibility(checkBoxPage.getTitle());
        return checkBoxPage;
    }

    public RadioButtonPage clickOnRadioButtonMenuButton(){
        WebDriverManager.waitForElementVisibility(radioButtonMenuButton);
        radioButtonMenuButton.click();
        RadioButtonPage radioButtonPage = new RadioButtonPage(driver);
        WebDriverManager.waitForElementVisibility(radioButtonPage.getTitle());
        return radioButtonPage;
    }

    public WebTablesPage clickOnWebTablesMenuButton(){
        WebDriverManager.waitForElementVisibility(webTablesMenuButton);
        webTablesMenuButton.click();
        WebTablesPage webTablesPage = new WebTablesPage(driver);
        WebDriverManager.waitForElementVisibility(webTablesPage.getTitle());
        return webTablesPage;
    }


}
