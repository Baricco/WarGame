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
import com.assets.gameAssets.basics.Army;
import com.assets.gameAssets.basics.City;

import javafx.util.Pair;

public class XMLParser {
    
    Document document;

    public XMLParser(String fileName) {
        try { this.document = Jsoup.parse(Files.readString(new File(fileName).toPath(), StandardCharsets.UTF_8), "", Parser.xmlParser()); } catch (Exception e) { e.printStackTrace(); }
    }

    public State parseFile(String id) {
        Element state = document.getElementById(id);

        ArrayList<City> cities = new ArrayList<>();

        ArrayList<Pair<String, Boolean>> neighboringStates = new ArrayList<>();

        Elements xmlCities = state.getElementsByTag("Cities").first().getElementsByTag("City");

        Elements xmlNeighboringStates = state.getElementsByTag("NeighboringStates").first().getElementsByTag("Border");

        for (Element neighboringState : xmlNeighboringStates) {
            String stateId = neighboringState.ownText();
            boolean isSeaBorder = Boolean.parseBoolean(neighboringState.attr("SeaBorder"));
            neighboringStates.add(new Pair<String, Boolean>(stateId, isSeaBorder));
        }

        for (Element city : xmlCities) {
            
            String cityName = city.ownText();
            boolean hasTrainStation = Boolean.parseBoolean(city.attr("trainStation"));
            
            int coordX, coordY;

            try {
                coordY = Integer.parseInt(city.attr("cordY"));
                coordX = Integer.parseInt(city.attr("cordX"));
            } catch (NumberFormatException e) {
                
                coordY = -1;
                coordX = -1;
            }
            
            cities.add(new City(cityName, hasTrainStation, id, coordY, coordX));

        }

        double armyMultiplier = 1;

        try { armyMultiplier = Double.parseDouble(state.getElementsByTag("ArmyMultiplier").text()); } catch (Exception e) { armyMultiplier = 1; }

        return new State(
            state.getElementsByTag("Name").text(),
            id,
            Double.parseDouble(state.getElementsByTag("Money").text()),
            Double.parseDouble(state.getElementsByTag("StageMoney").text()),
            Double.parseDouble(state.getElementsByTag("NaturalResources").text()), 
            Double.parseDouble(state.getElementsByTag("StageNaturalResources").text()), 
            Double.parseDouble(state.getElementsByTag("RefinedResources").text()), 
            Double.parseDouble(state.getElementsByTag("StageRefinedResources").text()),
            armyMultiplier,
            0, 
            Integer.parseInt(state.getElementsByTag("Population").text()), 
            0,
            new Army(0),
            0,
            0,
            null,
            cities,
            neighboringStates
        );
    }

}
