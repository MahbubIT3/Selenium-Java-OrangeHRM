package testCases;

import config.SetupTestEnvironment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PostLoginPage;
import utils.Utils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTestCase extends SetupTestEnvironment {
    LoginPage objectLoginPage;
    PostLoginPage postLoginPage;

    @Order(1)
    @Test
    @DisplayName("User should be logged in to the system as Admin")
    public void validLogin() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        objectLoginPage = new LoginPage(driver);
        setSleepTime(5000);

        JSONObject adminData = (JSONObject) Utils.readJSONData("src/test/resources/testdata/loginData.json").get(0);
        objectLoginPage.doLogin(adminData.get("username").toString(),adminData.get("password").toString());
        setSleepTime(5000);

        postLoginPage = new PostLoginPage(driver);
        String actual = postLoginPage.actualFirstMenu();
        String expected = "Admin";

        assertEquals(expected,actual);
    }

    @Order(2)
    @Test
    @DisplayName("User should be logged out")
    public void logout(){
        postLoginPage = new PostLoginPage(driver);
        postLoginPage.doLogout();
        setSleepTime(5000);

        objectLoginPage = new LoginPage(driver);
        String actual = objectLoginPage.actualLoginTitle();
        String expected = "Login";

        assertEquals(expected,actual);
    }

    @Order(3)
    @Test
    @DisplayName("User should not be logged in with wrong password to the system as Admin")
    public void wrongPasswordLogin(){
        objectLoginPage = new LoginPage(driver);
        objectLoginPage.doLogin("Admin","admin12");
        setSleepTime(5000);

        String actual = objectLoginPage.actualLoginAlertMessage();
        String expected = "Invalid credentials";

        assertEquals(expected,actual);
    }
    @Order(4)
    @Test
    @DisplayName("User should not be logged in with wrong username to the system as Admin")
    public void wrongUsernameLogin(){
        objectLoginPage = new LoginPage(driver);
        objectLoginPage.doLogin("Admin222","admin123");
        setSleepTime(5000);

        String actual = objectLoginPage.actualLoginAlertMessage();
        String expected = "Invalid credentials";

        assertEquals(expected,actual);
    }
}
