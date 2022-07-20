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
    public void testSelectCBLOL() {
        //homePage.load();
        setUp(); //tearDown is called in error management also
        changeLanguageToEN();
        homePage.get().clickRegion("CBLOL");
        waitSeconds(1);
        assertEquals(homePage.get().getFirstPosition(),"FURIA");
        tearDown();
    }

    @Test
    public void testSelectLCK() {
        //homePage.load();
        setUp(); //tearDown is called in error management also
        changeLanguageToEN();
        homePage.get().clickRegion("LCK");
        waitSeconds(1);
        assertEquals(homePage.get().getFirstPosition(),"Gen.G");
        tearDown();
    }



}
