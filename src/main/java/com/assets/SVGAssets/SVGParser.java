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

    public Region createRegionFromSvg(String svgData, Pane container) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgData);
        
        Region svgRegion = new Region();
        svgRegion.setShape(svgPath);

        // .st0{fill:#ECECEC;stroke:#000000;stroke-width:0.2;stroke-linecap:round;stroke-linejoin:round;}x\

        svgRegion.setStyle("-fx-background-color: white; -fx-border-color: black");
        svgRegion.setPrefSize(container.getPrefWidth(), container.getPrefHeight()); // Imposta le dimensioni preferite
        return svgRegion;
    }
}
