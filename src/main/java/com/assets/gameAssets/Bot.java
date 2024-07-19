package com.assets.gameAssets;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

import com.assets.gameAssets.basics.Army;
import com.assets.generalAssets.App;

import javafx.util.Pair;

public class Bot extends Player implements Runnable {
    
    final static int MAX_ACTIONS = 5;

    /*
    private String[] stringozze = { 
        "Sei stato adottato",
        "Tuo padre è un tossico",
        "Dune è un film del cazzo",
        "Nano del cazzo",
        "Noi Bot facciamo sesso con tua mamma",
        "Dio, Patria e Famiglia",
        "Falce e Martello, Effige di Mao",
        "Palemmo mmedda",
        "Fanculo i carabinieri, mi scopo Giusy Ferreri",
        "Vannacci >> Tolstoj",
        "Vedi te se mi devo fa dì male dal figlio di Totò Riina",
        "Meglio fare la cacca nella doccia che fare la doccia nella cacca",
        "I <3 Dittatura del Proletariato",
        "Meglio un giorno in pretura che vent'anni di dittatura",
        "Madonna cara",
        "Baricco spero ti venga un colpo",
        "Sborricchio",
        "Galassia lontana, tua madre puttana"
    };
    */

    private static RandomGenerator rnd = RandomGenerator.getDefault();
    private GameManager gameManager;

    public Bot(String name, String hexColor) {
        super(name, hexColor, Player.PlayerType.TYPE_BOT);   
    }

    public void linkGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void run() {
        play();
    }

    private Army getRandomArmy() {
        return getRandomArmy(this.getTotalState().getArmy().getTotal());
    }

    private Army getRandomArmy(int maxArmy) {

        Army totalArmy = new Army(maxArmy);

        double infantry, artillery, tanks, apaches;

        do {
            try { infantry = rnd.nextDouble(0, totalArmy.getInfantry() / Army.SOLDIERS_PER_DICE) * Army.SOLDIERS_PER_DICE; } catch(Exception e) { infantry = 0; }
            try { artillery = rnd.nextDouble(0, totalArmy.getArtillery() / Army.SOLDIERS_PER_DICE) * Army.SOLDIERS_PER_DICE; } catch(Exception e) { artillery = 0; }
            try { tanks = rnd.nextDouble(0, totalArmy.getTanks() / Army.SOLDIERS_PER_DICE) * Army.SOLDIERS_PER_DICE; } catch(Exception e) { tanks = 0; }
            try { apaches = rnd.nextDouble(0, totalArmy.getApaches() / Army.SOLDIERS_PER_DICE) * Army.SOLDIERS_PER_DICE; } catch(Exception e) { apaches = 0; }
        } while(infantry == 0 && artillery == 0 && tanks == 0 && apaches == 0);

        return new Army(
            infantry,
            artillery,
            tanks,
            apaches,
            totalArmy.getAttackModifier()
        );
    }

    @Override
    public boolean acceptsAlliance(Player player) {
        if (rnd.nextDouble(10) > ((player.getLevel() * 0.1) + ((player.getTotalState().getMoney() + player.getTotalState().getArmy().getTotal()) * 0.0000000001) + (player.getTotalState().getWorkForce() * 0.00000001))) {
            System.out.println(this.getName() + " Refused to Ally with " + player.getName());
            return false;
        }
        System.out.println(this.getName() + " Accepts to Ally with " + player.getName());
        return true;
    }

    @Override
    public boolean acceptsNonAggressionPact(Player player) {
        if (rnd.nextDouble(10) > ((player.getLevel() * 0.1) + ((player.getTotalState().getMoney() + player.getTotalState().getArmy().getTotal()) * 0.0000000001) + (player.getTotalState().getWorkForce() * 0.00000001))) {
            System.out.println(this.getName() + " Refused to Treat with " + player.getName());
            return false;
        }
        System.out.println(this.getName() + " Accepts to Ally with " + player.getName());
        return true;
    }

    private void requestAlliance() {

        Player newAlly;
        
        do { newAlly = gameManager.getPlayers().get(rnd.nextInt(1, gameManager.getPlayers().size())); } while(!newAlly.getClass().equals(Bot.class) || newAlly.isAllied(this) || newAlly.equals(this));

        System.out.println(this.getName() + " Requested an Alliance to " + newAlly.getName());

        if (!((Bot)newAlly).acceptsAlliance(this)) return;

        try { newAlly.addAlly(this); } catch(Exception e) {}

    }

    private void requestNonAggressionPact() {

        Player player;
        
        do { player = gameManager.getPlayers().get(rnd.nextInt(1, gameManager.getPlayers().size())); } while(!player.getClass().equals(Bot.class) || player.hasNonAggressionPact(this) || player.equals(this));

        System.out.println(this.getName() + " Requested a Non-Aggression Pact to " + player.getName());

        if (!((Bot)player).acceptsNonAggressionPact(this)) return;

        try { player.addAlly(this); } catch(Exception e) {}

    }

