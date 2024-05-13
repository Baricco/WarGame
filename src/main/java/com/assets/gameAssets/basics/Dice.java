package com.assets.gameAssets.basics;

import java.util.random.RandomGenerator;

public class Dice {


    private final int faceNumber;
    private RandomGenerator randomGenerator;


    private Dice(int faceNumber) {
        this.faceNumber = faceNumber;
        this.randomGenerator = RandomGenerator.getDefault();

    }

    public static Dice getD6() { return new Dice(6); }

    public static Dice getD8() { return new Dice(8); }
    
    public static Dice getD10() { return new Dice(10); }
    
    public static Dice getD12() { return new Dice(12); }

    public static Dice getD20() { return new Dice(20); }

    public int getFaceNumber() { return this.faceNumber; }

    public int throwDice() { return this.randomGenerator.nextInt(1, this.faceNumber); }


}