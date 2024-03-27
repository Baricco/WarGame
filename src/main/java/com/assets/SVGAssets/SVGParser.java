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
        try { 
            this.file = Jsoup.parse(new File(fileName));
        } catch(Exception e) { System.out.println("Error, File doesn't exist"); }
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

        // .st0{fill:#ECECEC;stroke:#000000;stroke-width:0.2;stroke-linecap:round;stroke-linejoin:round;}x\

       // svgRegion.setLayoutX(0);
       // svgRegion.setLayoutY(0);
                

        System.out.println("Region Width: " + svgPath.prefWidth(-1) + " Region Height: " + svgPath.prefHeight(svgPath.prefWidth(-1)));

        double originalWidth = svgPath.prefWidth(-1);
        double originalHeight = svgPath.prefHeight(originalWidth);

        svgPath.setScaleX(container.getPrefWidth() / originalWidth);
        svgPath.setScaleY(container.getPrefHeight() / originalHeight);


        Region svgRegion = new Region();
        
        svgRegion.setShape(svgPath);

        //svgRegion.setPrefSize(container.getPrefWidth(), container.getPrefHeight()); // IL PROBLEMA STA IN QUESTA RIGA, PERCHE' IMPOSTA LA
        // DIMENSIONE DI OGNI REGION ALLA DIMENSIONE DEL PAIN, MA NON SO A COSA IMPOSTARLA PERCHE' SE TOLGO QUESTA RIGA IMPOSTA LE DIMENSIONI A 0
        // BISOGNA CREARE UNA FUNZIONE CHE CALCOLA LARGHEZZA E ALTEZZA DI OGNI REGION PARTENDO DAL PATH SVG E SETTI QUESTE DIMENSIONI NELLA RIGA SOPRA


        return svgRegion;
    }


    // */



}
