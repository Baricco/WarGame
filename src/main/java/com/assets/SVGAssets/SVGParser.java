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

public class SVGParser {
    
    private Document file;

    
    public SVGParser(String fileName) {
        try { this.file = Jsoup.parse(new File(fileName)); } catch(Exception e) { System.out.println("Error, File doesn't exist"); }
    }

    public ArrayList<String> parseFile() {
        ArrayList<String> pathData = new ArrayList<>();
        Elements paths = this.file.body().getElementsByTag("path");

        for (Element path : paths) {
            pathData.add(path.attr("d"));
            
        }
        return pathData;
    }


    /*

    public Region createRegionFromSvg(ArrayList<String> svgPathsData, Pane container) {
        StringBuilder combinedSvgPaths = new StringBuilder();
        for (String svgPathData : svgPathsData) {
            combinedSvgPaths.append(svgPathData);
        }
        
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(combinedSvgPaths.toString());
        
        Region svgRegion = new Region();
        svgRegion.setShape(svgPath);
    
        // Applica le trasformazioni di scala per adattare la dimensione dei percorsi al contenitore
        double scaleFactor = 1.0; // Imposta il fattore di scala desiderato
        svgRegion.setScaleX(scaleFactor);
        svgRegion.setScaleY(scaleFactor);
    
        // Imposta le dimensioni preferite della regione
        svgRegion.setPrefSize(container.getPrefWidth(), container.getPrefHeight());
    
        return svgRegion;
    }
    
    */

   // /*


    public Region createRegionFromSvg(String svgData, Pane container) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgData);
        
        Region svgRegion = new Region();
        svgRegion.setShape(svgPath);

        // .st0{fill:#ECECEC;stroke:#000000;stroke-width:0.2;stroke-linecap:round;stroke-linejoin:round;}x\
        
        double scaleFactor = 1.0; // Imposta il fattore di scala desiderato
        svgRegion.setScaleX(scaleFactor);
        svgRegion.setScaleY(scaleFactor);

        svgRegion.setStyle("
            -fx-fill: #ECECEC;
            -fx-stroke: #000000; 
            -fx-stroke-width: 0.2; 
            -fx-stroke-linecap: round; 
            -fx-stroke-linejoin: round; 
            -fx-background-color: white; 
            -fx-border-color: black
        ");
        
        svgRegion.setPrefSize(container.getPrefWidth(), container.getPrefHeight()); // Imposta le dimensioni preferite
        
        return svgRegion;
    }


    // */



}
