package com.assets.gameAssets;

import java.util.ArrayList;

import com.assets.gameAssets.basics.City;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MapIconManager {
    
    private ArrayList<ImageView> icons;

    private double posX, posY;

    private String sourceName;
    
    private Pane mapContainer;

    public MapIconManager(String sourceName, Pane mapContainer) {
        this.posX = 0;
        this.posY = 0;
        this.icons = new ArrayList<>();
        this.mapContainer = mapContainer;
        this.sourceName = sourceName;
    }

    public void addIcon(City city) {

        ImageView newIcon = new ImageView(new Image("com/icons/" + this.sourceName));       // L'ERRORE E' QUESTA RIGA PERCHE' COME AL SOLITO I PERCORSI IN JAVA NON HANNO SENSO

        newIcon.setVisible(true);
        newIcon.setScaleX(0.25);
        
        this.icons.add(newIcon);
        
        this.posX = (this.mapContainer.getPrefWidth() / 2) + city.getLongitude();
        this.posY = (this.mapContainer.getPrefHeight() / 2) + city.getLatitude();

        ImageView lastIcon = this.icons.get(this.icons.size() - 1);

        lastIcon.setLayoutX(this.posX);
        lastIcon.setLayoutY(this.posY);

        this.mapContainer.getChildren().add(lastIcon);

        Tooltip.install(lastIcon, new Tooltip(city.getName()));

        for (ImageView icon : this.icons) System.out.println(icon);

    }


}
