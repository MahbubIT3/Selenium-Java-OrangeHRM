package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
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
    @FindBy(className = "oxd-select-option")
    List<WebElement> selectOptions;
    @FindBy(className = "oxd-date-input")
    public List<WebElement> dateInput;

    @FindBy(className = "oxd-calendar-selector-month")
    WebElement monthSelector;
    @FindBy(xpath = "//li[text()=\"February\"]")
    WebElement selectMonth;
    @FindBy(className = "oxd-calendar-selector-year")
    WebElement yearSelector;
    @FindBy(xpath = "//li[text()=\"2000\"]")
    WebElement selectYear;

    @FindBy(xpath = "//div[@class='oxd-calendar-date'][text()= '7']")
    WebElement selectDate;

    @FindBy(className = "oxd-calendar-selector-month-selected")
    WebElement selectedMonth;
    @FindBy(className = "oxd-calendar-selector-year-selected")
    WebElement selectedYear;
    @FindBy(className = "--selected")
    WebElement selectedDate;
    @FindBy(className = "oxd-button")
    List<WebElement> button;

    @FindBy(className = "orangehrm-tabs-item")
    List<WebElement> tabs;
//    @FindBys({})
    @FindBys({
        @FindBy(className = "oxd-table-cell"),
        @FindBy(tagName = "div")
    })
    List<WebElement> tableCells;
    @FindBy(xpath = "//input[@placeholder=\"Type for hints...\"]")
    List<WebElement> autoCompleteTextFields;
    @FindBy(className = "oxd-autocomplete-option")
    WebElement autoCompleteOption;
    @FindBys({
            @FindBy(className = "orangehrm-horizontal-padding"),
            @FindBy(tagName = "span")
    })
    WebElement recordText;
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
        textInputFields.get(4).sendKeys(employeeId);
        imageInput.sendKeys(imagePath);
        toggleSwitch.click();

        textInputFields.get(5).sendKeys(username);
        textInputFields.get(6).sendKeys(password);
        textInputFields.get(7).sendKeys(password);

        formButtons.get(1).click();
    }


    public void addEmployeeAdditionalValidData(int index){
        utils.Utils.scrollToWindow(0,200);
        dropdownFields.get(0).click();
        selectOptions.get(index).click();

        dateInput.get(1).click();
        monthSelector.click();
        selectMonth.click();
        yearSelector.click();
        selectYear.click();
        selectDate.click();
    }

    public void addEmployeeJobDetails(){
        dropdownFields.get(0).click();  //Job Title
        selectOptions.get(24).click();

        dropdownFields.get(1).click();  //Job Category
        selectOptions.get(6).click();

        dropdownFields.get(2).click();  //Sub Unit
        selectOptions.get(3).click();

        dropdownFields.get(3).click();  //Location
        selectOptions.get(3).click();

        dropdownFields.get(4).click();  //Employment Status
        selectOptions.get(3).click();
    }

    public void searchNewEmployee(String firstname) throws InterruptedException {
        autoCompleteTextFields.get(0).sendKeys(firstname);
        Thread.sleep(2000);

        autoCompleteOption.click();
        formButtons.get(1).click();
        Thread.sleep(3000);
    }
    public void clickSaveBtn(int index){
        button.get(index).click();
    }
    public void clickTab(int index){
        tabs.get(5).click();
    }
    public String actualEmployeeName(){
        return employeeNameDisplay.getText();
    }
    public String actualYear(){
        return selectedYear.getText();
    }
    public String actualMonth(){
        return selectedMonth.getText();
    }
    public String actualDate(){
        return selectedDate.getText();
    }

    public String actualRecord(){
        return recordText.getText();
    }
    public String actualEmployeeId(){
        return tableCells.get(2).getText();
    }
    public String actualFirstMiddlename(){
        return tableCells.get(3).getText();
    }
    public String actualLastame(){
        return tableCells.get(4).getText();
    }

}
