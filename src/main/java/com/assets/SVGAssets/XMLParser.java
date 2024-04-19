package com.assets.SVGAssets;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.assets.gameAssets.State;
import com.assets.gameAssets.basics.City;

public class XMLParser {
    
    Document document;

    public XMLParser(String fileName) {
        try { this.document = Jsoup.parse(Files.readString(new File(fileName).toPath(), StandardCharsets.UTF_8), "", Parser.xmlParser()); } catch (Exception e) { e.printStackTrace(); }
    }

    public State parseFile(String id) {
        Element state = document.getElementById(id);

        ArrayList<City> cities = new ArrayList<>();

        Elements xmlCities = state.getElementsByTag("Cities");

        for (Element city : xmlCities) {
            
            String cityName = city.text();
            boolean hasTrainStation = Boolean.parseBoolean(city.attr("trainStation"));
            
            int coordX, coordY;

            try {

                coordY = Integer.parseInt(city.attr("coordY"));
                coordX = Integer.parseInt(city.attr("coordX"));
            } catch (NumberFormatException e) {

                coordY = -1;
                coordX = -1;
            }
            
            cities.add(new City(cityName, hasTrainStation, id, coordY, coordX));

        }

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
            null,
            cities
        );
    }

}
