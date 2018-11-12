package compare;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ComparisonPage;
import pages.MainPage;
import pages.NotebooksCategoryPage;
import pages.SSDNotebooksMarketPage;
import settings.ChromeSettings;

public class RozetkaTests extends ChromeSettings {

    @Test
    public void compareSSDNotebooks() {
        MainPage mainPage = new MainPage(driver);
        NotebooksCategoryPage notebooksCategoryPage = mainPage.openNotebooksCategoryPage();
        SSDNotebooksMarketPage ssdNotebooksMarketPage = notebooksCategoryPage.openSSDNotebooksMarketPage();
        ComparisonPage comparisonPage = ssdNotebooksMarketPage.compareTwoItems();
        int allParameters = comparisonPage.countDiffers();
        int onlyDifferent = comparisonPage.countOnlyDifferentMode();
        Assert.assertEquals(allParameters, onlyDifferent, "Number of different parameters are not equals!");
    }

}
