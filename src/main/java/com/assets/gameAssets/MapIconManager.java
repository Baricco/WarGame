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

        System.out.println("SBORRA");       // BISOGNA CAPIRE COME MAI NON VADA QUESTA COSA

        ImageView newIcon = new ImageView(new Image("../resources/com/icons/" + this.sourceName));

        newIcon.setVisible(true);
        newIcon.setScaleX(0.25);

        System.out.println(new Image("../../../../resources/com/icons" + this.sourceName).getUrl());

        this.icons.add(newIcon);
        
        this.posX = (this.mapContainer.getPrefWidth() / 2) + city.getLongitude();
        this.posY = (this.mapContainer.getPrefHeight() / 2) + city.getLatitude();

        this.icons.getLast().setLayoutX(this.posX);
        this.icons.getLast().setLayoutY(this.posY);

        this.mapContainer.getChildren().add(this.icons.getLast());

        Tooltip.install(this.icons.getLast(), new Tooltip(city.getName()));

        for (ImageView icon : this.icons) System.out.println(icon);

    }


}
