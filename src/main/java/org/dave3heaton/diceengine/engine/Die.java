package org.dave3heaton.diceengine.engine;


public interface Die extends Rollable {

    /**
     * Returns the number of sides on a given dice.
     * @return
     */
    int getNumberOfSides();

}
