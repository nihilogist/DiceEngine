package org.dave3heaton.diceengine.game.aeg.seventhsea;

import org.dave3heaton.diceengine.engine.NumberGenerator;
import org.dave3heaton.diceengine.engine.RandomNumberGenerator;
import org.dave3heaton.diceengine.engine.Rollable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeventhSeaRoll implements Rollable {

    private NumberGenerator numberGenerator;

    private int diceToRoll;
    private int diceToKeep;
    private int facingNumber;
    private boolean isExploding;
    private List<SeventhSeaDie> seventhSeaDice;

    protected SeventhSeaRoll(int diceToRoll, int diceToKeep, boolean isExploding, NumberGenerator numberGenerator) {
        if (diceToRoll < 1 || diceToKeep < 1) {
            throw new IllegalArgumentException("Roll must have a positive number of dice to roll and keep");
        }

        if (diceToKeep > diceToRoll) {
            throw new IllegalArgumentException("Roll must not keep more dice than rolling");
        }

        if (diceToRoll > 10) {
            throw new IllegalArgumentException("Cannot roll more than ten dice");
        }

        this.diceToRoll = diceToRoll;
        this.diceToKeep = diceToKeep;
        this.numberGenerator = numberGenerator;
        this.isExploding = isExploding;
        this.seventhSeaDice = new ArrayList<SeventhSeaDie>();
    }

    public SeventhSeaRoll(int diceToRoll, int diceToKeep) {
        this(diceToRoll, diceToKeep, true, new RandomNumberGenerator());
    }

    public SeventhSeaRoll(int diceToRoll, int diceToKeep, boolean isExploding) {
        this(diceToRoll, diceToKeep, isExploding, new RandomNumberGenerator());
    }

    public void roll() {
        // Clear the list
        seventhSeaDice.clear();
        for (int i = 0; i < diceToRoll; i++) {
            seventhSeaDice.add(new SeventhSeaDie(isExploding, numberGenerator));
        }

        for(SeventhSeaDie seventhSeaDie : seventhSeaDice) {
            seventhSeaDie.roll();
        }

        Collections.sort(seventhSeaDice);
        Collections.reverse(seventhSeaDice);
        int scoreTotal = 0;
        for (int i = 0; i < diceToKeep; i++) {
            scoreTotal += seventhSeaDice.get(i).getFacingNumber();
            seventhSeaDice.get(i).setKept(true);
        }
        facingNumber = scoreTotal;
    }

    public List<SeventhSeaDie> getAllDice() {
        return seventhSeaDice;
    }

    public int getFacingNumber() {
        return facingNumber;
    }

    public int compareTo(Rollable o) {
        return this.getFacingNumber() - o.getFacingNumber();
    }

    public int getDiceToRoll() {
        return diceToRoll;
    }

    public int getDiceToKeep() {
        return diceToKeep;
    }
}
