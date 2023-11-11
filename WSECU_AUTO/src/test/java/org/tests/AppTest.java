package org.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.Base;
import utils.Helpers;



public class AppTest extends Base {

    @Test
    public void WSECUTest() throws InterruptedException {

        Helpers helpers = new Helpers();

        browserInit();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignIn();
        helpers.sleep(1500);

        log.info("Passing the user name in user name box on UI ");
        loginPage.setUserName("thisuserwillnotwork");
        helpers.sleep(500);

        log.info("Clicking the Submit Button");
        loginPage.clickSubmit();
        helpers.sleep(1500);

        log.info("Verify the redirection to the WSECU Online Banking web site");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://digital.wsecu.org/banking/signin")) {
            System.out.println("Redirected to the WSECU Online Banking web site.");
        } else {
            System.out.println("Redirection to the WSECU Online Banking web site failed.");
        }

      log.info("Passing the password data into password section on the website");
      loginPage.setPassword("thispasswillfailforsure").
      clickSubmit();
      helpers.sleep(1500);

        log.info("Grabbing Error Text Message from the error pop up on UI");
        WebElement errorMessageElement = driver.findElement(By.xpath("//div[contains(text(),'Sorry, incorrect username.')]"));
        String expectedErrorMessage = "Sorry, incorrect username.";
        String actualErrorMessage = errorMessageElement.getText();

        log.info("Asserting for Correct Error message of 'Sorry, incorrect username.' from the UI ");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");
    }

    //@Test
    public void test(){
        Assert.fail("Failed on purpose");
    }
}
