package com.assets.generalAssets;

import java.io.IOException;
import java.util.ArrayList;

import com.assets.SVGAssets.SVGParser;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.util.Pair;


public class startPageController {
    
    @FXML
    private Pane mapContainer;
    
    
  
    @FXML
    private void initialize() {
        SVGParser svgParser = new SVGParser("src\\main\\resources\\com\\world.svg");
        ArrayList<Pair<String, String>> svgPaths = svgParser.parseFile();

        double offsetX = 0; // Offset iniziale X nel contenitore
        double offsetY = 0; // Offset iniziale Y nel contenitore

        // Loop sui path dell'SVG
        for (Pair<String, String> svgPathData : svgPaths) {
            Region region = svgParser.createRegionFromSvg(svgPathData.getKey(), svgPathData.getValue(), mapContainer);
            
            // Imposta la posizione della regione considerando l'offset
            region.setLayoutX(offsetX);
            region.setLayoutY(offsetY);

            mapContainer.getChildren().add(region); // Aggiungi la regione al contenitore

            // qui potresti voler considerare la dimensione della regione appena aggiunta per calcolare l'offset successivo
            
        }
    }


    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Hai premuto il pulsantozzo");
    }

}
