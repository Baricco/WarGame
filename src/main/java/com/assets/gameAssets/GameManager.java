package com.assets.gameAssets;

import javafx.scene.control.Label;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.shape.SVGPath;

public class GameManager {
    
    private HashMap<String, State> states;
    private String fileName;
    private Scene scene;
    private Calendar calendar;

    public GameManager(String fileName, Scene scene) {
        this.states = new HashMap<>();
        this.fileName = fileName;
        this.scene = scene;
        this.calendar = new Calendar((Label)scene.lookup("#calendarLabel"));
    }

    public void addState(String id, SVGPath path) {
        try { this.states.put(id, new State(this.fileName, id, path)); } catch(Exception e) { e.printStackTrace(); }
    }

    public void manageClick(String id) {
        State curState = this.states.get(id);
        Label stateNameLabel = (Label)scene.lookup("#menuStateNameLabel");
        stateNameLabel.setText(curState.getName());
        Label stateLvLabel = (Label)scene.lookup("#menuStateLvlLabel");
        stateLvLabel.setText(String.valueOf(curState.getLevel()));
    }
    


    @Override
    public String toString() {

        String toString = "Game Manager: { \n\tfileName: " + this.fileName + "\n\tStates: [\n";

        for (Map.Entry<String, State> s : this.states.entrySet()) toString += s.toString() + "\n";
        
        toString += "\t]\n}";

        return toString;
    }

}
