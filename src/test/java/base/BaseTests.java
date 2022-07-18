package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import pages.StandingsPage;

public class BaseTests {
    private WebDriver driver;
    protected StandingsPage homePage;

    @BeforeClass
    public void SetUp () {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new StandingsPage(driver);


    }
}
