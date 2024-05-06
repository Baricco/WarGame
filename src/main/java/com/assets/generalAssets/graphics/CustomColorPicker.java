package com.assets.generalAssets.graphics;


import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class CustomColorPicker extends ColorPicker {
    
    public CustomColorPicker() {
        super(Color.rgb(205, 220, 57)); // Set initial color
        
        // Hide the color label
        setStyle("-fx-color-label-visible: false; " +
                 "-fx-background-color: transparent; " +
                 "-fx-background-radius: 0; " +
                 "-fx-padding: 0; " +
                 "-fx-display-caret: false; " +
                 "-fx-border-color: rgba(3, 1, 1, 0.08); " +
                 "-fx-border-width: 1; " +
                 "-fx-border-radius: 2; " +
                 "-fx-effect: dropshadow(0 2px 2px rgba(0,0,0,0.16));");
        
        // Add an event handler for color change
        valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String hexColor = "#" + newValue.toString().substring(2, 8);
                // Here you can perform any action with the picked color
                // For example, change the background color of a node
                // rootNode.setStyle("-fx-background-color: " + hexColor + ";");
            }
        });
    }
}