package settings;

import static helpers.Logger.CONSOLE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import helpers.ConfigProperties;
import helpers.Logger;

public class ChromeSettings {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("test.driver.location").trim());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //добавить из обработку из файла
        driver.get(ConfigProperties.getProperty("test.url").trim());
        Logger.info(CONSOLE, "Test started!");
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
        Logger.info(CONSOLE, "Test ended!");
    }
}
