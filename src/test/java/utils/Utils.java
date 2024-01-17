package utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static config.SetupTestEnvironment.driver;

public class Utils {

    public static JSONArray readJSONData(String fileName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray userData = (JSONArray) jsonParser.parse(new FileReader(fileName));
        return userData;
    }

    public static void pageRefresh(){
        driver.navigate().refresh();
    }

    public static void scrollToWindow(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+x+","+y+")");
    }
}
