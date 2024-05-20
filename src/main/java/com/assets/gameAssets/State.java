package com.assets.gameAssets;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

import com.assets.SVGAssets.XMLParser;
import com.assets.gameAssets.basics.Army;
import com.assets.gameAssets.basics.City;
import com.assets.generalAssets.App;

import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

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
    private int workForce;
    private int stageArmy;
    private ArrayList<City> cities;
    private ArrayList<String> neighboringStates;

    
    private SVGPath path;


    private int population;
    private int lastTurnAttacksDone; 

    public State(String name, String Id, double money, double stageMoney, double naturalResources, double stageNaturalResources, double refinedResources, double stageRefinedResources, int reputation, int population, int level, Army army, int workForce, int stageArmy, SVGPath path, ArrayList<City> cities, ArrayList<String> neighboringStates) {
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
        this.path = path;
        this.army = army;
        this.stageArmy = stageArmy;
        this.workForce = workForce;
        this.cities = new ArrayList<City>(cities);
        this.neighboringStates = new ArrayList<String>(neighboringStates);
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
        this.path = null;
        this.army = army;
        this.stageArmy = stageArmy;
        this.workForce = workForce;
        this.cities = new ArrayList<City>();
        this.neighboringStates = new ArrayList<>();
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
            this.population = state.getPopulation();
            this.cities = state.getCities();
            this.neighboringStates = state.getNeighboringStates();
        } else {
            throw new Exception("State with ID: " + Id + " not found in " + fileName);
        }

        // Qui settiamo le variabili che non si trovano nell'XML

        this.army = new Army((int)(((((this.population + this.workForce) / 10) + ((this.money / 10) + ((this.naturalResources + this.refinedResources) * 10)))) / 50) / 5);
        this.stageArmy = (int)((((((this.population + this.workForce) / 60) + ((this.money / 60) + ((this.naturalResources + this.refinedResources) * 5))) / 50) * ((this.reputation + 5) / 5)) / 5);

        this.workForce = (int)(((this.population / 100) * (this.reputation + 5) / 10) + ((this.money / 5) + (this.naturalResources / 5) + (this.refinedResources / 5)));

        this.lastTurnAttacksDone = 0;

        this.reputation = 0;

        this.path = path;
    
    
    } 



    public ArrayList<String> getNeighboringStates() { return this.neighboringStates; }

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
        return this.neighboringStates.contains(state.getId());
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