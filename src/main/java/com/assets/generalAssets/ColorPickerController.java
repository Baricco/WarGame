package com.assets.generalAssets;

import java.net.URL;
import java.util.ResourceBundle;

import com.assets.generalAssets.graphics.ColorPickerManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ColorPickerController implements Initializable {

    @FXML
    private Pane hexColorSelector;

    @FXML
    private TextField hexColorTextField;

    @FXML
    private Slider hueSlider;

    private ColorPickerManager colorPickerManager;
   

    @FXML
    void changeHue(MouseEvent event) {
        colorPickerManager.setHueValue(hueSlider.getValue());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.colorPickerManager = new ColorPickerManager(hexColorSelector, hexColorTextField, hueSlider);

        colorPickerManager.setGradient();
    }

}
