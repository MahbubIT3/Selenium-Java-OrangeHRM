package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(name = "username")
    WebElement usernameInput;
    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(className = "oxd-button")
    WebElement loginButton;

    @FindBy(className = "oxd-alert-content-text")
    WebElement loginAlert;

    @FindBy(className = "orangehrm-login-title")
    WebElement loginTitle;

    WebDriver driver;
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public String actualLoginAlertMessage(){
        return loginAlert.getText();
    }
    public String actualLoginTitle(){
        return loginTitle.getText();
    }
}
