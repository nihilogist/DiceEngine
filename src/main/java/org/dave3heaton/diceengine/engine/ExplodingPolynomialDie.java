package org.dave3heaton.diceengine.engine;

public class ExplodingPolynomialDie extends AbstractBasicDie implements Die {

    protected ExplodingPolynomialDie(int numberOfSides, NumberGenerator numberGenerator) {
        super(numberOfSides, numberGenerator);
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
