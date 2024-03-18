package com.assets.gameAssets;

import java.util.random.RandomGenerator;

public class Dice {


    private final int number;
    private RandomGenerator randomGenerator;


    public Dice(int number) {
        this.number = number;
        this.randomGenerator = RandomGenerator.getDefault();

    }

    public int getNumber() { return this.number; }

    public int throwDice() { return this.randomGenerator.nextInt(1, this.number); }





}

