package standings;

import base.BaseTests;
import org.testng.annotations.Test;
import utils.LanguageOptions;

import static org.testng.Assert.assertEquals;

public class StandingsTests extends BaseTests {

    @Test
    public void testChangeLanguageToEN() {
        homePage.load();
        languageOptions.selectLanguage("en-US");
        assertEquals(languageOptions.getCurrentLanguage(),"en-US");
    }

    @Test
    public void testSelectLCS() {
        //homePage.load();
        homePage.clickRegion("LCS");
        assertEquals(homePage.getFirstPosition(),"Evil Geniuses");
    }

    @Test
    public void testSelectCBLOL() {
        //homePage.load();
        homePage.clickRegion("CBLOL");
        assertEquals(homePage.getFirstPosition(),"FURIA");
    }



}
