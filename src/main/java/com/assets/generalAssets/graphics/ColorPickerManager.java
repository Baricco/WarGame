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


}
