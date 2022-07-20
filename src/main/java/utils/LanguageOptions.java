package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LanguageOptions {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final By languageOptionsElement = By.cssSelector("a.lang-switch-trigger");
    private final By currentLanguageElement = By.cssSelector("html");


    public LanguageOptions(WebDriver driver) {
        this.driver.set(driver);
    }

    public void selectLanguage(String lang) {
        //Example: en-US
        System.out.println("Clicking globe icon");
        driver.get().findElement(languageOptionsElement).click();

        System.out.println("Internal wait");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception while waiting");
        }

        System.out.println("Selecting language " + lang);
        driver.get().findElement(By.cssSelector("a[href='/" + lang + "']")).click();
    }

    public String getCurrentLanguage() {
        return driver.get().findElement(currentLanguageElement).getAttribute("lang");
    }
}
