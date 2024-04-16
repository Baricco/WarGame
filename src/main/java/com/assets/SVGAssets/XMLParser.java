package com.assets.SVGAssets;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import com.assets.gameAssets.State;

public class XMLParser {
    
    Document document;

    public XMLParser(String fileName) {
        try { this.document = Jsoup.parse(Files.readString(new File(fileName).toPath(), StandardCharsets.UTF_8), "", Parser.xmlParser()); } catch (Exception e) { e.printStackTrace(); }
    }

    public State parseFile(String id) {
        Element state = document.getElementById(id);

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
            0,
            0,
            0,
            null
        );
    }

}
