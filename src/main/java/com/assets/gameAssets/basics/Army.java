package com.assets.gameAssets.basics;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Army {

    private double infantry;
    private double artillery;
    private double tanks;
    private double apaches;
    private int attackModifierValue;
    private int defenseModifierValue;
    public static final int SOLDIERS_PER_DICE = 100000;
    public static final int MAX_MODIFIER = 3;
    public static final int MIN_MODIFIER = 0;

    public enum ARMY_TYPE {
        EMPTY,
        INFANTRY,
        ARTILLERY,
        TANK,
        APACHE,
        CHTULHU
    }

    private Dice dice;

    public Army() {
        this(0);
    }

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

    public void addSoldiers(double infantry, double artillery, double tanks, double apaches) {
        this.infantry += infantry;
        this.artillery += artillery;
        this.tanks += tanks;
        this.apaches += apaches;
    }

    public void addSoldiers(Army army) {
        this.infantry += army.infantry;
        this.artillery += army.artillery;
        this.tanks += army.tanks;
        this.apaches += army.apaches;
    }

    public int getAttackModifier() {
        return this.attackModifierValue;
    }

    public int getDefenseModifier() {
        return this.defenseModifierValue;
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
        if (modifierValue > MAX_MODIFIER) modifierValue = MAX_MODIFIER;
        if (modifierValue < MIN_MODIFIER) modifierValue = MIN_MODIFIER;
        this.attackModifierValue = modifierValue;
    }

    public void incrementDefenseModifierValue() {
        if (this.defenseModifierValue >= MAX_MODIFIER) return;
        this.defenseModifierValue++;
    }

    public void setDefenseModifierValue(int modifierValue) {
        if (modifierValue > MAX_MODIFIER) modifierValue = MAX_MODIFIER;
        if (modifierValue < MIN_MODIFIER) modifierValue = MIN_MODIFIER;
        this.defenseModifierValue = modifierValue;
    }

    public void looseSoldiers(double lostSoldiers, ARMY_TYPE type) {
        if(type == ARMY_TYPE.EMPTY) throw new IllegalArgumentException("No Army Available");
        if (type == ARMY_TYPE.INFANTRY) this.infantry -= lostSoldiers;
        if (type == ARMY_TYPE.ARTILLERY) this.artillery -= lostSoldiers;
        if (type == ARMY_TYPE.TANK) this.tanks -= lostSoldiers;
        if (type == ARMY_TYPE.APACHE) this.apaches -= lostSoldiers;
        if (type == ARMY_TYPE.CHTULHU) this.apaches -= lostSoldiers;

        if (this.infantry < 0) this.infantry = 0;
        if (this.artillery < 0) this.artillery = 0;
        if (this.tanks < 0) this.tanks = 0;
        if (this.apaches < 0) this.apaches = 0;

    }

    public int attack(ARMY_TYPE type, ArrayList<Image> attackerDices) {
        if(type == ARMY_TYPE.EMPTY) {
            return 0;
        }
        dice = getDiceByArmyType(type);

        int attackValue = dice.throwDice();
        attackerDices.add(dice.getIcon("D" + dice.getFaceNumber() + "_" + attackValue));

        attackValue += this.attackModifierValue;

        
        return attackValue;
    }   

    public int defend(ARMY_TYPE type, ArrayList<Image> defenderDices) {
        if(type == ARMY_TYPE.EMPTY) {
            return 0;
        }
        dice = getDiceByArmyType(type);

        int defenceValue = dice.throwDice();
        
        defenderDices.add(dice.getIcon("D" + dice.getFaceNumber() + "_" + defenceValue));

        defenceValue += this.defenseModifierValue;

        return defenceValue;
    }

    public double getTroupsByType(ARMY_TYPE type) {
        if(type == ARMY_TYPE.EMPTY) throw new IllegalArgumentException("No Army Available");
        if (type == ARMY_TYPE.INFANTRY) return this.getInfantry();
        if (type == ARMY_TYPE.ARTILLERY) return this.getArtillery();
        if (type == ARMY_TYPE.TANK) return this.getTanks();
        if (type == ARMY_TYPE.APACHE) return this.getApaches();
        if (type == ARMY_TYPE.CHTULHU) return this.getApaches();
        throw new IllegalArgumentException("Army Type doesn't exist");
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

    public double[] toArray() {
        return new double[] { this.getInfantry(), this.getArtillery(), this.getTanks(), this.getApaches() };
    }

    public boolean isEnoughBig() {
        if (this.infantry >= SOLDIERS_PER_DICE) return true;
        if (this.artillery >= SOLDIERS_PER_DICE) return true;
        if (this.tanks >= SOLDIERS_PER_DICE) return true;
        if (this.apaches >= SOLDIERS_PER_DICE) return true;
        return false;
    }

}
