package testCases;

import config.SetupTestEnvironment;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PostLoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTestCase extends SetupTestEnvironment {
    LoginPage objectLoginPage;
    PostLoginPage postLoginPage;

    @Order(1)
    @Test
    @DisplayName("User should be logged in to the system as Admin")
    public void validLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        objectLoginPage = new LoginPage(driver);
        setSleepTime(5000);

        objectLoginPage.doLogin("Admin","admin123");
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
