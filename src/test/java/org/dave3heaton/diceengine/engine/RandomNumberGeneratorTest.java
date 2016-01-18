package org.dave3heaton.diceengine.engine;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by david.heaton on 1/18/16.
 */
public class RandomNumberGeneratorTest {

    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();


    @Test
    public void whenRequestingANumberBetweenOneAndOneExpectToGetOne() {

        Assert.assertEquals(1, randomNumberGenerator.getRandomNumberToMaxValue(1));
    }

    @Test
    public void whenRequestingANumberMustAlwaysGetANumberAboveZero() {
        for (int i = 1; i < 1000; i++) {
            Assert.assertTrue("RandomNumberGenerator must always return result above zero", randomNumberGenerator.getRandomNumberToMaxValue(10) > 0);
        }
    }

    @Test
    public void whenRequestingANumberMustAlwaysGetANumberEqualToOrBelowThatRequested() {
        for (int i = 1; i < 1000; i++) {
            Assert.assertTrue("RandomNumberGenerator must always return result equal to or below that requested", randomNumberGenerator.getRandomNumberToMaxValue(10) < 11);
        }
    }
}
