package testCases;

import config.SetupTestEnvironment;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PostLoginPage;
import utils.Utils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeLoginTestCases extends SetupTestEnvironment {

    LoginPage objectLoginPage;
    PostLoginPage postLoginPage;

    @Order(1)
    @Test
    @DisplayName("User should be logged in to the system as Employee")
    public void validLogin() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        objectLoginPage = new LoginPage(driver);
        setSleepTime(5000);

        JSONObject employeeData = (JSONObject) Utils.readJSONData("src/test/resources/testdata/employeeData.json").get(0);
        objectLoginPage.doLogin(employeeData.get("username").toString(), employeeData.get("password").toString());
        setSleepTime(5000);

        postLoginPage = new PostLoginPage(driver);
        String actual = postLoginPage.actualName();
        String expected = employeeData.get("firstname").toString()+" "+employeeData.get("lastname");

        assertEquals(expected, actual);
        setSleepTime(10000);
    }
}