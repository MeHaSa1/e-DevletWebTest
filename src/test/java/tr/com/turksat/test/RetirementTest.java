package tr.com.turksat.test;

import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import tr.com.turksat.runner.Driver;

public class RetirementTest {
    private static final Logger logger = Logger.getLogger(RetirementTest.class);
    WebDriver driver = Driver.getDriver();

    @Then("user should be in retirement page")
    public void retirementPage(){
        if(!driver.getCurrentUrl().equals("https://www.turkiye.gov.tr/takasbank-bireysel-emeklilik-islemleri?hizmet=Ekrani")) throw new AssertionError("Wrong page!");
        logger.info("User in retirement page!");
    }
}
