package com.assets.generalAssets;

import java.io.IOException;
import java.util.ArrayList;

import com.assets.SVGAssets.SVGParser;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;


public class startPageController {
    
    @FXML
    private Pane mapContainer;
  
  
    @FXML
    private void initialize() {

        SVGParser svgParser = new SVGParser("src\\main\\resources\\com\\world.svg");
        
        ArrayList<String> svgPaths = svgParser.parseFile();

        // crea arraylist di region e stampale come fai sotto
        ArrayList<Region> regions = new ArrayList<Region>();
        for (int i = 0; i < svgPaths.size(); i++){
            regions.add(createRegionFromSvg(svgPaths.get(i)));
            mapContainer.getChildren().add(regions.get(i)); 
        }
    }


    private static Region createRegionFromSvg(String svgData) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgData);
        
        //DA QUA E' UNA MERDA
        

        Region svgRegion = new Region();
        svgRegion.setShape(svgPath);
        svgRegion.setStyle("-fx-background-color: white; -fx-border-color: black");
        svgRegion.setPrefSize(100, 100); // Imposta le dimensioni preferite

        return svgRegion;
    }

    // DA QUA VA BENE

    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Hai premuto il pulsantozzo");
    }

}
