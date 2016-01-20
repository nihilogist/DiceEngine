package org.dave3heaton.diceengine.engine;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by david.heaton on 1/20/16.
 */
public class ExplodingPolynomialDieTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingADieNumberOfSidesShouldBeGreaterThanZero() {
        ExplodingPolynomialDie nonsensicalDie = new ExplodingPolynomialDie(0);
    }

    @Test
    public void whenCreatingADieTheFacingNumberShouldBeOne() {
        ExplodingPolynomialDie nonsensicalDie = new ExplodingPolynomialDie(1);
        assertEquals(1, nonsensicalDie.getFacingNumber());
    }

    @Test
    public void whenCreatingADieExpectToSpecifyTheNumberOfSides() {
        ExplodingPolynomialDie oneSidedDie = new ExplodingPolynomialDie(1);
        assertEquals(1, oneSidedDie.getNumberOfSides());

        ExplodingPolynomialDie twoSidedDie = new ExplodingPolynomialDie(2);
        assertEquals(2, twoSidedDie.getNumberOfSides());
    }

    @Test
    public void whenRollingTheDiceExpectACallToBeMadeToANumberGenerator() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(6);

        ExplodingPolynomialDie tenSidedExplodingDie = new ExplodingPolynomialDie(10, mockNumberGenerator);
        tenSidedExplodingDie.roll();

        Mockito.verify(mockNumberGenerator).getRandomNumberToMaxValue(10);
    }

    @Test
    public void whenRollingTheDiceAndTheNumberDoesNotExplodeExpectThatNumberToBeSetAsFacingNumber() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(6);

        ExplodingPolynomialDie tenSidedExplodingDie = new ExplodingPolynomialDie(10, mockNumberGenerator);
        tenSidedExplodingDie.roll();

        assertEquals(6, tenSidedExplodingDie.getFacingNumber());
    }

    @Test
    public void whenRollingTheDiceAndTheNumberDoesExplodeExpectAnotherNumberToBeGeneratedAndTheTotalsSummed() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(10).thenReturn(6);

        ExplodingPolynomialDie tenSidedExplodingDie = new ExplodingPolynomialDie(10, mockNumberGenerator);
        tenSidedExplodingDie.roll();

        assertEquals(26, tenSidedExplodingDie.getFacingNumber());
    }

    @Test
    public void whenComparingTwoDiceExpectTheFacingNumberToBeTheMechanismOfComparison() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(10).thenReturn(4).thenReturn(6).thenReturn(8);

        ExplodingPolynomialDie tenSidedExplodingDieOne = new ExplodingPolynomialDie(10, mockNumberGenerator);
        tenSidedExplodingDieOne.roll();

        ExplodingPolynomialDie tenSidedExplodingDieTwo = new ExplodingPolynomialDie(10, mockNumberGenerator);
        tenSidedExplodingDieTwo.roll();

        PolynomialDie tenSidedDie = new PolynomialDie(10, mockNumberGenerator);
        tenSidedDie.roll();

        assertEquals(14, tenSidedExplodingDieOne.getFacingNumber());
        assertEquals(6, tenSidedExplodingDieTwo.getFacingNumber());
        assertEquals(8, tenSidedDie.getFacingNumber());
        assertEquals(8, tenSidedExplodingDieOne.compareTo(tenSidedExplodingDieTwo));
        assertEquals(-2, tenSidedExplodingDieTwo.compareTo(tenSidedDie));

    }
}
