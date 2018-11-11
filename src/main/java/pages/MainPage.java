package pages;

import helpers.HelpActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

public class MainPage implements IPage{

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
    }

    private void moveToNotebookCategory(){
        helpActions.moveToElement(noteBookAndComputersCategory);
    }

    public NotebooksCategoryPage openNotebooksCategoryPage(){
        moveToNotebookCategory();
        helpActions.waitForElement(notebooks);
        notebooks.click();
        return new NotebooksCategoryPage(driver);
    }

    public void validatePage() {
        if(!driver.getTitle().contains("Интернет-магазин ROZETKA")){
            throw new SkipException("Wrong page!");
        }
    }
}
