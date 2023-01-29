package pages;

import com.sun.tools.jconsole.JConsoleContext;
import org.company.configReader.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.io.Console;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class JavaScripError {
    private WebDriver driver;
    private SoftAssert softAssert;

    public JavaScripError(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver,this);
    }
    @FindBy(tagName = "script")
    private WebElement error;

    public void verifyJavaScripError() throws InterruptedException {



        navigateToJSErrorPage();


        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logs) {
            String logMessage = log.getMessage();
            if (logMessage.contains("Cannot read properties of undefined (reading 'xyz')")){
                softAssert.assertTrue(true);
                break;
            }


        }


//        Set<String> logtyp = driver.manage().logs().getAvailableLogTypes();
//        for (String s : logtyp) {
//            System.out.println(logtyp);
//
//        }
//        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
//        List<LogEntry> lg = logEntries.getAll();
//
//        for(LogEntry logEntry : lg) {
//
//            System.out.println(logEntry);
//        }
////        var str = "GeeksforGeeks";
////        console.log(str);


    }
    private void navigateToJSErrorPage(){
        driver.get(ConfigReader.getProperty("url")+"/javascript_error ");
    }
}
