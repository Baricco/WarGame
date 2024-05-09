package com.assets.generalAssets.graphics;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class ColorPickerManager {
    
    private Pane hexColorSelector;
    private TextField hexColorTextField;
    private Slider hueSlider;
    private Color curColor;
    private double hueValue;
    private Circle colorSelector;

    public ColorPickerManager(Pane hexColorSelector, TextField hexColorTextField, Slider hueSlider, Circle colorSelector) {
        this.hexColorSelector = hexColorSelector;
        this.hexColorTextField = hexColorTextField;
        this.hueSlider = hueSlider;
        this.curColor = new Color(1, 1, 1, 1);
        this.setHueValue(this.hueSlider.getValue());
        this.colorSelector = colorSelector;
    }

    public void setHueValue(double newHueValue) {
        this.hueValue = (360 * newHueValue) / this.hueSlider.getMax(); 
        setGradient();
    }

    public void setGradient() {

        // Creazione del gradiente orizzontale

        LinearGradient horizontalGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.BLACK),
            new Stop(0.5, Color.hsb(this.hueValue, 1, 1)),
            new Stop(1, Color.WHITE)
        );

        // Applicazione del gradiente come sfondo
        hexColorSelector.setBackground(new Background(new BackgroundFill(horizontalGradient, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

        LinearGradient hueSliderGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.RED),
            new Stop(0.2, Color.MAGENTA),
            new Stop(0.4, Color.BLUE),
            new Stop(0.6, Color.GREEN),
            new Stop(0.8, Color.YELLOW),
            new Stop(1, Color.RED)
        );

        this.hueSlider.setBackground(new Background(new BackgroundFill(hueSliderGradient, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
    
    }

    public void setColorValue(double posX, double posY) {
        if(posX <= hexColorSelector.getWidth() / 2) this.curColor = Color.hsb(this.hueValue, 1, (2 * posX) / hexColorSelector.getWidth());
        else this.curColor = Color.hsb(this.hueValue, (double)(1 - (posX / hexColorSelector.getWidth())) * 2, 1);

        refreshTextField();

    }

    private void refreshTextField() {
        hexColorTextField.setText(getCurHexColor());
    }

    public Color getCurColor() {
        return this.curColor;
    }

    private String formatColorValue(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String getCurHexColor() {
        return "#" + (formatColorValue(this.curColor.getRed()) + formatColorValue(this.curColor.getGreen()) + formatColorValue(this.curColor.getBlue()))
                .toUpperCase();
    }

    public void setColorByHex(String hexColor) {
        
        this.curColor = HEXToHSB(hexColor);

        float[] hsb = java.awt.Color.RGBtoHSB((int)curColor.getRed(), (int)curColor.getGreen(), (int)curColor.getBlue(), null);
        
        setSliderByHue(hsb[0]);
        setCirclePosByHSB(hsb[1], hsb[2]);
    }

    private void setCirclePosByHSB(double saturation, double brightness) {

        this.colorSelector.setLayoutX((saturation + brightness) / 2 * this.hexColorSelector.getWidth());

    }


    private void setSliderByHue(double hue) {
        this.setHueValue(hue * this.hueSlider.getMax());
        this.hueSlider.setValue(hue);
    }


    private Color HEXToHSB(String hexColor) {
        // Rimuove il carattere '#' se presente
        hexColor = hexColor.replace("#", "");

        // Estrae i valori RGB dalla stringa esadecimale
        int red = Integer.valueOf(hexColor.substring(0, 2), 16) / 255;
        int green = Integer.valueOf(hexColor.substring(2, 4), 16) / 255;
        int blue = Integer.valueOf(hexColor.substring(4, 6), 16) / 255;

        return new Color(red, green, blue, 1);

    }


}
