package com.assets.generalAssets.graphics;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class ColorPickerManager {
    
    private Pane hexColorSelector;
    private TextField hexColorTextField;
    private Slider hueSlider;
    private Color curColor;
    private double hueValue;

    public ColorPickerManager(Pane hexColorSelector, TextField hexColorTextField, Slider hueSlider) {
        this.hexColorSelector = hexColorSelector;
        this.hexColorTextField = hexColorTextField;
        this.hueSlider = hueSlider;
        this.curColor = new Color(1, 1, 1, 1);
        this.setHueValue(this.hueSlider.getValue());
    }

    public void setHueValue(double newHueValue) {
        this.hueValue = newHueValue;
        setGradient();
    }

    public void setGradient() {

    // Creazione del gradiente lineare
    LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
    new Stop(0, Color.BLACK),
    new Stop(1, Color.hsb(this.hueValue, 1, 1)));
    
    System.out.println(Color.hsb(this.hueValue, 1, 1));

    // Applicazione del gradiente come sfondo
    hexColorSelector.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

}
}
