package standings;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StandingsTests extends BaseTests {

    @Test
    public void testSelectLCS() {
        homePage.load();
        homePage.clickRegion("LCS");
        assertEquals(homePage.getFirstPosition(),"Evil Geniuses");
    }



}
