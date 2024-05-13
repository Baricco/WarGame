package com.assets.gameAssets.basics;

public class Army {

    private double infantry;
    private double artillery;
    private double tanks;
    private double apaches;
    private int modifierValue;

    public enum ARMY_TYPE {
        INFANTRY,
        ARTILLERY,
        TANK,
        APACHE,
        CHTULHU
    }

    private Dice dice;

    public Army(double army) {
        this.infantry = army * 0.5;
        this.artillery = army * 0.25;
        this.tanks = army * 0.15;
        this.apaches = army * 0.1;
        this.modifierValue = 0;
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

    public int attack(ARMY_TYPE type) {
        
        dice = getDiceByArmyType(type);

        return dice.throwDice() + this.modifierValue;
    }   

    private Dice getDiceByArmyType(ARMY_TYPE type) {
        if (type == ARMY_TYPE.INFANTRY) return Dice.getD6();
        if (type == ARMY_TYPE.ARTILLERY) return Dice.getD8();
        if (type == ARMY_TYPE.TANK) return Dice.getD10();
        if (type == ARMY_TYPE.APACHE) return Dice.getD12();
        if (type == ARMY_TYPE.CHTULHU) return Dice.getD20();
        throw new IllegalArgumentException("Army Type doesn't exist");
    }

    public int getTotal() {
        return (int)Math.round(this.artillery + this.apaches + this.tanks + this.apaches);
    }

}
