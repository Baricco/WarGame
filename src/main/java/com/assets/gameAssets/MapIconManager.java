package com.assets.gameAssets;

import java.util.ArrayList;

import com.assets.gameAssets.basics.City;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MapIconManager {
    
    private ArrayList<Circle> icons;
    
    private Pane mapContainer;

    public MapIconManager(Pane mapContainer) {
        this.icons = new ArrayList<>();
        this.mapContainer = mapContainer;
    }

    public void addIcon(City city) {
        
        double posX = (this.mapContainer.getPrefWidth() / 2) + city.getLongitude();
        double posY = (this.mapContainer.getPrefHeight() / 2) + city.getLatitude();

        Circle newIcon = new Circle(posX, posY, 3); 

        newIcon.setLayoutX(0);
        newIcon.setLayoutY(0);


        this.icons.add(newIcon);


        Circle lastIcon = this.icons.get(this.icons.size() - 1);

        this.mapContainer.getChildren().add(lastIcon);

        Tooltip curTooltip = new Tooltip(city.getName());

        curTooltip.setShowDelay(Duration.millis(100));

        Tooltip.install(lastIcon, curTooltip);

        //for (Circle icon : this.icons) System.out.println(icon);

    }


}
