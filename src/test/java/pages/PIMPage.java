package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-topbar-body-nav-tab")
    List<WebElement> topNavMenus;

    @FindBy(className = "orangehrm-firstname")
    WebElement firstnameInput;

    @FindBy(className = "orangehrm-middlename")
    WebElement middlenameInput;
    @FindBy(className = "orangehrm-lastname")
    WebElement lastnameInput;
    @FindBy(className = "oxd-file-input")
    WebElement imageInput;
    @FindBy(className = "oxd-switch-input")
    WebElement toggleSwitch;

    @FindBy(className = "oxd-input")
    List<WebElement> textInputFields;

    @FindBy(className = "oxd-button")
    List<WebElement> formButtons;
    @FindBy(className = "oxd-select-wrapper")
    List<WebElement> dropdownFields;

    @FindBy(className = "orangehrm-edit-employee-name")
    WebElement employeeNameDisplay;
    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void clickTopNavMenu(int index){
        topNavMenus.get(index).click();
    }
    public void addEmployeeValidData(String firstname, String middlename, String lastname, String employeeId, String imagePath, String username, String password){
        firstnameInput.sendKeys(firstname);
        middlenameInput.sendKeys(middlename);
        lastnameInput.sendKeys(lastname);

        textInputFields.get(4).sendKeys(Keys.chord(Keys.CONTROL, "a")+Keys.DELETE);
        textInputFields.get(4).sendKeys("11111");
        imageInput.sendKeys(imagePath);
        toggleSwitch.click();

        textInputFields.get(5).sendKeys(username);
        textInputFields.get(6).sendKeys(password);
        textInputFields.get(7).sendKeys(password);

        formButtons.get(1).click();
    }

    public void addEmployeeAdditionalValidData(){
        utils.Utils.scrollToWindow(0,200);
        dropdownFields.get(0).click();
    }

    public String actualEmployeeName(){
        return employeeNameDisplay.getText();
    }
}
