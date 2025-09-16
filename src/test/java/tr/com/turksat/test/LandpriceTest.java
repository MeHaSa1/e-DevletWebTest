package tr.com.turksat.test;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tr.com.turksat.runner.Driver;

import java.time.Duration;

public class LandpriceTest {
    WebDriver driver = Driver.getDriver();

    @When("user fills the form with values {string} {string} {string}")
    public void fillForm(String district, String street, String year){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement neighbourhood = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mahalle")));
        new Select(neighbourhood).selectByVisibleText(district);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#caddesokak option"),1));
        new Select(driver.findElement(By.id("caddesokak"))).selectByVisibleText(street);
        new Select(driver.findElement(By.id("yil"))).selectByVisibleText(year);
        Logging.info("User filled area form!");
    }

    @Then("user should see at least one land pricing")
    public void shouldSeePricing(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement searchTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[caption[text()='Arsa Rayiç Değer Bilgisi']]")));
        if(searchTable.findElements(By.tagName("tr")).isEmpty()) throw new AssertionError("Pricing not found!");
        Logging.info("User saw the pricing!");
    }
}
