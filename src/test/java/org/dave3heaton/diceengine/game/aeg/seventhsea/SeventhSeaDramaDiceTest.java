package org.dave3heaton.diceengine.game.aeg.seventhsea;


import org.dave3heaton.diceengine.engine.NumberGenerator;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SeventhSeaDramaDiceTest {

    @Test
    public void whenCreatingASeventhSeaRollExpectNoDramaDicePresent() {
        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1);
        assertEquals(0, rollOneKeepOneRoll.getDramaDice().size());
    }

    @Test
    public void withAGivenSeventhSeaRollExpectToBeAbleToAddDramaDice() {
        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1);
        rollOneKeepOneRoll.addDramaDice();
        assertEquals(1, rollOneKeepOneRoll.getDramaDice().size());
        rollOneKeepOneRoll.addDramaDice();
        assertEquals(2, rollOneKeepOneRoll.getDramaDice().size());
    }

    @Test
    public void whenAddingADramaDieToARollExpectThemToBeRolledAndReflectedInTheTotal() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(2).thenReturn(7).thenReturn(10).thenReturn(7).thenReturn(9).thenReturn(5);

        SeventhSeaRoll rollTwoKeepOneRoll = new SeventhSeaRoll(2, 1, true, mockNumberGenerator);
        rollTwoKeepOneRoll.roll();

        assertEquals(12, rollTwoKeepOneRoll.getFacingNumber());

        rollTwoKeepOneRoll.addDramaDice();

        assertEquals(29, rollTwoKeepOneRoll.getFacingNumber());
    }

    @Test
    public void whenRollingASeventhSeaRollExpectAllDramaDiceToBeRemoved() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(2).thenReturn(7).thenReturn(10).thenReturn(7).thenReturn(9).thenReturn(5);

        SeventhSeaRoll rollTwoKeepOneRoll = new SeventhSeaRoll(2, 1, true, mockNumberGenerator);
        rollTwoKeepOneRoll.roll();
        rollTwoKeepOneRoll.addDramaDice();
        rollTwoKeepOneRoll.roll();

        assertEquals(9, rollTwoKeepOneRoll.getFacingNumber());
    }

    @Test
    public void dramaDiceMustExplodeEvenIfTheRollIsNot() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(2).thenReturn(7).thenReturn(10).thenReturn(7).thenReturn(9).thenReturn(5);

        SeventhSeaRoll rollTwoKeepOneRoll = new SeventhSeaRoll(2, 1, false, mockNumberGenerator);
        rollTwoKeepOneRoll.roll();
        assertEquals(10, rollTwoKeepOneRoll.getFacingNumber());
        rollTwoKeepOneRoll.addDramaDice();
        assertEquals(27, rollTwoKeepOneRoll.getFacingNumber());

    }

}
