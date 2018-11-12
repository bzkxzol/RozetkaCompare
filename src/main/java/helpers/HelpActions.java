package helpers;

import static helpers.Logger.CONSOLE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelpActions {


    private WebDriver driver;

    public HelpActions(WebDriver driver) {
        this.driver = driver;
    }


    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void moveToElement(WebElement element) {
        Logger.info(CONSOLE, "Move to element " + element.getText().replaceAll("\n", " "));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void moveToElementAndClick(WebElement element) {
        Logger.info(CONSOLE, "Move to element " + element.getText().replaceAll("\n", " ") + " and click in it");
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();

    }

    public void clickOnElement(WebElement element) {
        Logger.info(CONSOLE, "Test click on " + element.getText().replaceAll("\n", " "));
        element.click();
    }

    public void timeOut(long millis) {
//        Logger.info(CONSOLE, "Test wait for " + millis + " ms");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
