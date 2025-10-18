package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends BasePage{

    @Getter
    @FindBy(xpath = "//span[@class='text'][contains(text(), 'Alerts')]")
    private WebElement alertsMenuButton;

    public AlertsPage(WebDriver driver) {
        super(driver);
    }
}
