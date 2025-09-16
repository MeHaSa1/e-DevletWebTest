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
                URL url = new URL("http://172.18.0.3:4444");
                ChromeOptions opt = new ChromeOptions();
                opt.addArguments("--headless");
                opt.addArguments("--no-sandbox");
                opt.addArguments("--disable-dev-shm-usage");
                driver = new RemoteWebDriver(url, opt);
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
