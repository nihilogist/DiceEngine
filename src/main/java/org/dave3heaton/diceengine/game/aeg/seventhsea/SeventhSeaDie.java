package org.dave3heaton.diceengine.game.aeg.seventhsea;

import org.dave3heaton.diceengine.engine.ExplodingPolynomialDie;
import org.dave3heaton.diceengine.engine.NumberGenerator;
import org.dave3heaton.diceengine.engine.RandomNumberGenerator;

public class SeventhSeaDie extends ExplodingPolynomialDie {

    private boolean isExploding;
    private boolean isKept;

    public SeventhSeaDie(boolean exploding) {
        this(exploding, new RandomNumberGenerator());
    }

    protected SeventhSeaDie(boolean exploding, NumberGenerator numberGenerator) {
        super(10, numberGenerator);
        this.isExploding = exploding;
        this.isKept = false;
    }

    @Override
    public int getFacingNumber() {
        if (isExploding) {
            return this.facingNumber;
        } else {
            return getFacingNumberUpToMaximumOfTen();
        }
    }

    public boolean isKept() {
        return isKept;
    }

    public void setKept(boolean isKept) {
        this.isKept = isKept;
    }

    public void setExploding(boolean isExploding) {
        this.isExploding = isExploding;
    }

    private int getFacingNumberUpToMaximumOfTen() {
        if (facingNumber < 10) {
            return facingNumber;
        } else {
            return 10;
        }
    }

}
