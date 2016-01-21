package org.dave3heaton.diceengine.engine;

/**
 * Interface to define the basic characteristics of something _rollable_
 */
public interface Rollable extends Comparable<Rollable> {

    /**
     * Rolls the instance, refreshing whatever number is facing on it.
     */
    void roll();

    /**
     * Returns the number that is currently held by the rollable object.
     * @return
     */
    int getFacingNumber();
}
