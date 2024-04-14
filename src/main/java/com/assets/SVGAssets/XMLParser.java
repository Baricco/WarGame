package com.assets.SVGAssets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.assets.gameAssets.State;

public class XMLParser {
    
    Document document;

    public XMLParser(String fileName) {
        this.document = Jsoup.parse(fileName, "UTF-8");
    }

    public State parseFile(String id) {
        Element state = (document.select("#" + id)).first();

        return new State(
            state.select("Name").text(),
            id,
            Double.parseDouble(state.select("Money").text()),
            Double.parseDouble(state.select("StageMoney").text()),
            Double.parseDouble(state.select("NaturalResources").text()), 
            Double.parseDouble(state.select("StageNaturalResources").text()), 
            Double.parseDouble(state.select("RefinedResources").text()), 
            Double.parseDouble(state.select("StageRefinedResources").text()),
            0, 
            Integer.parseInt(state.select("Population").text()), 
            0
        );
    }

}
