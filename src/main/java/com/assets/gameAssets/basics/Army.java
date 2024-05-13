package com.assets.gameAssets.basics;

public class Army {

    private double infantry;
    private double artillery;
    private double tanks;
    private double apaches;
    private int attackValue;
    private int modifierValue;

    private Dice dice;

    private boolean isAtlantis;

    public Army(double army) {
        this.infantry = army * 0.5;
        this.artillery = army * 0.35;
        this.tanks = army * 0.15;
        this.apaches = army * 0.1;
        this.modifierValue = 0;
    }

    public Army(double army, int modifierValue) {
        this.infantry = 0;
        this.artillery = 0;
        this.tanks = 0;
        this.apaches = army;
        this.modifierValue = 5;
    }

    public double getInfantry() {
        return this.infantry;
    }

    public double getArtillery() {
        return this.artillery;
    }

    public double getTanks() {
        return this.tanks;
    }

    public double getApaches() {
        return this.apaches;
    }

    public void setModifierValue(int modifierValue) {
        this.modifierValue = modifierValue;
    }

    public void infantryAttack() {
        dice = Dice.getD6();
        attackValue = dice.throwDice() + this.modifierValue;
    }

    public void artilleryAttack() {
        dice = Dice.getD8();
        attackValue = dice.throwDice() + this.modifierValue;
    }

    public void tankAttack() {
        dice = Dice.getD10();
        attackValue = dice.throwDice() + this.modifierValue;
    }

    public void apacheAttack() {
        dice = Dice.getD12();
        attackValue = dice.throwDice() + this.modifierValue;
    }

    public void atlantisAttack() {
        dice = Dice.getD20();
        attackValue = dice.getFaceNumber() + this.modifierValue;
    }
}
