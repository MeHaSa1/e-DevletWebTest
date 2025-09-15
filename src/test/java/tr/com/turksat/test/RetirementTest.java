package tr.com.turksat.test;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import tr.com.turksat.runner.Driver;

public class RetirementTest {
    WebDriver driver = Driver.getDriver();

    @Then("user should be in retirement page")
    public void retirementPage(){
        if(!driver.getCurrentUrl().equals("https://www.turkiye.gov.tr/takasbank-bireysel-emeklilik-islemleri?hizmet=Ekrani")) throw new AssertionError("Wrong page!");
    }
}
