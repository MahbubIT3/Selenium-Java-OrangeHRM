package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;

public class SetupTestEnvironment {
    public static WebDriver driver;
    public static String browser;
    @BeforeAll
    public static void setBrowser() {
        browser = "Chrome";
    }

    @BeforeAll
    public static void setDriver() {
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();

            String downloadPath = "G:/Selenium/JUnitPractice2/src/test/resources/downloads";

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

    public void setSleepTime(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
