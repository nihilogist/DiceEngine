package org.dave3heaton.diceengine.engine;

public class PolynomialDie extends AbstractBasicDie implements Die {

    private int numberOfSides;
    private NumberGenerator numberGenerator;

    public PolynomialDie(int numberOfSides) {
        this(numberOfSides, new RandomNumberGenerator());
    }

    protected PolynomialDie(int numberOfSides, NumberGenerator numberGenerator) {
        if (numberOfSides < 1) {
            throw new IllegalArgumentException("Polynomial die with fewer than 1 sides is nonsensical.");
        }
        this.numberOfSides = numberOfSides;
        this.numberGenerator = numberGenerator;
        this.facingNumber = 1;
    }

    public void roll() {
        facingNumber = numberGenerator.getRandomNumberToMaxValue(numberOfSides);
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }


}
