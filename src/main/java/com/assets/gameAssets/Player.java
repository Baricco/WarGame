package com.assets.gameAssets;

import java.util.ArrayList;

import com.assets.gameAssets.basics.Army;
import com.assets.generalAssets.App;

import javafx.util.Pair;


public abstract class Player {


    public static final int MAX_LEVEL = 20;

    public enum PlayerType {
        TYPE_BOT,               // every bot Player must have this playerType
        TYPE_PLAYER             // every human Player must have this playerType
    }

    private boolean active;     // the Player is active only if it's their turn

    private State originalState;                // the State that the Player starts with

    private String name;                        // the name of the Player
    private String hexColor;                    // the color (hex value) of the States in the mapContainer (starts with # character)
    private PlayerType playerType;              // the type of the player (human, Bot)
    private int level;                          // the level of the player    

    private ArrayList<State> occupiedStates;    // the states that are occupied by the Player
    private ArrayList<Player> allies;           // this Player allies

    public Player(String name, String hexColor) {

        this.originalState = null;

        this.name = name;
        this.hexColor =  hexColor;
        this.playerType = PlayerType.TYPE_PLAYER;
        
        this.occupiedStates = new ArrayList<>();
        this.allies = new ArrayList<>();

        this.active = true;
        this.level = 1;

        System.out.println(hexColor);
    
    }

    public Player(String name, String hexColor, PlayerType playerType) {

        this.originalState = null;

        this.name = name;
        this.hexColor =  hexColor;
        this.playerType = playerType;

        
        this.occupiedStates = new ArrayList<>();
        this.allies = new ArrayList<>();
        
        this.active = true;
        this.level = 1;

    }

    public Player(State originalState, String name, String hexColor, PlayerType playerType, ArrayList<State> occupiedStates, ArrayList<Player> allies) {
        
        this.originalState = originalState;
        this.name = name;
        this.hexColor = hexColor;
        this.playerType = playerType;
        this.occupiedStates = occupiedStates;
        this.allies = allies;
        this.active = true;
        this.level = 1;
    }

    public boolean hasOriginalState() {
        return (this.originalState != null);
    }

    public void updateTurnActions() {
        for (State s : this.getAllStates()) {
            s.updateResources();
            s.updateFortification();
            s.updateRecruitingArmy();
            s.updateMilitaryConscription();
        }

    }

    public State getTotalState() {

        double money = this.originalState.getMoney();
        double stageMoney = this.originalState.getStageMoney();
        double naturalResources = this.originalState.getNaturalResources();
        double stageNaturalResources = this.originalState.getStageNaturalResources();
        double refinedResources = this.originalState.getRefinedResources();
        double stageRefinedResources = this.originalState.getStageRefinedResources();
        int reputation = this.originalState.getReputation();
        int population = this.originalState.getPopulation();
        int level = this.originalState.getlastTurnAttacksDone();
        Army army = this.originalState.getArmy();
        int workForce = this.originalState.getWorkForce();
        int stageArmy = this.originalState.getStageArmy();

        for (State s : this.occupiedStates) {

            money += s.getMoney();
            stageMoney += s.getStageMoney();
            naturalResources += s.getNaturalResources();
            stageNaturalResources += s.getStageNaturalResources();
            refinedResources += s.getRefinedResources();
            stageRefinedResources += s.getStageRefinedResources();
            reputation += s.getReputation();
            population += s.getPopulation();
            level += s.getlastTurnAttacksDone();
            army.addSoldiers(s.getArmy());
            workForce += s.getWorkForce();
            stageArmy += s.getStageArmy();

        }

        return new State(
                this.originalState.getName(),
                "", 
                money, 
                stageMoney, 
                naturalResources, 
                stageNaturalResources, 
                refinedResources, 
                stageRefinedResources, 
                reputation, 
                population, 
                level, 
                army, 
                workForce, 
                stageArmy
            );
    }

    public boolean isAllied(State state) {
        
        if (this.hasOccupied(state)) return false;

        for(Player ally : this.allies) if (ally.hasOccupied(state)) return true;
        
        return false;

    }

    public boolean hasNeighboringState(State state) {
        if (this.originalState.isNeighboring(state)) return true;
        for (State s : this.occupiedStates) if (s.isNeighboring(state)) return true;
        return false;
    }

    public void increaseLevel () {
        if(this.level < MAX_LEVEL) {
            this.level++;
            App.gameManager.refreshLevelLabel();
        }
    }

    public ArrayList<Pair<State, Boolean>> getNeighboringStates(State state) {

        ArrayList<Pair<State, Boolean>> neighboringStates = new ArrayList<>();

        if (this.originalState.isNeighboring(state)) neighboringStates.add(new Pair<State, Boolean>(this.originalState, true));
        for (State s : this.occupiedStates) if (s.isNeighboring(state)) neighboringStates.add(new Pair<State, Boolean>(s, s.hasSeaBorder(state)));
        return neighboringStates;
    }

    public boolean hasOccupied(State state) {
        if (this.originalState != null && this.originalState.getId() == state.getId()) return true;
        return this.occupiedStates.contains(state);
    }

    private void setStateColor(State state) {
        state.setColor(this.getHexColor());
    }

    public void setOriginalState(State originalState) {
        if (this.originalState == null) this.originalState = originalState;
        System.out.println(this.name + " starts from " + originalState.getName());
        this.setStateColor(originalState);
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

        this.setStateColor(occupiedState);

        if(this.occupiedStates.size() == level * 2) increaseLevel();
        
        
    }

    public void loseOccupiedState(State lostState) throws Exception {
        
        if (!this.occupiedStates.contains(lostState)) throw new Exception("Error on " + this.name + ": Tried to loose: " + lostState.getName() + "But that state results already lost");
        
        this.occupiedStates.remove(lostState);
        
    }

    public ArrayList<State> getAllStates() {

        ArrayList<State> allStates = new ArrayList<>();
        
        allStates.add(this.originalState);

        allStates.addAll(this.occupiedStates);

        return allStates;
    }

    public void addAlly(Player newAlly) throws Exception {
        
        if (this.allies.contains(newAlly)) throw new Exception("Error on " + this.name + ": Tried to make an alliance with: " + newAlly.getName() + "But that Player results already an ally");
        
        this.allies.add(newAlly);
        
    }

    public void removeAlly(Player lostAlly) throws Exception {
        
        if (!this.allies.contains(lostAlly)) throw new Exception("Error on " + this.name + ": Tried to remove the alliance with: " + lostAlly.getName() + "But that Player results a lost alliance");
        
        this.allies.remove(lostAlly);
        
    }

    public int getLevel() {
        return this.level;
    }
    


}
