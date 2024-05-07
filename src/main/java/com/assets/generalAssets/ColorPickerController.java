package com.assets.generalAssets;

import java.net.URL;
import java.util.ResourceBundle;

import com.assets.generalAssets.graphics.ColorPickerManager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.shape.Circle;

public class ColorPickerController implements Initializable {

    @FXML
    private Pane hexColorSelector;

    @FXML
    private TextField hexColorTextField;

    @FXML
    private Slider hueSlider;

    private ColorPickerManager colorPickerManager;

    @FXML
    private Circle colorSelector;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.colorPickerManager = new ColorPickerManager(hexColorSelector, hexColorTextField, hueSlider);

        colorPickerManager.setGradient();

        this.hueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    colorPickerManager.setHueValue(new_val.intValue());
                    refreshColor(colorSelector.getLayoutX(), colorSelector.getLayoutY());
                }
        });   
        
    }

    @FXML
    void changeColor(MouseEvent event) {
        colorSelector.setCenterX(event.getX() - hexColorSelector.getWidth() / 2 + 9);
        colorSelector.setCenterY(event.getY() - hexColorSelector.getHeight() / 2 + 9);
        refreshColor(event.getX(), event.getY());
    }

    void refreshColor(double posX, double posY) {
        colorPickerManager.setColorValue(posX, posY);
        colorSelector.setFill(colorPickerManager.getCurColor());
    }

}
