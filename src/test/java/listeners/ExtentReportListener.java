package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import stepsDef.Hooks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportListener {

    public static ExtentReports extent;
    public static ExtentTest scenarioTest;
    public static ExtentTest stepTest;
    public static WebDriver driver = Hooks.getDriver();

    
    public void setup(Scenario scenario) {
        // Initialize ExtentReports and attach the ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/extent-report.html");
        sparkReporter.config().setDocumentTitle("Automation Report"); // Title of the report
        sparkReporter.config().setReportName("Functional Testing"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Tester");
        extent.setSystemInfo("OS", "Windows10");
        extent.setSystemInfo("Browser", "Chrome");

        // Create a new test entry in the report
        scenarioTest = extent.createTest(scenario.getName());
    }
    
    public void logStep(String stepName, boolean passed) {
        if (passed) {
            stepTest = scenarioTest.createNode(stepName);
            stepTest.log(Status.PASS, stepName + " - PASSED");
        } else {
            stepTest = scenarioTest.createNode(stepName);
            stepTest.log(Status.FAIL, stepName + " - FAILED");
        }
    }

    
    	public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // Capture screenshot if the scenario fails
            String screenshotPath = takeScreenshot(scenario.getName());
            scenarioTest.log(Status.FAIL, "Scenario Failed. Screenshot taken.");
			scenarioTest.addScreenCaptureFromPath(screenshotPath);
        } else {
            scenarioTest.log(Status.PASS, "Scenario Passed");
        }
        
        extent.flush();
        
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to take a screenshot
    public String takeScreenshot(String scenarioName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotName = scenarioName + "_" + timestamp + ".png";
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + screenshotName;

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/target/screenshots/"));
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}

