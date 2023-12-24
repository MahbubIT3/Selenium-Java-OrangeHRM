import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.File;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrangeHRM {
    String s = Keys.chord(Keys.CONTROL, "a");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    static String browser;
    static WebDriver driver;


    @BeforeAll
    public static void setBrowser() {
        browser = "Chrome";
    }

    @BeforeAll
    public static void setDriver() {
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();

            String downloadPath = "G:\\Selenium\\JUnitPractice2\\src\\test\\resources\\downloads";

            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.default_directory",downloadPath);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs",chromePrefs);

            driver = new ChromeDriver(options);
        } else if (browser.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }

    @BeforeEach
    public void setWindowSize() {
        if (driver != null) {
            driver.manage().window().maximize();
            setSleepTime(3000);
        }
    }

    private void setSleepTime(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Order(1)
    @Test
    @DisplayName("Verify Website Title")
    void titleTest() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        String websiteTitle = driver.getTitle();
        assertEquals("OrangeHRM",websiteTitle);
        setSleepTime(3000);
    }

    @Order(2)
    @Test
    @DisplayName("Verify Login URL")
    void loginUrlTest() {
        String loginURl = driver.getCurrentUrl();
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login",loginURl);
        setSleepTime(3000);
    }

    @Order(2)
    @Test
    @DisplayName("Verify Logged in")
    void login() {
        WebElement username = driver.findElement(By.xpath("//input[@name=\"username\"]"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.xpath("//input[@name=\"password\"]"));
        password.sendKeys("admin123");

        WebElement submit = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        submit.click();
        setSleepTime(3000);

        String indexUrl = driver.getCurrentUrl();
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",indexUrl);
        setSleepTime(3000);
    }

    @Order(3)
    @Test
    @DisplayName("Verify language")
    void changeLanguage(){
        WebElement adminMenu = driver.findElement(By.xpath("//ul[@class=\"oxd-main-menu\"]/li[1]"));
        adminMenu.click();
        setSleepTime(3000);

        WebElement configuration = driver.findElement(By.xpath("//nav[@class=\"oxd-topbar-body-nav\"]/ul/li[7]"));
        configuration.click();
        setSleepTime(3000);

        WebElement localization = configuration.findElement(By.xpath("./child::ul/li[3]"));
        localization.click();
        setSleepTime(3000);


        WebElement language = driver.findElement(By.xpath("//form[@class=\"oxd-form\"]/div[1]/div/div/div/div[2]/div/div/div[1]"));
        String currentLanguage = language.getText();
        if(!currentLanguage.contains("English")){
            WebElement languageSelector = driver.findElement(By.xpath("//form[@class=\"oxd-form\"]/div[1]/div/div/div/div[2]/div/div/div[2]"));
            languageSelector.click();
            setSleepTime(5000);

            WebElement selectLanguage = driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[4]"));
            selectLanguage.click();
            setSleepTime(3000);

            WebElement save = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
            save.click();
            setSleepTime(3000);
            String selectedLanguage = language.getText();
            assertEquals("English (United States)",selectedLanguage);
        }
        else {
            assertEquals("English (United States)",currentLanguage);
        }
    }

    @Order(4)
    @Test
    @DisplayName("Verify changes of Firstname, Lastname and Nickname")

    void changeName(){
        WebElement myInfoMenu = driver.findElement(By.xpath("//ul[@class=\"oxd-main-menu\"]/li[6]"));
        myInfoMenu.click();
        setSleepTime(3000);

        WebElement firstName = driver.findElement(By.xpath("//div[@class=\"oxd-input-group\"]/div[2]/div[1]/div[2]/input"));
        firstName.sendKeys(s);
        firstName.sendKeys(Keys.DELETE);
        firstName.sendKeys("TestF");
        setSleepTime(3000);

        WebElement lastName = driver.findElement(By.xpath("//div[@class=\"oxd-input-group\"]/div[2]/div[3]/div[2]/input"));
        lastName.sendKeys(s);
        lastName.sendKeys(Keys.DELETE);
        lastName.sendKeys("TestL");
        setSleepTime(3000);

        WebElement nickName = driver.findElement(By.xpath("//label[text()=\"Nickname\"]/parent::div/following-sibling::div/input"));
        nickName.sendKeys(s);
        nickName.sendKeys(Keys.DELETE);
        nickName.sendKeys("TestN");
        setSleepTime(3000);



        WebElement savePersonalDetails = driver.findElement(By.xpath("//form[@class=\"oxd-form\"]/div[5]/button"));
        savePersonalDetails.click();
        setSleepTime(6000);

        String expectedName = "TestF"+" "+"TestL";

        driver.navigate().refresh();

        setSleepTime(3000);
        String name = driver.findElement(By.xpath("//p[@class=\"oxd-userdropdown-name\"]")).getText();
        assertEquals(name,expectedName);
    }

    @Order(5)
    @Test
    @DisplayName("Verify Date of Birth")
    @Disabled
    void birthDate(){
        for (int i = 1; i <= 10; i++) {
            js.executeScript("window.scrollBy(0,50)");
            setSleepTime(100);
        }
        WebElement birthDateSelector = driver.findElement(By.xpath("//label[text()=\"Date of Birth\"]/parent::div/following-sibling::div/div/div"));
        birthDateSelector.click();
        setSleepTime(2000);

        WebElement monthSelector = driver.findElement(By.xpath("//li[@class=\"oxd-calendar-selector-month\"]"));
        monthSelector.click();
        setSleepTime(2000);

        WebElement selectMonth = driver.findElement(By.xpath("//li[text()=\"January\"]"));
        selectMonth.click();
        setSleepTime(2000);

        WebElement yearSelector = driver.findElement(By.xpath("//li[@class=\"oxd-calendar-selector-year\"]"));
        yearSelector.click();
        setSleepTime(2000);

        WebElement selectYear = driver.findElement(By.xpath("//li[text()=\"1995\"]"));
        selectYear.click();
        setSleepTime(2000);

        WebElement selectDate = driver.findElement(By.xpath("//div[@class='oxd-calendar-date'][text()= '7']"));
        selectDate.click();
        setSleepTime(2000);

        for (int i = 1; i <= 7; i++) {
            js.executeScript("window.scrollBy(0,-50)");
            setSleepTime(100);
        }

        WebElement savePersonalDetails = driver.findElement(By.xpath("//form[@class=\"oxd-form\"]/div[5]/button"));
        savePersonalDetails.click();
        setSleepTime(6000);
    }

    @Order(6)
    @Test
    @DisplayName("Verify upload an attachment")
    void attachment(){
        String filePath = "G:\\Selenium\\JUnitPractice2\\src\\test\\resources\\images\\image_test-1.jpg";

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); //Scroll to the end of the page
        setSleepTime(2000);

        WebElement add = driver.findElement(By.xpath("//h6[text()=\"Attachments\"]/following-sibling::button"));
        add.click();

        setSleepTime(3000);
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys(filePath);

        setSleepTime(2000);
        WebElement textArea = driver.findElement(By.xpath("//div/label[text()=\"Comment\"]/parent::div/following-sibling::div/textarea"));
        textArea.sendKeys("Image of a quote for introverts");

        WebElement save = driver.findElement(By.xpath("//h6[text()=\"Add Attachment\"]/following-sibling::form/div/button[@type=\"submit\"]"));
        save.click();
        setSleepTime(5000);

        WebElement fileName = driver.findElement(By.xpath("//div[@class=\"oxd-table-card\"]/div/div[2]/div"));
        String actualFileName = fileName.getText();

        assertEquals("image_test-1.jpg",actualFileName);

        setSleepTime(3000);
    }

    @Order(7)
    @Test
    @DisplayName("Download a File")
    void download(){
        WebElement download = driver.findElement(By.xpath("//*[@id=\"app\"]//button[3]"));
        download.click();
        setSleepTime(5000);
        String downloadPath = "G:\\Selenium\\JUnitPractice2\\src\\test\\resources\\downloads";
        File downloadedFile = new File(downloadPath + File.separator + "image_test-1.jpg");

        boolean isFileDownloaded = downloadedFile.exists();
        assertTrue(isFileDownloaded, "The file was not downloaded successfully.");

        setSleepTime(3000);
    }

    @Order(8)
    @Test
    @DisplayName("Delete a File")
    void deleteFile(){
        WebElement delete = driver.findElement(By.xpath("//*[@id=\"app\"]//button[2]"));
        delete.click();
        setSleepTime(2000);

        WebElement yesDelete = driver.findElement(By.xpath("//div[@class=\"orangehrm-modal-footer\"]/button[2]"));
        yesDelete.click();
        setSleepTime(7000);

        String records = driver.findElement(By.xpath("//div[@class=\"orangehrm-attachment\"]/div/div/span")).getText();
        assertEquals("No Records Found",records);
    }
    @AfterAll
    public static void tearDown() {
        // Close the driver in the tearDown method
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
