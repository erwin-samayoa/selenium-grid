package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LanguageOptions {
    private final WebDriver driver;
    private final By languageOptionsElement = By.cssSelector("a.lang-switch-trigger");
    private final By currentLanguageElement = By.cssSelector("html");


    public LanguageOptions(WebDriver driver) {
        this.driver = driver;
    }

    public void selectLanguage(String lang) {
        //Example: en-US
        driver.findElement(languageOptionsElement).click();
        driver.findElement(By.cssSelector("a[href='/" + lang + "']")).click();
    }

    public String getCurrentLanguage() {
        return driver.findElement(currentLanguageElement).getAttribute("lang");
    }
}
