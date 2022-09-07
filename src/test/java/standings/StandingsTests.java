package standings;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StandingsTests extends BaseTests {


    public void changeLanguageToEN() {
        //waitSeconds(10);
        homePage.get().load();
        waitSeconds(1);
        languageOptions.get().selectLanguage("en-US");
        waitSeconds(2);
        assertEquals(languageOptions.get().getCurrentLanguage(),"en-US");
    }

    @Test
    public void testSelectLCS() {
        //homePage.load();
        setUp(); //tearDown is called in error management
        changeLanguageToEN();
        homePage.get().clickRegion("LCS");
        waitSeconds(1);
        assertEquals(homePage.get().getFirstPosition(),"Evil Geniuses");
        tearDown();
    }

    @Test
    public void testSelectLcsAcademy() {
        //homePage.load();
        setUp(); //tearDown is called in error management also
        changeLanguageToEN();
        homePage.get().clickRegion("LCS Academy");
        waitSeconds(1);
        assertEquals(homePage.get().getFirstPosition(),"TL Academy");
        tearDown();
    }

    @Test
    public void testSelectLCL() {
        //homePage.load();
        setUp(); //tearDown is called in error management also
        changeLanguageToEN();
        homePage.get().clickRegion("LCL");
        waitSeconds(1);
        assertEquals(homePage.get().getFirstPosition(),"Team Spirit");
        tearDown();
    }



}
