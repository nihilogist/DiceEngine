package org.dave3heaton.diceengine.engine;


public interface Die extends Comparable {

    void roll();

    int getFacingNumber();

    int getNumberOfSides();

}
