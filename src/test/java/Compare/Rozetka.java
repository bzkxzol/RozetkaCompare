package Compare;

import org.testng.annotations.Test;
import pages.MainPage;
import pages.NotebooksCategoryPage;
import pages.SSDNotebooksMarketPage;
import settings.ChromeSettings;

public class Rozetka extends ChromeSettings{


    @Test
    public void compareSSDNotebooks(){
        MainPage mainPage = new MainPage(driver);
        NotebooksCategoryPage notebooksCategoryPage = mainPage.openNotebooksCategoryPage();
        SSDNotebooksMarketPage ssdNotebooksMarketPage = notebooksCategoryPage.openSSDNotebooksMarketPage();
        ssdNotebooksMarketPage.addFirstTwoItemsToCompare();
        System.out.println("");
    }

}
