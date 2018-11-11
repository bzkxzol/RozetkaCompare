package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

public class NotebooksCategoryPage implements IPage {

    @FindBy(xpath = ".//p/a[contains(text(),'Ноутбуки с SSD')]")
    WebElement notebooksSSD;

    private WebDriver driver;

    public NotebooksCategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        validatePage();
    }

    public SSDNotebooksMarketPage openSSDNotebooksMarketPage() {
        notebooksSSD.click();
        return new SSDNotebooksMarketPage(driver);
    }

    @Override
    public void validatePage() {
        if (!driver.getTitle().contains("Ноутбуки - Rozetka.ua")) {
            throw new SkipException("Wrong page!");
        }
    }
}
