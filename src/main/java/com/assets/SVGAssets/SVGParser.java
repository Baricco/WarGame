package com.assets.SVGAssets;

import java.io.File;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.util.Pair;

public class SVGParser {
    
    private Document file;

    
    public SVGParser(String fileName) {
        try { this.file = Jsoup.parse(new File(fileName)); } catch(Exception e) { System.out.println("Error, File doesn't exist"); }
    }

    public ArrayList<Pair<String, String>> parseFile() { 
        ArrayList<Pair<String, String>> pathsWithPositions = new ArrayList<>();
        Elements paths = this.file.body().getElementsByTag("path");

        for (Element path : paths) {
            String pathData = path.attr("d");
            String xPos = "0";
            String yPos = "0";
            pathsWithPositions.add(new Pair<>(pathData, "translate(" + xPos + "," + yPos + ")"));
        }
        return pathsWithPositions;
    }

    public Region createRegionFromSvg(String svgData, String position, Pane container) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgData);
        
        Region svgRegion = new Region();
        svgRegion.setShape(svgPath);

        // .st0{fill:#ECECEC;stroke:#000000;stroke-width:0.2;stroke-linecap:round;stroke-linejoin:round;}x\

        svgRegion.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-transform: 0px 0px");
        svgRegion.setPrefSize(container.getPrefWidth(), container.getPrefHeight()); // Imposta le dimensioni preferite
    
        // Parsing della trasformazione di posizione
        double xPos = 0;
        double yPos = 0;
        if (position.startsWith("translate(") && position.endsWith(")")) {
            String[] positionValues = position.substring(10, position.length() - 1).split(",");
            if (positionValues.length == 2) {
                xPos = Double.parseDouble(positionValues[0]);
                yPos = Double.parseDouble(positionValues[1]);
            }
        }
    
        // Applicazione della traslazione
        svgRegion.setLayoutX(xPos);
        svgRegion.setLayoutY(yPos);
    
        return svgRegion;
    }
}
