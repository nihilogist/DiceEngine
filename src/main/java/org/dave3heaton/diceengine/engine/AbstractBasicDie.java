package org.dave3heaton.diceengine.engine;

public abstract class AbstractBasicDie implements Die {

    protected int facingNumber;

    public int getFacingNumber() {
        return facingNumber;
    }

    public int compareTo(Die otherDie) {
        return this.facingNumber - otherDie.getFacingNumber();
    }

}
