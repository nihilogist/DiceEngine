package org.dave3heaton.diceengine.engine;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


import static org.junit.Assert.assertEquals;


public class PolynomialDieTest {

    @Test
    public void whenCreatingADieTheFacingNumberShouldBeOne() {
        PolynomialDie polynomialDie = new PolynomialDie(1);
        assertEquals(1, polynomialDie.getFacingNumber());
    }

    @Test
    public void whenCreatingADieExpectToBeAbleToChooseTheNumberOfSides() {
        PolynomialDie sixSidedDie = new PolynomialDie(6);
        assertEquals(6, sixSidedDie.getNumberOfSides());

        PolynomialDie tenSidedDie = new PolynomialDie(10);
        assertEquals(10, tenSidedDie.getNumberOfSides());
    }

    @Test
    public void whenRollingADieExpectACallToBeMadeToANumberGenerator() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);

        PolynomialDie tenSidedDie = new PolynomialDie(10, mockNumberGenerator);
        tenSidedDie.roll();

        verify(mockNumberGenerator).getRandomNumberToMaxValue(10);
    }

    @Test
    public void whenRollingADieExpectTheFacingNumberToBeUpdatedToTheNumberGenerated() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(6);

        PolynomialDie tenSidedDie = new PolynomialDie(10, mockNumberGenerator);
        tenSidedDie.roll();

        verify(mockNumberGenerator).getRandomNumberToMaxValue(10);
        assertEquals(6, tenSidedDie.getFacingNumber());
    }

    @Test
    public void whenComparingTwoDiceExpectTheFacingValueToDetermineTheOrder() {
        NumberGenerator mockNumberGenerator = mock(NumberGenerator.class);
        when(mockNumberGenerator.getRandomNumberToMaxValue(10)).thenReturn(6).thenReturn(4).thenReturn(6).thenReturn(8);

        PolynomialDie tenSidedDieOne = new PolynomialDie(10, mockNumberGenerator);
        PolynomialDie tenSidedDieTwo = new PolynomialDie(10, mockNumberGenerator);

        tenSidedDieOne.roll();
        tenSidedDieTwo.roll();
        assertTrue("Expect dice one to be greater than dice two", tenSidedDieOne.compareTo(tenSidedDieTwo) == 2);

        tenSidedDieTwo.roll();
        assertTrue("Expect dice one to be greater than dice two", tenSidedDieOne.compareTo(tenSidedDieTwo) == 0);

        tenSidedDieTwo.roll();
        assertTrue("Expect dice one to be greater than dice two", tenSidedDieOne.compareTo(tenSidedDieTwo) == -2);

    }

}
