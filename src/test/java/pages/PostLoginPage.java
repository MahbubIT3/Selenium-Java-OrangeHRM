package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PostLoginPage {
    @FindBy(className = "oxd-userdropdown-tab")
    public WebElement userDropdown;
    @FindBy(className = "oxd-userdropdown-name")
    public WebElement usernameText;
    @FindBy(className = "oxd-userdropdown-link")
    public List<WebElement> logoutOption;

    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> mainMenu;

    public PostLoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLogout(){
        userDropdown.click();
        logoutOption.get(3).click();
    }

    public String actualFirstMenu(){
        return mainMenu.get(0).getText();
    }
    public String actualName(){
        return usernameText.getText();
    }
}
