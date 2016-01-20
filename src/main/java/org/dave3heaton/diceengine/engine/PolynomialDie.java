package org.dave3heaton.diceengine.engine;

public class PolynomialDie extends AbstractBasicDie implements Die {

    public PolynomialDie(int numberOfSides) {
        this(numberOfSides, new RandomNumberGenerator());
    }

    protected PolynomialDie(int numberOfSides, NumberGenerator numberGenerator) {
        super(numberOfSides, numberGenerator);
    }

    public void roll() {
        facingNumber = numberGenerator.getRandomNumberToMaxValue(numberOfSides);
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }


}
