package com.assets.gameAssets;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.assets.gameAssets.basics.Calendar;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;

public class GameManager {
    
    private HashMap<String, State> states;
    private String fileName;
    private Scene scene;
    private Calendar calendar;
    private ArrayList<Player> players;


    public GameManager(String fileName, Scene scene) {
        this.states = new HashMap<>();
        this.fileName = fileName;
        this.scene = scene;
        this.calendar = new Calendar((Label)scene.lookup("#calendarLabel"));
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player newPlayer) throws Exception {
        if (this.players.contains(newPlayer)) throw new Exception("Error, tried to had: " + newPlayer.getName() + " in the game, but it is already been added");
        
        if (this.players.add(newPlayer)) System.out.println(newPlayer.getName() + " was added to this Game, Say hi to " + newPlayer.getName());

    }

    public void removePlayer(Player player) throws Exception {
        if (!this.players.contains(player)) throw new Exception("Error, tried to remove: " + player.getName() + " from the game, but it is already been removed");
        
        if (this.players.remove(player)) System.out.println(player.getName() + " exited the Game, We will miss you " + player.getName() + " :'( ");

    }

    public void addState(String id, SVGPath path) {
        try { this.states.put(id, new State(this.fileName, id, path)); } catch(Exception e) { e.printStackTrace(); }
    }

    private Node getElementByCssSelector(String selector) {
        return this.scene.lookup(selector);
    }

    private void setLabelContent(String labelSelector, String content) {
        ((Label)getElementByCssSelector(labelSelector)).setText(content);
    }

    private void setTrapezoidXScale(String paneSelector, double xScale) {
        ((SVGPath)(((Pane)scene.lookup(paneSelector)).lookup(".trapezoid-shape"))).setScaleX(xScale < 1 ? 1 : xScale);
    }

    public void manageClick(String id) {
        
        State curState = this.states.get(id);
        
        setLabelContent("#menuStateNameLabel", curState.getName());
        setLabelContent("#menuStateLvlLabel", String.valueOf(String.valueOf(curState.getLevel())));
        
        setTrapezoidXScale("#sideMenu", curState.getName().length() * 0.12);    // 0.12 è un numero magico che ho calcolato

    }
    


    @Override
    public String toString() {

        String toString = "Game Manager: { \n\tfileName: " + this.fileName + "\n\tStates: [\n";

        for (Map.Entry<String, State> s : this.states.entrySet()) toString += s.toString() + "\n";
        
        toString += "\t]\n}";

        return toString;
    }

}
