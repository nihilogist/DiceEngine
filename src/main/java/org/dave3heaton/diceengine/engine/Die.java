package org.dave3heaton.diceengine.engine;


public interface Die extends Comparable<Die> {

    void roll();

    int getFacingNumber();

    int getNumberOfSides();

}
