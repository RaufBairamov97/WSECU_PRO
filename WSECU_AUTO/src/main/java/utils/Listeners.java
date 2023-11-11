package utils;

import java.io.FileNotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Base implements ITestListener {
    ExtentTest test;
    public WebDriver driver;

    ExtentReports extent = ExtentReporterNG.getReportObject();

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public Listeners() throws FileNotFoundException {
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(String.valueOf(Character.toChars(0x1F52C)) + "- Running test: " + result.getMethod().getMethodName()
                + " -----");
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().log(Status.PASS, "Test Passes");
        log.info(result.getMethod().getMethodName() + " Test Passed " + String.valueOf(Character.toChars(0x2705))
                + "\n\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());

        if (driver != null) {

            try {
                log.error("----- Test failed: " + String.valueOf(Character.toChars(0x274C))
                        + result.getMethod().getMethodName()
                        + " -----\n\n");
                driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
                        .get(result.getInstance());
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        extentTest.get().skip(result.getThrowable());
        log.warn("Test Skipped: " + testName);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub

        extent.flush();

    }

}
