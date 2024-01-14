package testCases;

import config.SetupTestEnvironment;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
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
    public void validLogin() throws Exception{
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        objectLoginPage = new LoginPage(driver);
        setSleepTime(5000);
        objectLoginPage.doLogin("Admin","admin123");
        setSleepTime(10000);
    }

    @Order(2)
    @Test
    @DisplayName("User should be logged out")
    public void logout() throws Exception{
        postLoginPage = new PostLoginPage(driver);
        postLoginPage.doLogout();
        setSleepTime(10000);

    }

    @Order(3)
    @Test
    @DisplayName("User should not be logged in with wrong credentials to the system as Admin")
    public void inValidLogin() throws Exception{
        objectLoginPage = new LoginPage(driver);
        setSleepTime(5000);
        objectLoginPage.doLogin("Admin","admin12");
        setSleepTime(10000);

        String actual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String expected = "Invalid credentials";

        assertEquals(actual,expected);
    }

    @AfterAll
    public static void finishTest(){
        driver.close();
        driver.quit();
    }
}
