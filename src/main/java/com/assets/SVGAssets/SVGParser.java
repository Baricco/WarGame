package com.assets.SVGAssets;

import java.io.File;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SVGParser {
    
    private Document file;

    
    public SVGParser(String fileName) {
        try { this.file = Jsoup.parse(new File(fileName)); } catch(Exception e) { System.out.println("Error, File doesn't exist"); }
    }

    public ArrayList<String> parseFile() { 
        
        ArrayList<Element> paths = new ArrayList<Element>();
        ArrayList<String> stringPaths = new ArrayList<String>();
        paths.addAll(this.file.body().getElementsByTag("path"));

        for (Element path : paths) {
            String temp = path.attribute("d").toString();
            stringPaths.add(temp.substring(3,temp.length()-2));
        }
        return stringPaths;
    }
}
