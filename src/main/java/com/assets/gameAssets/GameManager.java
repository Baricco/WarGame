package com.assets.gameAssets;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.assets.gameAssets.basics.Calendar;
import com.assets.gameAssets.basics.City;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;

public class GameManager {
    
    private HashMap<String, State> states;
    private String fileName;
    private Scene scene;
    private Calendar calendar;
    private ArrayList<Player> players;      // the first player is ALWAYS the Human Player
    //private MapIconManager mapIconManager;


    public GameManager(String fileName, Scene scene) {
        this.states = new HashMap<>();
        this.fileName = fileName;
        this.scene = scene;
        this.calendar = new Calendar((Label)scene.lookup("#calendarLabel"));
        this.players = new ArrayList<>();
        //this.mapIconManager = new MapIconManager((Pane)scene.lookup("#mapContainer"));
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
        try { 
            this.states.put(id, new State(this.fileName, id, path));

            // for (City city : this.states.get(id).getCities()) { if (city.hasTrainStation()) this.mapIconManager.addIcon(city); }

        } catch(Exception e) { e.printStackTrace(); }
        
    }

    private Node getElementByCssSelector(String selector) {
        return this.scene.lookup(selector);
    }

    private void addStringToListView(String listViewSelector, String newString) {
        Node element = (Node)getElementByCssSelector(listViewSelector);
        ListView<String> curListView;
        if (!(element instanceof ListView)) return;
        curListView = (ListView<String>)element;
        curListView.getItems().add(newString);
        curListView.refresh();
    }

    private void setLabelContent(String labelSelector, String content) {
        ((Label)getElementByCssSelector(labelSelector)).setText(content);
    }

    private void setTrapezoidXScale(String paneSelector, double xScale) {
        ((SVGPath)(((Pane)scene.lookup(paneSelector)).lookup(".trapezoid-shape"))).setScaleX(xScale < 1 ? 1 : xScale);
    }

    private String formatHighNumber(double value) {
        int billion = 1000000000, million = 1000000, thousand = 1000;
        if (value >= billion) return Math.round(value / billion) + " Bln";
        if (value >= million) return Math.round(value / million) + " Mln";
        if (value >= thousand) return Math.round(value / thousand) + " K";
        return String.valueOf(value);
    }

    private Human getHumanPlayer() {
        Human curPlayer = null;
        
        try { curPlayer = (Human)this.players.get(0); } catch (IndexOutOfBoundsException e) {  }

        return curPlayer; 
    }

    private void refreshSideMenu(State selectedState) {
        setLabelContent("#menuStateNameLabel", selectedState.getName());
        setLabelContent("#menuStateLvlLabel", String.valueOf(String.valueOf(selectedState.getLevel())));
        
        setTrapezoidXScale("#sideMenu", selectedState.getName().length() * 0.12);    // 0.12 è un numero magico che ho calcolato

        setLabelContent("#sideMenuStateMoneyLabel", String.valueOf(formatHighNumber(selectedState.getMoney())));
        setLabelContent("#sideMenuStateArmyLabel", String.valueOf(formatHighNumber(selectedState.getArmy())));
        
    }


    private void refreshPlayerMenu() {

        State playerState = this.getHumanPlayer().getTotalState();

        setLabelContent("#playerStateNameLabel", playerState.getName());
        setLabelContent("#playerStateLvlLabel", String.valueOf(playerState.getLevel()));
        setTrapezoidXScale("#playerMenu", playerState.getName().length() * 0.12);
        setLabelContent("#playerStateMoneyLabel", String.valueOf(formatHighNumber(playerState.getMoney())));
        setLabelContent("#playerStateArmyLabel", String.valueOf(formatHighNumber(playerState.getArmy())));
        setLabelContent("#playerStateNaturalResourcesLabel" , String.valueOf(formatHighNumber(playerState.getNaturalResources())));
        setLabelContent("#playerStateRefinedResourcesLabel", String.valueOf(formatHighNumber(playerState.getRefinedResources())));
        setLabelContent("#playerStateWorkForceLabel", String.valueOf(formatHighNumber(playerState.getWorkForce())));

    }

    public void manageClick(String id) {
                
        State curState = this.states.get(id);

        if (!this.getHumanPlayer().hasOriginalState()) {
            
            // qui si può mettere la roba per chiedere al player se è sicuro, ma secondo me sporca il codice ed è inutile

            this.getHumanPlayer().setOriginalState(curState);
            
            addStringToListView("#playerStateAlliedStatesListView", curState.getName());

            refreshPlayerMenu();
            
            return;
        }
        
       refreshSideMenu(curState);

    }
    


    @Override
    public String toString() {

        String toString = "Game Manager: { \n\tfileName: " + this.fileName + "\n\tStates: [\n";

        for (Map.Entry<String, State> s : this.states.entrySet()) toString += s.toString() + "\n";
        
        toString += "\t]\n}";

        return toString;
    }

}
