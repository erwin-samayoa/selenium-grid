package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StandingsPage {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    By regionBarsElement = By.cssSelector("div.label div.name");
    By firstPositionElement = By.cssSelector("a.ranking:nth-child(2) div.name");

    public StandingsPage(WebDriver driver) {
        this.driver.set(driver);
    }

    public void load() {

        System.out.println("Loading page " + this.getClass().getName());
        //driver.get("https://lolesports.com/standings");

        driver.get().get("https://lolesports.com/standings/lcs/lcs_summer_2022/regular_season");

    }

    public void clickRegion(String region) {
        System.out.println("Clicking region " + region);
        List<WebElement> elements = driver.get().findElements(regionBarsElement);
        WebElement elementToClick = null;
        //Looking for the element with the content
        //I have prefered to do this loop instead of an Xpath tha anyways might return more than one element
        //And to avoid using the easiest one -> linkText
        for(WebElement element: elements) {
            if (element.getText().equals(region)) {
                elementToClick = element;
            }
        }

        //If located
        if (elementToClick != null) {
            try {
                elementToClick.click();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getFirstPosition() {

        System.out.println("Getting first position");
        return driver.get().findElement(firstPositionElement).getText();
    }
}
