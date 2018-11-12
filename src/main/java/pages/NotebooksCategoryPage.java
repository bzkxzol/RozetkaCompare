package pages;

import static helpers.Logger.CONSOLE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import helpers.HelpActions;
import helpers.Logger;

public class NotebooksCategoryPage implements IPage {

    @FindBy(xpath = ".//p/a[contains(text(),'Ноутбуки с SSD')]")
    WebElement notebooksSSD;

    private WebDriver driver;
    private HelpActions helpActions;

    public NotebooksCategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        validatePage();
        helpActions = new HelpActions(driver);
        Logger.info(CONSOLE, "Test on Notebooks category page!");
    }

    public SSDNotebooksMarketPage openSSDNotebooksMarketPage() {
        helpActions.clickOnElement(notebooksSSD);
        return new SSDNotebooksMarketPage(driver);
    }

    @Override
    public void validatePage() {
        if (!driver.getTitle().contains("Ноутбуки - Rozetka.ua")) {
            throw new SkipException("Wrong page!");
        }
    }
}
