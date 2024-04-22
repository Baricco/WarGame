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
        
        double yUnit = this.mapContainer.getPrefHeight()/180;
        double xUnit = this.mapContainer.getPrefWidth()/360;

        double posX = (this.mapContainer.getPrefWidth() / 2 -25) + city.getLongitude() * (xUnit / 2) * 1.8 - 5;              //*2.75 -30;
        double posY = (this.mapContainer.getPrefHeight() / 2 +75) + city.getLatitude() * yUnit;

        

        Circle newIcon = new Circle(posX, posY, 3); 

        newIcon.setLayoutX(0);
        newIcon.setLayoutY(0);


        this.icons.add(newIcon);


        Circle lastIcon = this.icons.get(this.icons.size() - 1);

        this.mapContainer.getChildren().add(lastIcon);

       // Circle franco = new Circle(this.mapContainer.getPrefWidth() / 2 -25, this.mapContainer.getPrefHeight() / 2 + 75, 6);

       // this.mapContainer.getChildren().add(franco);

        Tooltip curTooltip = new Tooltip(city.getName());

        curTooltip.setShowDelay(Duration.millis(100));

        Tooltip.install(lastIcon, curTooltip);

        //for (Circle icon : this.icons) System.out.println(icon);

    }


}