    private State getRandomState() { return this.getAllStates().get(rnd.nextInt(this.getAllStates().size())); }
    

    public void play() {

        // System.out.println(this.getName() + " says: " + stringozze[rnd.nextInt(stringozze.length)]);
        
        int actionNumber = rnd.nextInt(MAX_ACTIONS);

        for (int i = 0; i < actionNumber; i++) {

            int curAction = rnd.nextInt(6); 

            System.out.println("i --> " + i + "\tcurAction --> " + curAction);

            switch (curAction) {
                case 0:     // Attacco

                    ArrayList<State> allStates = this.getAllStates();

                    State attackingState = allStates.get(rnd.nextInt(allStates.size()));
                    
                    if (attackingState.getlastTurnAttacksDone() > this.getLevel() || !this.getTotalState().getArmy().isEnoughBig()) {
                        i--;
                        break;
                    }

                    ArrayList<Pair<String, Boolean>> neighboringStates = attackingState.getNeighboringStates();

                    State attackedState = gameManager.getState(neighboringStates.get(rnd.nextInt(neighboringStates.size())).getKey());

                    if (attackedState == null) { i--; break; }

                    gameManager.selectState(attackedState);

                    System.out.println(this.getName() + " Attacks " + attackedState.getName());

                    gameManager.initDices();

                    gameManager.applyAttackOutcome(
                        gameManager.attackState(
                            getRandomArmy(),
                            attackedState,
                            allStates,
                            gameManager.calcAttackPrice(
                                getNeighboringStates(attackedState),
                                attackedState
                            )
                        )
                    );
                    
                    attackingState.incrementAttacksDone();


                    break;

                case 1:     // Negoziazione
                    
                    if (rnd.nextBoolean()) requestAlliance();
                    else requestNonAggressionPact();
                    
                    break;

                case 2:     // Opere per i Cittadini

                    State selectedState = getRandomState();

                    boolean repeat = false;

                    do {
                        switch (rnd.nextInt(6)) {
                            case 0:
                                selectedState.harvestImprovement();
                                repeat = false;
                                break;
                            case 1:
                                selectedState.industrialImprovement();
                                repeat = false;
                                break;
                            case 2:
                                selectedState.infrastructureBuilding(rnd.nextInt(10, selectedState.getWorkForce() / 2000000));
                                repeat = false;
                                break;
                            case 3:
                                selectedState.infrastructureRenovation(rnd.nextInt(10, selectedState.getWorkForce() / 1000000));
                                repeat = false;
                                break;
                            case 4:
                                selectedState.governmentIncentives();
                                repeat = false;
                                break;
                            case 5:
                                selectedState.cutTaxes(rnd.nextDouble(0.05, 1));
                                repeat = false;
                                break;
                            default:
                                repeat = true;
                                break;
                        }
                    } while(repeat);

                    break;

                case 3:     // Fortificazione
                    
                    if (allStatesFortifying()) break;

                    State fortifyingState;

                    do {
                        fortifyingState = getRandomState();
                    } while(fortifyingState.isFortifiying());
                    
                    System.out.println(this.getName() + " Scheduled to Fortify " + fortifyingState.getName() + " in the Next 2 Turns");
                    
                    fortifyingState.startFortification();

                    break;
                
                case 4:     // Recluta

                    State recruitingState = getRandomState();

                    recruitingState.recruitArmy(getRandomArmy(recruitingState.getStageArmy()), rnd.nextInt(Army.MIN_MODIFIER, Army.MAX_MODIFIER) + 1);
    
                    break;

                case 5:     // Rifornimento
                    
                    if (this.getAllStates().size() < 2) { i--; break; }
                    
                    State supplyingState = getRandomState();

                    double[] maxResources = gameManager.getSupplyResources(this.getTotalState(), supplyingState);
                    
                    double[] supplies = new double[4];

                    for (int j = 0; j < 4; j++) {
                        try {
                            supplies[j] = rnd.nextDouble(maxResources[j]);
                        } catch(IllegalArgumentException e) { supplies[j] = 0; }
                    }

                    gameManager.printSupplies(supplies);

                    ArrayList<State> givingStates = getAllStates();
                    givingStates.remove(supplyingState);

                    supplyingState.supply(supplies, givingStates);
                    
                    break;

                default:    // Se entra qui dentro abbiamo un problema
                    i--;
                    break;
            }


            

            try { Thread.sleep(rnd.nextLong(500, 2000)); } catch (InterruptedException e) { return; }
        }

        try { Thread.sleep(rnd.nextLong(500, 2000)); } catch (InterruptedException e) { return; }

        App.gameManager.passTurn();
    }


    private boolean allStatesFortifying() {
        for (State s : this.getAllStates()) if (!s.isFortifiying()) return false;
        return true;
    }

    @Override
    public void gameLost() {
        if (this.getAllStates().isEmpty()) {
            try { App.gameManager.removePlayer(this); } catch (Exception e) { } 
            gameManager.passTurn();
        }
    }

}
