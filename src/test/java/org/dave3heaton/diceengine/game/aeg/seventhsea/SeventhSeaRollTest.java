package org.dave3heaton.diceengine.game.aeg.seventhsea;


import org.dave3heaton.diceengine.engine.NumberGenerator;
import org.dave3heaton.diceengine.engine.PolynomialDie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SeventhSeaRollTest {

    @Test
    public void whenCreatingASeventhSeaRollExpectToSpecifyDiceToRollAndDiceToKeep() {
        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1);
        assertTrue(rollOneKeepOneRoll != null);
    }

    @Test
    public void whenCreatingASeventhSeaRollExpectToSpecifyIfNotExploding() {
        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1, false);
        assertTrue(rollOneKeepOneRoll != null);
    }

    @Test
    public void theDiceToRollShouldBeAPubliclyAccessibleField() {
        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1);
        assertEquals(1, rollOneKeepOneRoll.getDiceToRoll());

        SeventhSeaRoll rollTwoKeepOneRoll = new SeventhSeaRoll(2, 1);
        assertEquals(2, rollTwoKeepOneRoll.getDiceToRoll());
    }

    @Test
    public void theDiceToKeepShouldBeAPubliclyAccessibleField() {
        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1);
        assertEquals(1, rollOneKeepOneRoll.getDiceToKeep());

        SeventhSeaRoll rollTwoKeepTwoRoll = new SeventhSeaRoll(2, 2);
        assertEquals(2, rollTwoKeepTwoRoll.getDiceToKeep());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingASeventhSeaRollTheRollValueMustBePositive() {
        SeventhSeaRoll badRoll = new SeventhSeaRoll(-1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingASeventhSeaRollTheKeepValueMustBePositive() {
        SeventhSeaRoll badRoll = new SeventhSeaRoll(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingASeventhSeaRollTheNumberOfDiceToKeepMustBeLessThanOrEqualToTheNumberOfDiceToRoll() {
        SeventhSeaRoll badRoll = new SeventhSeaRoll(1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingSeventhSeaRollCannotRollMoreThanTenDice() {
        SeventhSeaRoll badRoll = new SeventhSeaRoll(11, 1);
    }

    @Test
    public void onConstructionTheRollShouldHaveAResultOfZero() {
        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1);
        assertEquals(0, rollOneKeepOneRoll.getFacingNumber());
    }

    @Test
    public void comparisonShouldWorkBetweenDieTypes() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(6).thenReturn(6);

        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1, true, mockNumberGenerator);
        rollOneKeepOneRoll.roll();

        SeventhSeaDie otherDie = new SeventhSeaDie(false, mockNumberGenerator);
        otherDie.roll();

        assertEquals(10, rollOneKeepOneRoll.compareTo(otherDie));
    }

    @Test
    public void whenRollingAOneKeepOneRollAndASixteenIsScoredExpectTheRollToReturnSixteen() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(6);

        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1, true, mockNumberGenerator);
        rollOneKeepOneRoll.roll();

        assertEquals(16, rollOneKeepOneRoll.getFacingNumber());
    }

    @Test
    public void whenRollingAOneKeepOneNonExplodingRollAndASixteenIsScoredExpectTheRollToReturnTen() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(6);

        SeventhSeaRoll rollOneKeepOneRoll = new SeventhSeaRoll(1, 1, false, mockNumberGenerator);
        rollOneKeepOneRoll.roll();

        assertEquals(10, rollOneKeepOneRoll.getFacingNumber());
    }

    @Test
    public void forATwoKeepOneRollOnlyTheHighestDieShouldBeCounted() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(4).thenReturn(10).thenReturn(7);

        SeventhSeaRoll rollTwoKeepOneRoll = new SeventhSeaRoll(2, 1, true, mockNumberGenerator);
        rollTwoKeepOneRoll.roll();

        assertEquals(17, rollTwoKeepOneRoll.getFacingNumber());
    }

    @Test
    public void forASeventhSeaRollIWantToAccessTheIndividualDice() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(4).thenReturn(10).thenReturn(7);

        SeventhSeaRoll rollTwoKeepOneRoll = new SeventhSeaRoll(2, 1, true, mockNumberGenerator);
        rollTwoKeepOneRoll.roll();

        assertEquals(2, rollTwoKeepOneRoll.getAllDice().size());
        assertEquals(17, rollTwoKeepOneRoll.getAllDice().get(0).getFacingNumber());
        assertEquals(4, rollTwoKeepOneRoll.getAllDice().get(1).getFacingNumber());
    }

    @Test
    public void forASeventhSeaRollIWantToDetermineIfADieWasKeptOrNot() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(4).thenReturn(10).thenReturn(7);

        SeventhSeaRoll rollTwoKeepOneRoll = new SeventhSeaRoll(2, 1, true, mockNumberGenerator);
        rollTwoKeepOneRoll.roll();

        assertEquals(17, rollTwoKeepOneRoll.getFacingNumber());
        assertTrue(rollTwoKeepOneRoll.getAllDice().get(0).isKept());
        assertTrue(!rollTwoKeepOneRoll.getAllDice().get(1).isKept());

    }

    @Test
    public void whenRollingManyDiceExpectCorrectSorting() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(4).thenReturn(6).thenReturn(10).thenReturn(2);

        SeventhSeaRoll rollThreeKeepOneRoll = new SeventhSeaRoll(3, 1, true, mockNumberGenerator);
        rollThreeKeepOneRoll.roll();

        assertEquals(12, rollThreeKeepOneRoll.getFacingNumber());
        assertTrue(rollThreeKeepOneRoll.getAllDice().get(0).isKept());
        assertTrue(!rollThreeKeepOneRoll.getAllDice().get(1).isKept());
    }

    @Test
    public void testFromAndroidMock() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(6).thenReturn(2).thenReturn(5).thenReturn(7).thenReturn(7).thenReturn(9).thenReturn(5);

        SeventhSeaRoll rollSevenKeepFiveRoll = new SeventhSeaRoll(7,5, true, mockNumberGenerator);
        rollSevenKeepFiveRoll.roll();

        assertEquals(34, rollSevenKeepFiveRoll.getFacingNumber());
        assertTrue(rollSevenKeepFiveRoll.getAllDice().get(0).isKept());
        assertTrue(rollSevenKeepFiveRoll.getAllDice().get(1).isKept());
    }

    @Test
    public void aSeventhSeaRollShouldBeToggleableBetweenExplodingResults() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(2).thenReturn(10).thenReturn(7).thenReturn(7).thenReturn(9).thenReturn(5);

        SeventhSeaRoll rollFourKeepTwoRoll = new SeventhSeaRoll(4,2, true, mockNumberGenerator);
        rollFourKeepTwoRoll.roll();

        assertEquals(29, rollFourKeepTwoRoll.getFacingNumber());
        assertEquals(17, rollFourKeepTwoRoll.getAllDice().get(0).getFacingNumber());

        rollFourKeepTwoRoll.setExploding(false);

        assertEquals(20, rollFourKeepTwoRoll.getFacingNumber());
        assertEquals(10, rollFourKeepTwoRoll.getAllDice().get(0).getFacingNumber());

        rollFourKeepTwoRoll.setExploding(true);
        assertEquals(29, rollFourKeepTwoRoll.getFacingNumber());
        assertEquals(17, rollFourKeepTwoRoll.getAllDice().get(0).getFacingNumber());

    }


}
