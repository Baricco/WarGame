package com.assets.gameAssets;

import java.util.ArrayList;


public abstract class Player {

    public enum PlayerType {
        TYPE_BOT,               // every bot Player must have this playerType
        TYPE_PLAYER             // every human Player must have this playerType
    }

    private boolean active;     // the Player is active only if it's their turn

    private State originalState;                // the State that the Player starts with

    private String name;                        // the name of the Player
    private String hexColor;                    // the color (hex value) of the States in the mapContainer
    private PlayerType playerType;                  // the type of the player (human, Bot)

    private ArrayList<State> occupiedStates;    // the states that are occupied by the Player
    private ArrayList<Player> allies;           // this Player allies

    public Player(State state, String name, String hexColor) {

        this.originalState = state;

        this.name = name;
        this.hexColor =  hexColor;
        this.playerType = PlayerType.TYPE_PLAYER;
        
        this.occupiedStates = new ArrayList<>();
        this.allies = new ArrayList<>();

        this.active = false;
    
    }

    public Player(State state, String name, String hexColor, PlayerType playerType) {

        this.originalState = state;

        this.name = name;
        this.hexColor =  hexColor;
        this.playerType = playerType;

        
        this.occupiedStates = new ArrayList<>();
        this.allies = new ArrayList<>();
        
        this.active = false;

    }

    public Player(State originalState, String name, String hexColor, PlayerType playerType, ArrayList<State> occupiedStates, ArrayList<Player> allies) {
        
        this.originalState = originalState;
        this.name = name;
        this.hexColor = hexColor;
        this.playerType = playerType;
        this.occupiedStates = occupiedStates;
        this.allies = allies;
        this.active = false;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public State getOriginalState() {
        return this.originalState;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexColor() {
        return this.hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public ArrayList<State> getOccupiedStates() {
        return this.occupiedStates;
    }

    public void occupyState(State occupiedState) throws Exception {
        
        if (this.occupiedStates.contains(occupiedState)) throw new Exception("Error on " + this.name + ": Tried to occupy: " + occupiedState.getName() + "But that state results already occupied");
        
        this.occupiedStates.add(occupiedState);
        
    }

    public void loseOccupiedState(State lostState) throws Exception {
        
        if (!this.occupiedStates.contains(lostState)) throw new Exception("Error on " + this.name + ": Tried to loose: " + lostState.getName() + "But that state results already lost");
        
        this.occupiedStates.remove(lostState);
        
    }

    public void addAlly(Player newAlly) throws Exception {
        
        if (this.allies.contains(newAlly)) throw new Exception("Error on " + this.name + ": Tried to make an alliance with: " + newAlly.getName() + "But that Player results already an ally");
        
        this.allies.add(newAlly);
        
    }

    public void removeAlly(Player lostAlly) throws Exception {
        
        if (!this.allies.contains(lostAlly)) throw new Exception("Error on " + this.name + ": Tried to remove the alliance with: " + lostAlly.getName() + "But that Player results a lost alliance");
        
        this.allies.remove(lostAlly);
        
    }
    


}
