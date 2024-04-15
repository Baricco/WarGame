package com.assets.gameAssets;

import com.assets.SVGAssets.XMLParser;

public class State {

    private String name;
    private String Id;

    private double money;                   // soldi totali
    private double stageMoney;              // soldi guadagnati in un turno (tasse)
    private double naturalResources;        // risorse naturali totali
    private double stageNaturalResources;   // risorse naturali generate per ogni turno
    private double refinedResources;        // risorse lavorate totali
    private double stageRefinedResources;   // risorse lavorate generate per ogni turno
    private double reputation;  // questa variabile aumenta quando si fanno le opere per i cittadini e diminuisce quando si fa la leva obbligatoria (-5 ; +5)
    
    private int population;
    private int level; 

    public State(String name, String Id, double money, double stageMoney, double naturalResources, double stageNaturalResources, double refinedResources, double stageRefinedResources, double reputation, int population, int level) {
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
    }

    public State(String fileName, String id) throws Exception {
        
        System.out.println("Sbirro");


        XMLParser xmlParser = new XMLParser(fileName);

        System.out.println("Sburro");


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
        } else {
            throw new Exception("State with ID: " + Id + " not found in " + fileName);
        }

        // Qui settiamo le variabili che non si trovano nell'XML

        this.level  = 0;

        this.reputation = -5;


    
    
    } 

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.Id;
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
            " name='" + getName() + "'" +
            ", Id='" + getId() + "'" +
            ", money='" + getMoney() + "'" +
            ", naturalResources='" + getNaturalResources() + "'" +
            ", stageNaturalResources='" + getStageNaturalResources() + "'" +
            ", refinedResources='" + getRefinedResources() + "'" +
            ", stageRefinedResources='" + getStageRefinedResources() + "'" +
            ", reputation='" + getReputation() + "'" +
            ", population='" + getPopulation() + "'" +
            ", level='" + getLevel() + "'" +
            "}";
    }


}