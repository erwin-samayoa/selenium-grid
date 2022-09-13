package base;

import com.google.common.io.Files;
import org.asynchttpclient.uri.Uri;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.StandingsPage;
import utils.LanguageOptions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    //private WebDriver driver;
    //It seems the base test class requires ThreadLocal properties
    //These are made somehow paralel because of method paralell testing
    //However Page Object Model doesnt seems to require to be Thread Safe
    // as long as their instances are? So test classes might need to be thread safe
    private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    protected ThreadLocal<StandingsPage> homePage = new ThreadLocal<>();
    protected ThreadLocal<LanguageOptions> languageOptions = new ThreadLocal<>();

    /*
    //Moved to constructor (parameters)
    private final String scope = "remote"; //local or remote (grid)
    private final String browser = "firefox"; //chrome or firefox (grid)

     */

    private final String scope; //local or remote (grid)
    private final String browser; //chrome or firefox (grid)

    public BaseTests() {
        scope = System.getProperty("scope");
        browser = System.getProperty("browser");
        

    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--start-maximized"); //This doesnt work on headless because is pointless having no screen
        chromeOptions.addArguments("--window-size=1366x768");
        chromeOptions.setHeadless(true);
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions fireFoxOptions = new FirefoxOptions();
        //chromeOptions.addArguments("--start-maximized"); //This doesnt work on headless because is pointless having no screen
        fireFoxOptions.addArguments("--window-size=1366x768");
        fireFoxOptions.setHeadless(true);
        return fireFoxOptions;
    }

    //@BeforeTest
    public void setUp() {

        if (scope == "local") {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver.set(new ChromeDriver(getChromeOptions()));
        } else {
            try {
                switch (browser) {
                    case "firefox":
                        driver.set(new RemoteWebDriver(new URL("http://192.168.215.10:4444/wd/hub"),getFirefoxOptions()));
                        break;
                    case "chrome":
                    default:
                        driver.set(new RemoteWebDriver(new URL("http://192.168.215.10:4444/wd/hub"),getChromeOptions()));
                        break;

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        //Timers might need to be adjusted based on local and remote (grid) machine
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));

        homePage.set(new StandingsPage(driver.get()));
        languageOptions.set(new LanguageOptions(driver.get()));
        waitSeconds(1);

    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            var camera = (TakesScreenshot) driver.get();
            File screenshot = camera.getScreenshotAs(OutputType.FILE);

            try {
                //de google es esta clase
                Files.move(screenshot,new File("resources/screenshots/" + result.getName() + ".png"));
                //Files.move(screenshot,new File("C:\\Users\\User\\AppData\\Local\\Temp\\" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }



            System.out.println("Screenshot taken " + screenshot.getAbsolutePath());
        }
        //tearDown(); //To close browser in grid but it doesnt close OK
    }

    @AfterClass
    public void tearDown() {
        driver.get().quit();
    }

    public void waitSeconds(int seconds) {
        System.out.println("Waiting " + seconds + "seconds");
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception while waiting");
        }
        System.out.println("End of waiting " + seconds + "seconds");
    }
}
