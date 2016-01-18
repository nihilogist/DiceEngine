package org.dave3heaton.diceengine.engine;

/**
 * Created by david.heaton on 1/18/16.
 */
public interface NumberGenerator {

    /**
     * Returns a random number between 1 and the maximum value specified in the parameters.
     * @param maximumValue
     * @return
     */
    int getRandomNumberToMaxValue(int maximumValue);
}
