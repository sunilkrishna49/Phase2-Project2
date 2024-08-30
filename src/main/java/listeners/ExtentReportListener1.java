package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ExtentReportListener1 {

    private static ExtentReports extent;
    private static ExtentTest test;

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReportTestNG.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("PizzaHut Automation Test Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }

    // Automatically create a test before each test method
    @BeforeMethod
//    public void onStart(ITestContext context) {
//        test = extent.createTest(context.getName());
//    }
    public void onStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }
    

    // Automatically log results after each test method
    @AfterMethod
    public void onFinish(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }

        // Flush report to ensure all information is written
        extent.flush();
    }

    // Method to log step manually (if needed)
    public void logStep(String message, boolean status) {
        if (test != null) {
            if (status) {
                test.log(Status.PASS, message);
            } else {
                test.log(Status.FAIL, message);
            }
        }
//            else {
//            System.err.println("ExtentTest is not initialized. Call startTest() before logging steps.");
//        }
    }

    public void flush() {
        extent.flush();
    }
}
