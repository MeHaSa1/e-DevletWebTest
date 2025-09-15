package tr.com.turksat.test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tr.com.turksat.runner.Driver;

import java.time.Duration;

public class PharmacyTest {
    private static final Logger logger = Logger.getLogger(PharmacyTest.class);
    WebDriver driver = Driver.getDriver();

    @When("user enters district {string}")
    public void enterDistrict(String district){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement plakaCombo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("plakaKodu")));
        new Select(plakaCombo).selectByVisibleText(district);
        logger.info("User entered district info!");
    }

    @And("user enters date {string}")
    public void enterDate(String date){
        driver.findElement(By.id("nobetTarihi")).sendKeys(date);
        logger.info("User entered date info!");
    }

    @Then("user should see at least one pharmacy")
    public void shouldSeePharmacy(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchTable")));
        if(searchTable.findElements(By.tagName("tr")).isEmpty()) throw new AssertionError("Pharmacy not found!");
        logger.info("User saw the pharmacies!");
    }
}
