package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElementsPage extends BasePage{

    @Getter
    @FindBy (xpath = "//span[@class='text'][contains(text(), 'Web Tables')]")
    WebElement webTablesMenuButton;

    public ElementsPage(WebDriver driver) {
        super(driver);
    }
}
