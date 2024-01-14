package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PostLoginPage {
    @FindBy(className = "oxd-userdropdown-tab")
    WebElement userDropdown;

    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> logoutOption;

    public PostLoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLogout(){
        userDropdown.click();
        logoutOption.get(3).click();
    }
}
