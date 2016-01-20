package org.dave3heaton.diceengine.engine;

public class ExplodingPolynomialDie extends AbstractBasicDie implements Die {

    private int numberOfSides;
    private NumberGenerator numberGenerator;

    protected ExplodingPolynomialDie(int numberOfSides, NumberGenerator numberGenerator) {
        if (numberOfSides < 1) {
            throw new IllegalArgumentException("Die must have greater than 0 sides to give useful results.");
        }
        this.numberOfSides = numberOfSides;
        this.numberGenerator = numberGenerator;
        this.facingNumber = 1;
    }

    public ExplodingPolynomialDie(int numberOfSides) {
        this(numberOfSides, new RandomNumberGenerator());
    }

    public void roll() {
        facingNumber = 0;
        int numberRolled = numberGenerator.getRandomNumberToMaxValue(numberOfSides);
        while (numberRolled == getNumberOfSides()) {
            facingNumber += numberRolled;
            numberRolled = numberGenerator.getRandomNumberToMaxValue(numberOfSides);
        }
        facingNumber += numberRolled;

    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

}
