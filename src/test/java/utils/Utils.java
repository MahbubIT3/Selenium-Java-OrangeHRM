package utils;

import config.EmployeeDataModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;

import java.io.*;

import static config.SetupTestEnvironment.driver;

public class Utils {
    public static void saveEmployeeData(EmployeeDataModel model) throws IOException, ParseException {
        String file = "G:/Selenium/JUnitPractice2/src/test/resources/testdata/employeeData.json";
        JSONParser jsonParser = new JSONParser();
        File fileObj = new File(file);
        JSONArray empData;
        if(fileObj.length()==0){
            empData = new JSONArray();
        }
        else {
           empData = (JSONArray) jsonParser.parse(new FileReader(file));
        }

        JSONObject empObj = new JSONObject();

        empObj.put("employeeId", model.getEmployeeId());
        empObj.put("firstname", model.getFirstname());
        empObj.put("middlename", model.getMiddlename());
        empObj.put("lastname", model.getLastname());
        empObj.put("username", model.getUsername());
        empObj.put("password", model.getPassword());
        empObj.put("jobTitle", model.getJobTitle());

        empData.add(empObj);

        FileWriter writer = new FileWriter(file);
        writer.write(empData.toJSONString());
        writer.flush();
        writer.close();
    }
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
