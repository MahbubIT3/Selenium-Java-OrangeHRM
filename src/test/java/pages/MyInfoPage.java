package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class MyInfoPage {
    @FindBy(className = "orangehrm-firstname")
    WebElement firstnameInput;
    @FindBy(className = "orangehrm-middlename")
    WebElement middlenameInput;
    @FindBy(className = "orangehrm-lastname")
    WebElement lastnameInput;
    @FindBy(className = "oxd-input")
    List<WebElement> nicknameInput;

    @FindBy(className = "oxd-button")
    List<WebElement> button;

    @FindBy(className = "oxd-userdropdown-name")
    WebElement nameText;

    public MyInfoPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void changeName(String firstname, String middlename, String lastname, String nickname){
        firstnameInput.sendKeys(Keys.chord(Keys.CONTROL, "a")+Keys.DELETE);
        firstnameInput.sendKeys(firstname);

        middlenameInput.sendKeys(Keys.chord(Keys.CONTROL, "a")+Keys.DELETE);
        middlenameInput.sendKeys(middlename);

        lastnameInput.sendKeys(Keys.chord(Keys.CONTROL, "a")+Keys.DELETE);
        lastnameInput.sendKeys(lastname);

        nicknameInput.get(4).sendKeys(Keys.chord(Keys.CONTROL, "a")+Keys.DELETE);
        nicknameInput.get(4).sendKeys(nickname);
    }

    public void clickSaveBtn(int index){
        button.get(index).click();
    }
    public String actualName(){
        return nameText.getText();
    }
}
