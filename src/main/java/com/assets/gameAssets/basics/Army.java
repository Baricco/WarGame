package com.assets.gameAssets.basics;

public class Army {

    private double infantry;
    private double artillery;
    private double tanks;
    private double apaches;
    private int attackModifierValue;
    private int defenseModifierValue;
    public static final int SOLDIERS_PER_DICE = 1000000;

    public enum ARMY_TYPE {
        EMPTY,
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
        this.attackModifierValue = 0;
        this.defenseModifierValue = 0;
    }

    public Army(double infantry, double artillery, double tanks, double apaches, int attackModifierValue) {
        this.infantry = infantry;
        this.artillery = artillery;
        this.tanks = tanks;
        this.apaches = apaches;
        this.attackModifierValue = attackModifierValue;
        this.defenseModifierValue = 0;
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

    public void setAttackModifierValue(int modifierValue) {
        this.attackModifierValue = modifierValue;
    }

    public void setDefenseModifierValue(int modifierValue) {
        this.defenseModifierValue = modifierValue;
    }

    public int attack(ARMY_TYPE type) {
        if(type == ARMY_TYPE.EMPTY) {
            return 0;
        }
        dice = getDiceByArmyType(type);

        return dice.throwDice() + this.attackModifierValue;
    }   

    public int defend(ARMY_TYPE type) {
        if(type == ARMY_TYPE.EMPTY) {
            return 0;
        }
        dice = getDiceByArmyType(type);

        return dice.throwDice() + this.defenseModifierValue;
    }

    private Dice getDiceByArmyType(ARMY_TYPE type) {
        if(type == ARMY_TYPE.EMPTY) throw new IllegalArgumentException("No Army Available");
        if (type == ARMY_TYPE.INFANTRY) return Dice.getD6();
        if (type == ARMY_TYPE.ARTILLERY) return Dice.getD8();
        if (type == ARMY_TYPE.TANK) return Dice.getD10();
        if (type == ARMY_TYPE.APACHE) return Dice.getD12();
        if (type == ARMY_TYPE.CHTULHU) return Dice.getD20();
        throw new IllegalArgumentException("Army Type doesn't exist");
    }

    public int getTotal() {
        return (int)Math.round(this.infantry + this.artillery + this.tanks + this.apaches);
    }

    public ARMY_TYPE getBestArmyType() {
        if(this.apaches >= SOLDIERS_PER_DICE) {
            return ARMY_TYPE.APACHE;
        }
        if(this.tanks >= SOLDIERS_PER_DICE) {
            return ARMY_TYPE.TANK;
        }
        if(this.artillery >= SOLDIERS_PER_DICE) {
            return ARMY_TYPE.ARTILLERY;
        }
        if(this.infantry >= SOLDIERS_PER_DICE) {
            return ARMY_TYPE.INFANTRY;
        }
        return ARMY_TYPE.EMPTY;
    }

}
