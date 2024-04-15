package com.assets.gameAssets;

import java.util.ArrayList;


public class GameManager {
    
    private ArrayList<State> states;
    private String fileName;

    public GameManager(String fileName) {
        this.states = new ArrayList<>();
        this.fileName = fileName;
    }

    public void addState(String id) {
        try { this.states.add(new State(this.fileName, id)); } catch(Exception e) { System.out.println("Error on State: " + id); }
    }
    


    @Override
    public String toString() {

        String toString = "";

        for (State s : this.states) toString += s.toString() + "\n";

        return toString;
    }

}
