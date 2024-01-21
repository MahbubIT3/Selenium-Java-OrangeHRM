package testCases;

import config.SetupTestEnvironment;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PIMPage;
import pages.PostLoginPage;
import utils.Utils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PIMMenuTestCases extends SetupTestEnvironment {
    LoginPage loginPage;
    PostLoginPage postLoginPage;
    PIMPage pimPage;

    @BeforeEach
    public void login() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        setSleepTime(6000);
        loginPage = new LoginPage(driver);

        JSONObject adminData = (JSONObject) Utils.readJSONData("src/test/resources/testdata/loginData.json").get(0);
        loginPage.doLogin(adminData.get("username").toString(),adminData.get("password").toString());
        setSleepTime(6000);
    }

    String firstname = "Ethan";
    String middlename = "James";
    String lastname = "Hunt";

    String employeeId = "100001";
    String imagePath = "G:/Selenium/JUnitPractice2/src/test/resources/images/image_test-1.jpg";
    String username = "Ethan123";
    String password = "Sunbeam1";

    @Test
    @Order(1)
    @DisplayName("Employee should be added in the employee list")
    public void addEmployee() throws InterruptedException {
        postLoginPage = new PostLoginPage(driver);
        postLoginPage.mainMenu.get(1).click();      //Click to PIM Menu
        setSleepTime(10000);

        pimPage = new PIMPage(driver);
        pimPage.clickTopNavMenu(2);
        setSleepTime(5000);

        pimPage.addEmployeeValidData(firstname, middlename, lastname, employeeId, imagePath, username, password);
        setSleepTime(10000);

        assertEquals(firstname+" "+lastname, pimPage.actualEmployeeName());

        pimPage.addEmployeeAdditionalValidData(15);
        utils.Utils.scrollToWindow(0,-100);
        setSleepTime(10000);

        pimPage.clickSaveBtn(0);
        setSleepTime(10000);
        utils.Utils.scrollToWindow(0,-300);

        pimPage.dateInput.get(1).click();
        setSleepTime(2000);

        assertEquals("February",pimPage.actualMonth());
        assertEquals("2000",pimPage.actualYear());
        assertEquals("7",pimPage.actualDate());
        setSleepTime(10000);
        utils.Utils.scrollToWindow(0,-400);

        pimPage.clickTab(5);
        setSleepTime(6000);

        pimPage.addEmployeeJobDetails();
        pimPage.clickSaveBtn(0);
        setSleepTime(2000);

        pimPage.clickTopNavMenu(1);
        setSleepTime(8000);

        pimPage.searchNewEmployee(firstname);
        assertEquals("(1) Record Found",pimPage.actualRecord());
        setSleepTime(5000);
        utils.Utils.scrollToWindow(0,300);

        assertEquals(employeeId,pimPage.actualEmployeeId());
        assertEquals(firstname+" "+middlename,pimPage.actualFirstMiddlename());
        assertEquals(lastname,pimPage.actualLastame());
    }


}
