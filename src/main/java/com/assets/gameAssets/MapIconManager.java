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

        if (city.getLongitude() == -1 || city.getLatitude() == -1) return;

        double xUnit = this.mapContainer.getPrefWidth()/360f;
        double yUnit = this.mapContainer.getPrefHeight()/360f;


        double posX = (this.mapContainer.getPrefWidth() / 2f) + city.getLongitude() * xUnit;
        double posY = (this.mapContainer.getPrefHeight() / 2f) + (Math.log(Math.tan((45f + (double)Math.abs(city.getLatitude() / 2f)) * Math.PI / 180))) * yUnit;


        System.out.println(Math.log(Math.tan((45f + (double)Math.abs(city.getLatitude() / 2f)) * Math.PI / 180)));
        System.out.println(city.getName() + " { long: " + city.getLongitude() + ", lat: " + city.getLatitude() + "; x: " + posX + ", y: " + posY + " }");

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
