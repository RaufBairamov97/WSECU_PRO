package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public static Logger log = LogManager.getLogger(Base.class.getName());

    public Base() {
        this.driver= driver;
    }

    @BeforeMethod
    public void browserInit() throws InterruptedException {
        log.info("Initializing the Chrome Web Driver instance to Lunch the Browser and Page");
        Helpers helpers = new Helpers();
        WebDriverManager.chromedriver().setup();

        // Initialize the Chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Launch the web page
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://wsecu.org/");
        helpers.sleep(1000);
        log.info("Opening the Web Page");
    }

    @AfterMethod
    public static void killTest(ITestResult result){

        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                String testCasaName = result.getMethod().getMethodName();
                getScreenShotPath(testCasaName);
            } catch (Exception e) {
                // TODO: handle exception
                log.error("Exception while taking screenshot " + e.getMessage());
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
    public static void getScreenShotPath(String testCasaName) throws IOException {
        TakesScreenshot ts = ((TakesScreenshot) driver);
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/ScreenShots/" + testCasaName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
    }

}
