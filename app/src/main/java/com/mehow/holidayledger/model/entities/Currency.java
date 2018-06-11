package com.mehow.holidayledger.model.entities;

import com.mehow.holidayledger.model.entities.enums.CurrencyShortcut;

public class Currency {
    CurrencyShortcut currencyShortcut;
    float rateToPLN;
    String name;

    public Currency(CurrencyShortcut currencyShortcut, float rateToPLN, String name) {
        this.currencyShortcut = currencyShortcut;
        this.rateToPLN = rateToPLN;
        this.name = name;
    }

    public CurrencyShortcut getCurrencyShortcut() {
        return currencyShortcut;
    }

    public void setCurrencyShortcut(CurrencyShortcut currencyShortcut) {
        this.currencyShortcut = currencyShortcut;
    }

    public float getRateToPLN() {
        return rateToPLN;
    }

    public void setRateToPLN(float rateToPLN) {
        this.rateToPLN = rateToPLN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
