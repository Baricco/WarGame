package com.assets.gameAssets;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

import com.assets.SVGAssets.XMLParser;
import com.assets.gameAssets.basics.Army;
import com.assets.gameAssets.basics.City;
import com.assets.generalAssets.App;

import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.util.Pair;

public class State {

    public static final int MAX_REPUTATION = 50;
    public static final int MIN_REPUTATION = -50;

    private String name;
    private String Id;

    private double money;                   // soldi totali
    private double stageMoney;              // soldi guadagnati in un turno (tasse)
    private double naturalResources;        // risorse naturali totali
    private double stageNaturalResources;   // risorse naturali generate per ogni turno
    private double refinedResources;        // risorse lavorate totali
    private double stageRefinedResources;   // risorse lavorate generate per ogni turno
    private int reputation;              // questa variabile aumenta quando si fanno le opere per i cittadini e diminuisce quando si fa la leva obbligatoria
    private Army army;
    private Pair<Army, Pair<Integer, Integer>> recruitingArmy;
    private int workForce;
    private int stageArmy;
    private ArrayList<City> cities;
    private ArrayList<Pair<String, Boolean>> neighboringStates;

    
    private SVGPath path;

    private double armyMultiplier;
    private int population;
    private int lastTurnAttacksDone; 

    public State(String name, String Id, double money, double stageMoney, double naturalResources, double stageNaturalResources, double refinedResources, double stageRefinedResources, double armyMultiplier, int reputation, int population, int level, Army army, Pair<Army, Pair<Integer, Integer>> recruitingArmy, int workForce, int stageArmy, SVGPath path, ArrayList<City> cities, ArrayList<Pair<String, Boolean>> neighboringStates) {
        this.name = name;
        this.Id = Id;
        this.money = money;
        this.stageMoney = stageMoney;
        this.naturalResources = naturalResources;
        this.stageNaturalResources = stageNaturalResources;
        this.refinedResources = refinedResources;
        this.stageRefinedResources = stageRefinedResources;
        this.reputation = reputation;
        this.population = population;
        this.lastTurnAttacksDone = 0;
        this.armyMultiplier = armyMultiplier;
        this.path = path;
        this.army = army;
        this.recruitingArmy = recruitingArmy;
        this.stageArmy = stageArmy;
        this.workForce = workForce;
        this.cities = new ArrayList<City>(cities);
        this.neighboringStates = new ArrayList<>(neighboringStates);
    }

    public State(String name, String Id, double money, double stageMoney, double naturalResources, double stageNaturalResources, double refinedResources, double stageRefinedResources, int reputation, int population, int lastTurnAttacksDone, Army army, int workForce, int stageArmy) {
        this.name = name;
        this.Id = Id;
        this.money = money;
        this.stageMoney = stageMoney;
        this.naturalResources = naturalResources;
        this.stageNaturalResources = stageNaturalResources;
        this.refinedResources = refinedResources;
        this.stageRefinedResources = stageRefinedResources;
        this.reputation = reputation;
        this.population = population;
        this.lastTurnAttacksDone = 0;
        this.armyMultiplier = 1;
        this.path = null;
        this.army = army;
        this.recruitingArmy = new Pair<Army, Pair<Integer, Integer>>(new Army(), new Pair<>(0, 0));
        this.stageArmy = stageArmy;
        this.workForce = workForce;
        this.cities = new ArrayList<City>();
        this.neighboringStates = new ArrayList<Pair<String, Boolean>>();
    }

    public State(String fileName, String id, SVGPath path) throws Exception {
        
        XMLParser xmlParser = new XMLParser(fileName);

        State state = xmlParser.parseFile(id);
        
        // Qui settiamo le variabili che si possono settare dall'XML

        if (state != null) {
            this.name = state.getName();
            this.Id = state.getId();
            this.money = state.getMoney();
            this.stageMoney = state.getStageMoney();
            this.naturalResources = state.getNaturalResources();
            this.stageNaturalResources = state.getStageNaturalResources();
            this.refinedResources = state.getRefinedResources();
            this.stageRefinedResources = state.getStageRefinedResources();
            this.armyMultiplier = state.getArmyMultiplier();
            this.population = state.getPopulation();
            this.cities = state.getCities();
            this.neighboringStates = state.getNeighboringStates();
        } else {
            throw new Exception("State with ID: " + Id + " not found in " + fileName);
        }

        // Qui settiamo le variabili che non si trovano nell'XML

        this.army = new Army((int)(((((this.population + this.workForce) / 10) + ((this.money / 10) + ((this.naturalResources + this.refinedResources) * 10)))) / 50) / 5 * this.armyMultiplier);
        
        this.recruitingArmy = new Pair<>(new Army(), new Pair<>(0, 0));

        this.stageArmy = (int)((((((this.population + this.workForce) / 60) + ((this.money / 60) + ((this.naturalResources + this.refinedResources) * 5))) / 50) * ((this.reputation + 5) / 5)) / 5);

        this.workForce = (int)(((this.population / 100) * (this.reputation + 5) / 10) + ((this.money / 5) + (this.naturalResources / 5) + (this.refinedResources / 5)));

        this.lastTurnAttacksDone = 0;

        this.reputation = 0;

        this.path = path;
    
    
    } 

