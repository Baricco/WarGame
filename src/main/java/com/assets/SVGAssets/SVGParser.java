package com.assets.SVGAssets;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        try { 
            this.file = Jsoup.parse(new File(fileName));
        } catch(Exception e) { System.out.println("Error, File doesn't exist"); }
    }

    public ArrayList<String> parseFile() {
        ArrayList<String> pathData = new ArrayList<>();
        
        Elements paths = this.file.body().select("g path");
        
        if (paths.isEmpty()) System.out.println("No path Found!");        
        
        for (Element path : paths) pathData.add(path.attr("d"));
        
        return pathData;
    }

    private Pair<Double, Double> getStartingCoords(String svgData) {

        Pattern commandPattern = Pattern.compile("([A-Za-z])([\\s-0-9.]+)", Pattern.DOTALL);
        Matcher commandMatcher = commandPattern.matcher(svgData);
        
        ArrayList<String> params = new ArrayList<>();
        
        while (commandMatcher.find()) params.add(commandMatcher.group(2));

        return new Pair<Double, Double>(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1)));

    }

    public Region createRegionFromSvg(String svgData, Pane container) throws Exception {
        
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgData);    

        double originalWidth = svgPath.prefWidth(-1);
        double originalHeight = svgPath.prefHeight(originalWidth);

        if (originalWidth == 0 || originalHeight == 0) throw new Exception();

        svgPath.setScaleX(container.getPrefWidth() / originalWidth);
        svgPath.setScaleY(container.getPrefHeight() / originalHeight);

        Region svgRegion = new Region();
        
        svgRegion.setShape(svgPath);

        svgRegion.getStyleClass().add("State");

        double widthRatio = container.getPrefWidth() / originalWidth;
        double heightRatio = container.getPrefHeight() / originalHeight; 

        Pair<Double, Double> startCoords = getStartingCoords(svgData);

        svgRegion.setLayoutX(startCoords.getKey());
        svgRegion.setLayoutY(startCoords.getValue());


        System.out.println("Path Width: " + originalWidth + " Path Height: " + originalHeight);
        System.out.println("Container Width: " + container.getPrefWidth() + " Container Height: " + container.getPrefHeight());
        System.out.println("Region Width: " + widthRatio + " Region Height: " + heightRatio);
        System.out.println("Region X: " + svgRegion.getLayoutX() + " Region Y: " + svgRegion.getLayoutY());
        System.out.println("Region class: " + svgRegion.getStyleClass());
        System.out.println("\n\n");


        // QUI BISOGNA CALCOLARE LA POSIZIONE DELLA REGION CHE SI STA PER STAMPARE NEL MAPCONTAINER
        // LA POSIZIONE VA RICAVATA (IN QUALCHE MODO, NON SO COME) DAL FILE SVG
        // UNA VOLTA OTTENUTE LE COORDINATE CHE VANNO ASSEGNATE ALLA REGION, USIAMO IL COMANDO 
        // svgRegion.setLayoutX() E svgRegion.setLayoutY() PER IMPOSTARLE, PER 
        // DI DEFAULT ADESSO LE STAMPA A (0, 0), INFATTI LE METTE TUTTE UNA SOPRA L'ALTRA NELL'ANGOLO IN ALTO A SINISTRA
        // L'ALTERNATIVA POTREBBE ESSERE DI MODIFICARE IN QUALCHE MODO IL FILE SVG IN MODO CHE ANCHE SE VENGONO STAMPATE TUTTE 
        // IN (0, 0), LE REGIONI SI VEDONO IN MANIERA CORRETTA, MA CREDO CHE SIA PIU' DIFFICILE, PERO' FAI COME PREFERISCI PER ME E' UGUALE
        // BUON SOGGIORNO TERMOLESE :)
        
          svgRegion.setPrefSize(widthRatio, heightRatio);

        return svgRegion;
    }


    // */



}
