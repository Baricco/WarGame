package com.assets.SVGAssets;

import java.io.File;
import java.io.IOException;
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

public class SVGPathLoader {

    public SVGPathLoader(){

    }

    public ArrayList<SVGPathElement> loadPaths(){
        ArrayList<SVGPathElement> svgPaths = new ArrayList<>();

        try {
            File input = new File("src\\main\\resources\\com\\worldLow.svg");
            Document doc = Jsoup.parse(input, "UTF-8", "");

            Elements paths = doc.select("path");

            for (Element path : paths) {

                svgPaths.add(new SVGPathElement(path.attr("d"), path.attr("id"), path.attr("title")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return svgPaths;
    }

}

