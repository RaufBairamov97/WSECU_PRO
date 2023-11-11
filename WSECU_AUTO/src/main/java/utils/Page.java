package utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class Page extends Base{
    protected static int DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = 15;
    protected static WebDriver driver;
   // public static Logger log = LogManager.getLogger(Page.class.getName());

    protected Page(WebDriver driver, String[] TITLE_WORDS) {
        this.driver = driver;
        assert this.driver != null;

        PageFactory.initElements(this.driver, this);
    }

//    protected WebElement waitFor(WebElement element) {
//        WebDriverWait wait = new WebDriverWait(driver,DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT );
//        wait.ignoring(StaleElementReferenceException.class);
//        return wait.until(elementToBeClickable(element));
//    }
}
