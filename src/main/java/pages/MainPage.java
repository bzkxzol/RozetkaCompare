package pages;

import static helpers.Logger.CONSOLE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import helpers.HelpActions;
import helpers.Logger;

public class MainPage implements IPage {

    @FindBy(xpath = ".//*[@menu_id='2416']")
    private WebElement noteBookAndComputersCategory;

    @FindBy(xpath = ".//*[@class='f-menu-pop-l-i']/a[contains(text(),'Ноутбуки')]")
    private WebElement notebooks;

    private WebDriver driver;
    private HelpActions helpActions;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        validatePage();
        helpActions = new HelpActions(driver);
        Logger.info(CONSOLE, "Test on Main page!");
    }

    private void moveToNotebookCategory() {
        helpActions.timeOut(2000);
        helpActions.moveToElement(noteBookAndComputersCategory);
    }

    public NotebooksCategoryPage openNotebooksCategoryPage() {
        moveToNotebookCategory();
        helpActions.waitForElement(notebooks);
        helpActions.moveToElementAndClick(notebooks);
        return new NotebooksCategoryPage(driver);
    }

    @Override
    public void validatePage() {
        if (!driver.getTitle().contains("Интернет-магазин ROZETKA")) {
            throw new SkipException("Wrong page!");
        }
    }
}
