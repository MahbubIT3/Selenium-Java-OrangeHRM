package utils;

import org.openqa.selenium.JavascriptExecutor;

import static config.SetupTestEnvironment.driver;

public class Utils {
    public static void pageRefresh(){
        driver.navigate().refresh();
    }

    public static void scrollToWindow(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+x+","+y+")");
    }
}