    public double getArmyMultiplier() { return this.armyMultiplier; }

    public void recruitArmy(Army recruitingArmy, int turnCount) {
        if (this.isRecruiting()) return;
        this.recruitingArmy = new Pair<>(recruitingArmy, new Pair<>(turnCount - 1, turnCount));
    }

    public boolean isRecruiting() { 
        return (this.recruitingArmy == null || this.recruitingArmy.getValue().getValue() <= 0);
    }

    public void updateRecruitingArmy() {
        this.recruitingArmy = new Pair<>(this.recruitingArmy.getKey(), new Pair<>(this.recruitingArmy.getValue().getKey(), this.recruitingArmy.getValue().getValue() - 1));
        
        if (this.recruitingArmy.getValue().getValue() <= 0) {
            
            System.out.println(this.getName() + " has finished the training of new soldiers, they now have: \n" + this.recruitingArmy.getKey().getInfantry() + " More Infantry\n" + this.recruitingArmy.getKey().getArtillery() + " More Artillery\n" + this.recruitingArmy.getKey().getTanks() + " More Tanks\n " + this.recruitingArmy.getKey().getApaches() + " More Apaches");

            this.army.addSoldiers(this.recruitingArmy.getKey());

            if (this.recruitingArmy.getValue().getKey() < this.army.getAttackModifier()) this.army.setAttackModifierValue(this.recruitingArmy.getValue().getKey());
            
        }

    }

    public ArrayList<Pair<String, Boolean>> getNeighboringStates() { return this.neighboringStates; }

    public SVGPath getPath() { return this.path; }

    public void setColor(String hexColor) {
        this.path.setFill(Paint.valueOf(hexColor));
    }

    public int getStageArmy() {
        return this.stageArmy;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public boolean isNeighboring(State state) {
        if (this.neighboringStates.contains(new Pair<String, Boolean>(state.getId(), true))) return true;
        if (this.neighboringStates.contains(new Pair<String, Boolean>(state.getId(), false))) return true;
        return false;

    }

    public String getId() {
        return this.Id;
    }

    public Army getArmy() {
        return this.army;
    }

    public int getTotalArmy() {
        return this.army.getTotal();
    }

    public double getMoney() {
        return this.money;
    }

    public double getStageMoney() {
        return this.stageMoney;
    }

    public void collectTax() {
        this.addMoney(this.stageMoney);
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public void subMoney(double money) {
        this.money -= money;
    }

    public double getNaturalResources() {
        return this.naturalResources;
    }

    public double getStageNaturalResources() {
        return this.stageNaturalResources;
    }

    public boolean hasSeaBorder(State state) {
        for (Pair<String, Boolean> s : neighboringStates) if (s.getKey().equals(state.getId()) && s.getValue()) return true;
        return false;
    }

    // questa funzione va richiamata ad ogni turno per far aumentare le risorse naturali
    public void genNewNaturalResources() {
        this.naturalResources += this.stageNaturalResources;
    }

    public double getRefinedResources() {
        return this.refinedResources;
    }

    public double getStageRefinedResources() {
        return this.stageRefinedResources;
    }

    // questa funzione va richiamata ad ogni turno per far aumentare le risorse lavorate
    public void genNewRefinedResources() {
        this.refinedResources += this.stageRefinedResources;
    }

    public int getReputation() {
        return this.reputation;
    }

    public void increaseReputation() {
        if(this.reputation < MAX_REPUTATION) {
            this.reputation++;
        }
    }

    public void decreaseReputation() {
        if(this.reputation > MIN_REPUTATION) {
            this.reputation--;
        }
    }

    public int getPopulation() {
        return this.population;
    }

    public int getlastTurnAttacksDone() {
        return this.lastTurnAttacksDone;
    }

    public int getWorkForce() {
        return this.workForce;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "',\n" +
            "Id='" + getId() + "',\n" +
            "money='" + getMoney() + "',\n" +
            "naturalResources='" + getNaturalResources() + "',\n" +
            "stageNaturalResources='" + getStageNaturalResources() + "',\n" +
            "refinedResources='" + getRefinedResources() + "',\n" +
            "stageRefinedResources='" + getStageRefinedResources() + "',\n" +
            "reputation='" + getReputation() + "',\n" +
            "population='" + getPopulation() + "',\n" +
            "lastTurnAttacksDone='" + getlastTurnAttacksDone() + "',\n" +
            "}\n";
    }

    public String getRandomCityName() {

        RandomGenerator rnd = RandomGenerator.getDefault();

        return this.cities.get(rnd.nextInt(this.cities.size())).getName();
    }

    public void incrementAttacksDone() {
        this.lastTurnAttacksDone++;
    }


}