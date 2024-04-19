package com.assets.gameAssets;

import java.util.ArrayList;

import com.assets.SVGAssets.XMLParser;
import com.assets.gameAssets.basics.City;

import javafx.scene.shape.SVGPath;

public class State {

    private String name;
    private String Id;

    private double money;                   // soldi totali
    private double stageMoney;              // soldi guadagnati in un turno (tasse)
    private double naturalResources;        // risorse naturali totali
    private double stageNaturalResources;   // risorse naturali generate per ogni turno
    private double refinedResources;        // risorse lavorate totali
    private double stageRefinedResources;   // risorse lavorate generate per ogni turno
    private double reputation;              // questa variabile aumenta quando si fanno le opere per i cittadini e diminuisce quando si fa la leva obbligatoria (-5 ; +5)
    private int army;
    private int workForce;
    private ArrayList<City> cities;

    
    private SVGPath path;


    private int population;
    private int level; 

    public State(String name, String Id, double money, double stageMoney, double naturalResources, double stageNaturalResources, double refinedResources, double stageRefinedResources, double reputation, int population, int level, int army, int workForce, SVGPath path, ArrayList<City> cities) {
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
        this.level = level;
        this.path = path;
        this.army = army;
        this.workForce = workForce;
        this.cities = new ArrayList<City>(cities);
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
        } else {
            throw new Exception("State with ID: " + Id + " not found in " + fileName);
        }

        // Qui settiamo le variabili che non si trovano nell'XML

        this.army = 0;

        this.level  = 0;

        this.reputation = -5;

        this.path = path;
    
    
    } 

    public String getName() {
        return this.name;
    }

    public ArrayList<City> getCities() {
        return this.cities;
    }

    public String getId() {
        return this.Id;
    }

    public int getArmy() {
        return this.army;
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

    public double getReputation() {
        return this.reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public int getPopulation() {
        return this.population;
    }

    public int getLevel() {
        return this.level;
    }

    public void increaseLevel(int increase) {
        this.level += increase;
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
            "level='" + getLevel() + "',\n" +
            "}\n";
    }


}