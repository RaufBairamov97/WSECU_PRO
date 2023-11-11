package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Page;

public class LoginPage extends Page {

    @FindBy(css = ".user-lock-link.button.transparent.button-small-button")
    private WebElement click;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;


    private static final String[] TITLE_WORDS = { "Log", "In" };

    public LoginPage(WebDriver driver) {
        super(driver, TITLE_WORDS);
    }

    public LoginPage setUserName(String text) {
         (userName).clear();
        userName.sendKeys(text);
        return this;
    }

    public LoginPage setPassword(String text) {
        (password).clear();
        password.sendKeys(text);
        return this;
    }

    public LoginPage clickSignIn(){
        (click).click();
        return this;
    }

    public LoginPage clickSubmit(){
        (submitButton).click();
        return this;
    }


}
