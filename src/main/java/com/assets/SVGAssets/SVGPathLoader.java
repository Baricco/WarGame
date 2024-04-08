package com.assets.SVGAssets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SVGPathLoader {

    private String fileLocation;


    public SVGPathLoader(String fileLocation){
        this.fileLocation = fileLocation;
    }

    public ArrayList<SVGPathElement> loadPaths(){
        ArrayList<SVGPathElement> svgPaths = new ArrayList<>();

        try {
            File input = new File(this.fileLocation);
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

