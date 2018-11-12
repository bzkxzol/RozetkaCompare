package pages;

import helpers.HelpActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import java.util.ArrayList;
import java.util.List;

public class ComparisonPage implements IPage {

    @FindBy(xpath = ".//*[@class='comparison-t-row']")
    private List<WebElement> comparisonList;

    @FindBy(xpath = ".//*[@href='#only-different']")
    private WebElement onlyDifferentButton;

    private WebDriver driver;
    private HelpActions helpActions;

    public ComparisonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        validatePage();
        helpActions = new HelpActions(driver);
    }

    public int countDiffers() {
        comparisonList.size();
        return parseTableDataAndCountDiffers(comparisonList);
    }

    public int countOnlyDifferentMode() {
        onlyDifferentButton.click();
        return comparisonList.size();
    }


    private int parseTableDataAndCountDiffers(List<WebElement> elementList) {
        List<String> firstItem = new ArrayList<>();
        List<String> secondItem = new ArrayList<>();
        int counter = 0;
        for (WebElement element : elementList) {
            List<WebElement> elements = element.findElements(By.xpath(".//*[@class='comparison-t-cell']"));
            if (elements.size() == 0) {
                elements = element.findElements(By.xpath(".//*[@class='comparison-t-cell valigned-middle']"));
                firstItem.add(elements.get(0).findElement(By.xpath(".//descendant::img")).getAttribute("src"));
                secondItem.add(elements.get(1).findElement(By.xpath(".//descendant::img")).getAttribute("src"));
            } else {
                firstItem.add(elements.get(0).findElement(By.xpath(".//descendant::span")).getText());
                secondItem.add(elements.get(1).findElement(By.xpath(".//descendant::span")).getText());
            }
        }

        for (int i = 0; i < elementList.size(); i++) {
            if (!firstItem.get(i).equals(secondItem.get(i))) {
                counter++;
            }
        }
        return counter;
    }


    @Override
    public void validatePage() {
        if (!driver.getTitle().contains("Сравнение товаров в разделе Ноутбуки")) {
            throw new SkipException("Wrong page!");
        }
    }
}
