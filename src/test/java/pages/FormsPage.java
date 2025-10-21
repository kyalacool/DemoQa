package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormsPage extends BasePage {

    @Getter
    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Practice Form')]")
    private WebElement practiceFormMenuButton;

    public FormsPage(WebDriver driver) {
        super(driver);
    }
}
