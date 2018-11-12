package pages;

import static helpers.Logger.CONSOLE;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import helpers.HelpActions;
import helpers.Logger;

public class SSDNotebooksMarketPage implements IPage {

    @FindBy(xpath = ".//*[@class='g-i-tile g-i-tile-catalog']")
    private List<WebElement> allMarketItems;

    @FindBy(xpath = ".//*[@id='comparison']/a")
    private WebElement comparisonList;

    @FindBy(xpath = ".//*[@class='btn-link-to-compare']/a")
    private WebElement comparisonButton;

    private WebDriver driver;
    private HelpActions helpActions;

    public SSDNotebooksMarketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        validatePage();
        helpActions = new HelpActions(driver);
        Logger.info(CONSOLE, "Test on SSD notebooks market page!");
    }

    private void pickFirstTwoItemsToCompare() {
        List<WebElement> twoMarketItems = allMarketItems.stream().filter(p ->
                p.findElement(By.xpath(".//*[@class='g-i-tile-i-box']/input")).getAttribute("value").equals("001")
                        || p.findElement(By.xpath(".//*[@class='g-i-tile-i-box']/input")).getAttribute("value").equals("002"))
                .collect(Collectors.toList());
        for (WebElement item : twoMarketItems) {
            WebElement subElement = item.findElement(By.xpath(".//*[@class='g-tools-to-compare-label']/span"));
            helpActions.moveToElement(subElement);
            helpActions.timeOut(1000);
            helpActions.clickOnElement(subElement);
            helpActions.timeOut(1000);
        }
    }

    public ComparisonPage compareTwoItems() {
        pickFirstTwoItemsToCompare();
        helpActions.clickOnElement(comparisonList);
        helpActions.waitForElement(comparisonButton);
        helpActions.clickOnElement(comparisonButton);
        return new ComparisonPage(driver);
    }

    @Override
    public void validatePage() {
        if (!driver.getTitle().contains("Ноутбуки с SSD")) {
            throw new SkipException("Wrong page!");
        }
    }
}
