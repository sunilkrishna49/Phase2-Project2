package stepsDef;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import listeners.ExtentReportListener;

public class Hooks {
	
	ExtentReportListener reportListener = new ExtentReportListener();

    public static WebDriver driver; //declares a webdriver instance as a static variable,so that it will share across all instances of the Hooks class;

    @Before
    public void beforeScenario(Scenario scenario) {
    	reportListener.setup(scenario);
        System.out.println("Setting up the browser before the scenario");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException, InterruptedException {
    	reportListener.tearDown(scenario);
        System.out.println("Tearing down the browser after the scenario");
        if (driver != null) {
        	Thread.sleep(3000);
            driver.quit();
        }
    }

    public static WebDriver getDriver() {  //because it is a static method you can call Hooks.getDriver() from anywhere in your project to get access to the browser instance
        return driver;
    }
}
