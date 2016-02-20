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
    private boolean isExploding;
    private List<SeventhSeaDie> seventhSeaDice;
    private List<SeventhSeaDie> dramaDice;

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
        this.dramaDice = new ArrayList<SeventhSeaDie>();
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
        // Also clear the drama dice list
        dramaDice.clear();
        for (int i = 0; i < diceToRoll; i++) {
            seventhSeaDice.add(new SeventhSeaDie(isExploding, numberGenerator));
        }

        for(SeventhSeaDie seventhSeaDie : seventhSeaDice) {
            seventhSeaDie.roll();
        }

        Collections.sort(seventhSeaDice);
        Collections.reverse(seventhSeaDice);
        for (int i = 0; i < diceToKeep; i++) {
            seventhSeaDice.get(i).setKept(true);
        }
    }

    public void addDramaDice() {
        SeventhSeaDie dramaDie = new SeventhSeaDie(true, numberGenerator);
        dramaDie.roll();
        dramaDice.add(dramaDie);
    }

    public List<SeventhSeaDie> getAllDice() {
        return seventhSeaDice;
    }

    public List<SeventhSeaDie> getDramaDice() {
        return dramaDice;
    }

    public int getFacingNumber() {
        int scoreTotal = 0;

        for (SeventhSeaDie die : seventhSeaDice) {
            if (die.isKept()) {
                if (isExploding) {
                    scoreTotal += die.getFacingNumber();
                } else {
                    scoreTotal += getDieResultUpToTen(die);
                }

            }
        }

        for (SeventhSeaDie dramaDie : dramaDice) {
            scoreTotal += dramaDie.getFacingNumber();
        }

        return scoreTotal;
    }

    private int getDieResultUpToTen(SeventhSeaDie die) {
        if (die.getFacingNumber() > 10) {
            return 10;
        } else {
            return die.getFacingNumber();
        }
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

    public void setExploding(boolean isExploding) {
        this.isExploding = isExploding;
        for (SeventhSeaDie die : getAllDice()) {
            die.setExploding(isExploding);
        }
    }
}
