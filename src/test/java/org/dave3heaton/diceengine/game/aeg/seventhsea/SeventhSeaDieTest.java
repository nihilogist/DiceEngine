package org.dave3heaton.diceengine.game.aeg.seventhsea;

import org.dave3heaton.diceengine.engine.NumberGenerator;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SeventhSeaDieTest {


    @Test
    public void whenCreatingSeventhSeaDieMustSpecifyIfItWillExplode() {
        SeventhSeaDie seventhSeaDie = new SeventhSeaDie(true);
    }

    @Test
    public void seventhSeaDiceShouldActLikeTenSidedExplodingPolynomialDiceWhenConstructed() {
        SeventhSeaDie seventhSeaDie = new SeventhSeaDie(true);
        assertEquals(10, seventhSeaDie.getNumberOfSides());
        assertEquals(1, seventhSeaDie.getFacingNumber());
    }

    @Test
    public void anExplodingSeventhSeaDieShouldActLikeATenSidedExplodingPolynomialDie() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(4).thenReturn(6).thenReturn(8);

        SeventhSeaDie seventhSeaDie = new SeventhSeaDie(true, mockNumberGenerator);
        seventhSeaDie.roll();

        assertEquals(14, seventhSeaDie.getFacingNumber());
    }

    @Test
    public void aNonExplodingSeventhSeaDieShouldActLikeATenSidedPolynomialDie() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(4).thenReturn(6).thenReturn(8);

        SeventhSeaDie seventhSeaDie = new SeventhSeaDie(false, mockNumberGenerator);
        seventhSeaDie.roll();

        assertEquals(10, seventhSeaDie.getFacingNumber());
    }

    @Test
    public void aSeventhSeaDieShouldBeAbleToBeMarkedAsAKeptDie() {
        SeventhSeaDie seventhSeaDie = new SeventhSeaDie(true);
        assertTrue(!seventhSeaDie.isKept());

        seventhSeaDie.setKept(true);
        assertTrue(seventhSeaDie.isKept());
    }

    @Test
    public void aSeventhSeaDieShouldBeAbleToToggleBetweenExplodingAndNot() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(4).thenReturn(6).thenReturn(8);

        SeventhSeaDie seventhSeaDie = new SeventhSeaDie(false, mockNumberGenerator);
        seventhSeaDie.roll();

        assertEquals(10, seventhSeaDie.getFacingNumber());

        seventhSeaDie.setExploding(true);
        assertEquals(14, seventhSeaDie.getFacingNumber());
    }






}
