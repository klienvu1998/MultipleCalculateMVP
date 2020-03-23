package com.example.multiplecalculatemvp.model;


public class Number {
    private long numberMultiplied;
    private long multiplier;

    public Number(long numberMultiplied, long multiplier) {
        this.numberMultiplied = numberMultiplied;
        this.multiplier = multiplier;
    }

    public long getNumberMultiplied() {
        return numberMultiplied;
    }

    public void setNumberMultiplied(long numberMultiplied) {
        this.numberMultiplied = numberMultiplied;
    }

    public long getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(long multiplier) {
        this.multiplier = multiplier;
    }

}
