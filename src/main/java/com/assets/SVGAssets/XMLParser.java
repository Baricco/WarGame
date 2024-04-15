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
        Element state = this.document.getElementById(id);

        // state è null madonna senza mani
        System.out.println(state);

        return new State(
            state.getElementsByTag("Name").text(),
            id,
            Double.parseDouble(state.getElementsByTag("Money").text()),
            Double.parseDouble(state.getElementsByTag("StageMoney").text()),
            Double.parseDouble(state.getElementsByTag("NaturalResources").text()), 
            Double.parseDouble(state.getElementsByTag("StageNaturalResources").text()), 
            Double.parseDouble(state.getElementsByTag("RefinedResources").text()), 
            Double.parseDouble(state.getElementsByTag("StageRefinedResources").text()),
            0, 
            Integer.parseInt(state.getElementsByTag("Population").text()), 
            0
        );
    }

}
