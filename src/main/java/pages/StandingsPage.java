package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StandingsPage {
    WebDriver driver;

    By regionBarsElement = By.cssSelector("div.label div.name");
    By firstPositionElement = By.cssSelector("a.ranking:nth-child(2) div.name");

    public StandingsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void load() {

        //driver.get("https://lolesports.com/standings");
        driver.get("https://lolesports.com/standings/lcs/lcs_summer_2022/regular_season");

    }

    public void clickRegion(String region) {
        List<WebElement> elements = driver.findElements(regionBarsElement);
        WebElement elementToClick = null;
        //Looking for the element with the content
        //I have prefered to do this loop instead of an Xpath tha anyways might return more than one element
        //And to avoid using the easiest one -> linkText
        for(WebElement element: elements) {
            if (element.getText() == region) {
                elementToClick = element;
            }
        }

        //If located
        if (elementToClick != null) {
            elementToClick.click();
        }
    }

    public String getFirstPosition() {
        return driver.findElement(firstPositionElement).getText();
    }
}
