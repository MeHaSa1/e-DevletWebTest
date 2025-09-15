package tr.com.turksat.runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverConfig {
    @Before
    public void setDriver(){
        Driver.getDriver();
    }

    @After
    public void quitDriver(){
        Driver.quitDriver();
    }
}
