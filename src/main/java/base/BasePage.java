package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(30);

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitToClickElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitPresenceOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,WAIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void waitPresenceOfElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,WAIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void clickElement(By locator) {
        waitToClickElement(locator);
        driver.findElement(locator).click();
    }

    protected void clickElement(By locator, String text) {

        waitPresenceOfElements(locator);
        List<WebElement> elements = driver.findElements(locator);
        WebElement elementToClick = null;

        //I have prefered to do this loop instead of an Xpath tha anyways might return more than one element
        //And to avoid using the easiest one -> linkText

        for(WebElement element: elements) {
            if (element.getText().equals(text)) {
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

    protected String getTextFromElement(By locator) {
        waitPresenceOfElement(locator);
        return driver.findElement(locator).getText();
    }



}
