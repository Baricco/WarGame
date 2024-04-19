package com.assets.gameAssets;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MapIconManager {
    
    private ArrayList<ImageView> icons;

    private double posX, posY;
    
    private Pane mapContainer;

    public MapIconManager(String sourceName, Pane mapContainer) {
        this.posX = 0;
        this.posY = 0;
        this.icons = new ArrayList<>();
        this.mapContainer = mapContainer;
    }

    public void addIcon(String sourceName, double posX, double posY) {
        icons.add(new ImageView(new Image("../resources/com/icons/" + sourceName)));
        
        this.posX = posX;
        this.posY = posY;

        this.icons.getLast().setLayoutX(this.posX);
        this.icons.getLast().setLayoutY(this.posY);

        this.mapContainer.getChildren().add(this.icons.getLast());
    }


}
