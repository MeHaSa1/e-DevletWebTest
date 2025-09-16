package tr.com.turksat.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Driver {
    private static WebDriver driver;

    private Driver(){}

    public static WebDriver getDriver(){
        if(driver == null){
            try {
                URL url = new URL("http://localhost:4444/wd/hub");
                driver = new RemoteWebDriver(url, new ChromeOptions());
                driver.manage().window().maximize();
            }
            catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
        return driver;
    }

    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
