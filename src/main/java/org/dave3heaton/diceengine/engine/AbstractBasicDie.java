package org.dave3heaton.diceengine.engine;

public abstract class AbstractBasicDie implements Die {

    protected int facingNumber;
    protected int numberOfSides;
    protected NumberGenerator numberGenerator;

    protected AbstractBasicDie(int numberOfSides, NumberGenerator numberGenerator) {
        if (numberOfSides < 1) {
            throw new IllegalArgumentException("Dice must have more than 0 sides to produce meaningful results");
        }

        this.numberGenerator = numberGenerator;
        this.facingNumber = 1;
        this.numberOfSides = numberOfSides;
    }

    public int getFacingNumber() {
        return facingNumber;
    }

    public int compareTo(Die otherDie) {
        return this.facingNumber - otherDie.getFacingNumber();
    }

}
