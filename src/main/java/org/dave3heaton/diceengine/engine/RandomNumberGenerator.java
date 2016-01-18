package org.dave3heaton.diceengine.engine;


import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    private Random generator = new Random();

    public int getRandomNumberToMaxValue(int maximumValue) {
        return generator.nextInt(maximumValue) + 1;
    }

}
