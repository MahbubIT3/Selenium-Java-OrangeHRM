package testCases;

import config.SetupTestEnvironment;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.MyInfoPage;
import pages.PostLoginPage;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyInfo_TestCases extends SetupTestEnvironment {
    LoginPage loginPage;
    PostLoginPage postLoginPage;
    MyInfoPage myInfoPage;

    @BeforeEach
    public void login(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        setSleepTime(5000);
        loginPage.doLogin("Admin","admin123");
        setSleepTime(5000);
    }

    @Test
    @Order(5)
    @DisplayName("User name should be changed")
    public void changeName() {

        postLoginPage = new PostLoginPage(driver);
        postLoginPage.mainMenu.get(5).click();
        setSleepTime(5000);

        myInfoPage = new MyInfoPage(driver);
        myInfoPage.changeName("Lawrence","Griffin","Larry","Larry");
        setSleepTime(5000);

        Utils.scrollToWindow(0,300);
        myInfoPage.clickSaveBtn(0);
        Utils.pageRefresh();
        setSleepTime(10000);

        assertEquals("Lawrence Larry",myInfoPage.actualName());
    }
}
