package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.StandingsPage;
import utils.LanguageOptions;

public class BaseTests {
    private WebDriver driver;
    protected StandingsPage homePage;
    protected LanguageOptions languageOptions;

    @BeforeTest
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new StandingsPage(driver);
        languageOptions = new LanguageOptions(driver);


    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
