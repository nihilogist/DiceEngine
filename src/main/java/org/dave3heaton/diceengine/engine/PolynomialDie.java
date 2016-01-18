package org.dave3heaton.diceengine.engine;

/**
 * Created by david.heaton on 1/18/16.
 */
public class PolynomialDie implements Die {

    private int numberOfSides;
    private int facingNumber;
    private NumberGenerator numberGenerator;

    public PolynomialDie(int numberOfSides) {
        this(numberOfSides, new RandomNumberGenerator());
    }

    protected PolynomialDie(int numberOfSides, NumberGenerator numberGenerator) {
        this.numberOfSides = numberOfSides;
        this.numberGenerator = numberGenerator;
        this.facingNumber = 1;
    }

    public void roll() {
        facingNumber = numberGenerator.getRandomNumberToMaxValue(numberOfSides);
    }

    public int getFacingNumber() {
        return facingNumber;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public int compareTo(Object o) {
        return 0;
    }
}
