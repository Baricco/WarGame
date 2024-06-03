package com.assets.gameAssets.basics;

import java.util.HashMap;
import java.util.random.RandomGenerator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Dice {


    private final int faceNumber;
    private RandomGenerator randomGenerator;
    private static HashMap<String, Image> icons;
    public static final int DICE_NAMES[] = { 6, 8, 10, 12, 20 };


    private Dice() {
        this.faceNumber = 0;
        this.randomGenerator = RandomGenerator.getDefault();
        icons = new HashMap<>();
        loadIcons();
    }

    private Dice(int faceNumber) {
        this.faceNumber = faceNumber;
        this.randomGenerator = RandomGenerator.getDefault();
        icons = new HashMap<>();
        loadIcons();
    }

    public static Dice getGenericDice() { return new Dice(); }

    public static Dice getD6() { return new Dice(6); }

    public static Dice getD8() { return new Dice(8); }
    
    public static Dice getD10() { return new Dice(10); }
    
    public static Dice getD12() { return new Dice(12); }

    public static Dice getD20() { return new Dice(20); }

    public int getFaceNumber() { return this.faceNumber; }

    public int throwDice() { return this.randomGenerator.nextInt(1, this.faceNumber); }

    public Image getIcon(String iconName) {
        return icons.get(iconName);
    }

    public void loadIcons() {

        if (!icons.isEmpty()) {
            return;
        }

        for(int i=0;i<DICE_NAMES.length;i++){
            for(int j=1;j<=DICE_NAMES[i];j++){
                String imagePath = getClass().getResource("D" + DICE_NAMES[i] + "_" + j + ".png").toExternalForm();

                icons.put("D" + DICE_NAMES[i] + "_" + j, new Image(imagePath));

            }
        }
    }

}