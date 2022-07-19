package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.StandingsPage;
import utils.LanguageOptions;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    private WebDriver driver;
    protected StandingsPage homePage;
    protected LanguageOptions languageOptions;

    @BeforeTest
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));

        homePage = new StandingsPage(driver);
        languageOptions = new LanguageOptions(driver);


    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
