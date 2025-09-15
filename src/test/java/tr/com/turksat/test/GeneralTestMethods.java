package tr.com.turksat.test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tr.com.turksat.runner.Driver;

import javax.swing.*;
import java.time.Duration;

public class GeneralTestMethods {
    WebDriver driver;

    @Given("user is in main page")
    public void mainPage(){
        driver = Driver.getDriver();
        driver.get("https://www.turkiye.gov.tr");
    }

    @When("user searches for {string} service")
    public void searchService(String service){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchField")));
        searchField.sendKeys(service);
    }

    @And("user goes to the {string} page recommended to them")
    public void goToPage(String page){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement recommended = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(page)));
        recommended.click();
    }

    @And("user clicks find button")
    public void clickButton(){
        WebElement button = driver.findElement(By.name("btn"));
        Actions act = new Actions(driver);
        act.moveToElement(button).perform();
        button.click();
    }
}
