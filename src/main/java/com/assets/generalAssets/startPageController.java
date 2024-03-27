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
        SVGParser svgParser = new SVGParser("src\\main\\resources\\com\\cuore.svg");
        ArrayList<String> svgPaths = svgParser.parseFile();

        // Loop sui path dell'SVG
       for (String svgPathData : svgPaths) {
            Region region = svgParser.createRegionFromSvg(svgPathData, mapContainer);

            mapContainer.getChildren().add(region); // Aggiungi la regione al contenitore

            //System.out.println(region.getShape());

            // qui potresti voler considerare la dimensione della regione appena aggiunta per calcolare l'offset successivo
            
       }
        
    }


    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Hai premuto il pulsantozzo");
    }

}
