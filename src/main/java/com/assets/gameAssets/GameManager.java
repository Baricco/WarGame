package com.assets.gameAssets;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.random.RandomGenerator;

import com.assets.gameAssets.basics.Army;
import com.assets.gameAssets.basics.Calendar;
import com.assets.gameAssets.basics.Army.ARMY_TYPE;
import com.assets.generalAssets.App;
import com.assets.generalAssets.graphics.ToggleSwitch;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Pair;

public class GameManager {

    @FXML
    private AnchorPane ArmySelectorContainer;

    @FXML
    private AnchorPane DiceIconContainer;

    @FXML
    private Label maxSoldiersLabel;

    @FXML
    private Label selectedSoldiersLabel;

    @FXML
    private Slider soldierSlider;

    @FXML
    private AnchorPane attackMenu;

    @FXML
    private AnchorPane recruitMenu;

    @FXML
    private AnchorPane attackerDiceContainer;

    @FXML
    private AnchorPane defenderDiceContainer;
    
    private HashMap<String, State> states;
    private String fileName;
    private Scene scene;
    private Calendar calendar;
    private ArrayList<Player> players;      // the first player is ALWAYS the Human Player

    private static State curSelectedState;
    private int selectedPlayerIndex;
    public static Thread botThread = new Thread(); 
    

    public GameManager() { }


    public GameManager(String fileName, Scene scene) {
        this.states = new HashMap<>();
        this.fileName = fileName;
        this.scene = scene;
        this.calendar = new Calendar((Label)scene.lookup("#calendarLabel"));
        this.players = new ArrayList<>();
        //this.mapIconManager = new MapIconManager((Pane)scene.lookup("#mapContainer"));
        this.selectedPlayerIndex = 0;
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
        return App.gameManager.scene.lookup(selector);
    }



    private void addStringToListView(String listViewSelector, String newString) {
        Node element = (Node)getElementByCssSelector(listViewSelector);
        ListView<String> curListView;
        if (!(element instanceof ListView)) return;
        curListView = (ListView<String>)element;
        curListView.getItems().add(newString);
        curListView.scrollTo(curListView.getItems().size());
    }


/*
    private void setListViewCellColor(String listViewSelector, int cellIndex, boolean outcome) {
        String curClass = outcome ? "wonBattle" : "lostBattle";
        String otherClass = outcome ? "lostBattle" : "wonBattle";
    
        ListView<String> listView = (ListView<String>)getElementByCssSelector(listViewSelector);
        System.out.println("ListView: " + listView);
    
        if (listView == null) {
            System.err.println("ListView not found with selector: " + listViewSelector);
            return;
        }


        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (outcome) {
                            setTextFill(Color.GREEN);
                        } else {
                            setTextFill(Color.RED);
                        }
                        setText(item);
                    }
                };
            }
        });

        // listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
        //     @Override
        //     public ListCell<String> call(ListView<String> param) {
        //         return updateItem(item, empty) -> {
        //             super.updateItem(item, empty);
        //             if (index == cellIndex) {
        //                 System.out.println("Applying class at index " + index);
        //                 if (outcome) {
        //                     System.out.println("Applying " + curClass + " to cell " + cellIndex);
        //                     if (!getStyleClass().contains(curClass)) {
        //                         getStyleClass().add(curClass);
        //                     }
        //                 } else {
        //                     System.out.println("Applying " + otherClass + " to cell " + cellIndex);
        //                     if (!getStyleClass().contains(otherClass)) {
        //                         getStyleClass().add(otherClass);
        //                     }
        //                 }
        //             }
        //                 }
        //                 System.out.println("Cell styles: " + getStyleClass());
        //             }
        //         });
    
        listView.refresh();
    }



    private void addStringToListView(String listViewSelector, String newString, boolean outcome) {
        addStringToListView(listViewSelector, newString);
        int cellIndex = ((ListView<String>)getElementByCssSelector(listViewSelector)).getItems().size();
        setListViewCellColor(listViewSelector, cellIndex, outcome);
    }


*/
    private void setLabelContent(String labelSelector, String content) {
        ((Label)getElementByCssSelector(labelSelector)).setText(content);
    }

    private void setToggleSwitch(String toggleSwitchSelector, EventHandler<Event> actionHandler) {
        ToggleSwitch toggleSwitch = ((ToggleSwitch)getElementByCssSelector(toggleSwitchSelector));
        
        toggleSwitch.setVisible(true);

        toggleSwitch.setAction(actionHandler);
    }

    private void setButton(String buttonSelector, String buttonText, EventHandler<ActionEvent> clickHandler) {
        
        Button btn = ((Button)getElementByCssSelector(buttonSelector));
        btn.setVisible(true);
        btn.setOnAction(clickHandler);
        btn.setText(buttonText);        

    }

    private void setTrapezoidXScale(String paneSelector, double xScale) {
        ((SVGPath)(((Pane)getElementByCssSelector(paneSelector)).lookup(".trapezoid-shape"))).setScaleX(xScale < 1 ? 1 : xScale);
    }

    private String formatHighNumber(double value) {
        int billion = 1000000000, million = 1000000, thousand = 1000;
        if (value >= billion) return Math.round(value / billion) + " Bln";
        if (value >= million) return Math.round(value / million) + " Mln";
        if (value >= thousand) return Math.round(value / thousand) + " K";
        return String.valueOf(Math.round(value));
    }

    private Human getHumanPlayer() {
        Human curPlayer = null;
        
        try { curPlayer = (Human)App.gameManager.players.get(0); } catch (IndexOutOfBoundsException e) {  }

        return curPlayer; 
    }

    private void refreshRecruitMenu() {
        
        ObservableList<Node> armySelectors = ((AnchorPane)App.gameManager.scene.lookup("#ArmySelectorContainer")).getChildren();

        int maxSoldiers = curSelectedState.getStageArmy() / 4;

        int i = 0; 
        for (Node armySelector : armySelectors) {
            
            Slider curSlider = ((Slider)armySelector.lookup("#soldierSlider"));

            curSlider.setMax(((int)maxSoldiers / Army.SOLDIERS_PER_DICE) * Army.SOLDIERS_PER_DICE);
            curSlider.setMajorTickUnit(Army.SOLDIERS_PER_DICE);
            curSlider.setMinorTickCount(0);
            curSlider.setShowTickMarks(false);
            curSlider.setSnapToTicks(true);

            ((Label)armySelector.lookup("#selectedSoldiersLabel")).setText(formatHighNumber(((Slider)armySelector.lookup("#soldierSlider")).getValue()));
            ((Label)armySelector.lookup("#maxSoldiersLabel")).setText(formatHighNumber(maxSoldiers));
            
            // TODO: AGGIUNGERE IL PEZZO DI CODICE CHE AGGIORNA LE LABEL PER L'ATTACKMODFIER E GESTISCE LA PRESSIONE DEI TASTI

            i++;
        }

    }

    private void showOccupiedStateSideMenu(State state) {
        
        
        EventHandler<ActionEvent> supplyStateHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                    // TODO: INSERIRE FUNZIONE IL RIFORNIMENTO
                    System.out.println("Adesso Rifornisco " + state.getName());
            }
        };

        EventHandler<ActionEvent> citizenWorkHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                    // TODO: INSERIRE FUNZIONE CHE GESTISCE L'OPERA PER I CITTADINI
                    System.out.println("Adesso Faccio un'opera per i cittadini del " + state.getName());
            }
        };

        EventHandler<ActionEvent> manualRecruitHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                    Pane playerMenu = (Pane)getElementByCssSelector("#playerMenu");
    
                    StackPane bottomMenu = (StackPane)getElementByCssSelector("#bottomMenu");
    
                    AnchorPane recruitMenu;
    
                    try { 
                        recruitMenu = (AnchorPane)App.createRoot("/com/assets/fxml/recruitMenu");
                    } catch (IOException e) { e.printStackTrace(); return; }
    
    
                    ObservableList<Node> armySelectors = ((AnchorPane)recruitMenu.lookup("#ArmySelectorContainer")).getChildren();
            
                    for (Node armySelector : armySelectors) {                    
    
                        Slider curSlider = ((Slider)armySelector.lookup("#soldierSlider"));
                       
                        curSlider.valueProperty().addListener(new ChangeListener<Number>() {
                            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                                    App.gameManager.refreshRecruitMenu();
                                }
                        });
                    }
    
    
    
                    bottomMenu.getChildren().add(recruitMenu);
    
                    GameManager.curSelectedState = state;
    
                    refreshRecruitMenu();
    
                    playerMenu.setVisible(false);
    
                    recruitMenu.setVisible(true);

                    disableButton("#sideMenuThirdButton");
            }
        };

        EventHandler<ActionEvent> fortifyHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                    // TODO: INSERIRE FUNZIONE CHE GESTISCE LA FORTIFICAZIONE DELLO STATO
                    System.out.println("Adesso Fortifico " + state.getName());
            }
        };

        EventHandler<Event> militaryConscriptionHandler = new EventHandler<Event>() {
            @Override
            public void handle(Event event){
                    // TODO: INSERIRE FUNZIONE CHE GESTISCE LA LEVA OBBLIGATORIA

                    ToggleSwitch toggleSwitch;
                    
                    try { toggleSwitch = ((ToggleSwitch)event.getSource()); }
                    catch(ClassCastException e) { toggleSwitch = ((ToggleSwitch)((Button)event.getSource()).getParent()); }
                    catch(Exception e) { e.printStackTrace(); return; }

                    if (toggleSwitch.isOn()) {
                        System.out.println("Adesso c'è la Leva Obbligatoria in " + state.getName());
                    }
                    else {
                        System.out.println("Adesso non c'è la Leva Obbligatoria in " + state.getName());
                    }
            }
        };

        

        setButton("#sideMenuFirstButton", "Supply", supplyStateHandler);
        setButton("#sideMenuSecondButton", "Citizen Work", citizenWorkHandler);
        setButton("#sideMenuThirdButton", "Recruit", manualRecruitHandler);
        setButton("#sideMenuFourthButton", "Fortify", fortifyHandler);
        setToggleSwitch("#sideMenuToggleSwitch", militaryConscriptionHandler);

    }

    private void hideButton(String buttonSelector) {
        ((Button)getElementByCssSelector(buttonSelector)).setVisible(false);
    }

    private void hideToggleSwitch(String switchSelector) {
        ((ToggleSwitch)getElementByCssSelector(switchSelector)).setVisible(false);
    }
    
    private void showAllySideMenu(State state) {
       
        EventHandler<ActionEvent> supplyStateHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                    // TODO: INSERIRE FUNZIONE CHE GESTISCE IL RIFORNIMENTO DEGLI ALLEATI
                    System.out.println("Adesso Rifornisco il mio caro amico " + state.getName());
            }
        };

        setButton("#sideMenuFirstButton", "Supply", supplyStateHandler);
        hideButton("#sideMenuSecondButton");
        hideButton("#sideMenuThirdButton");
        hideButton("#sideMenuFourthButton");
        hideToggleSwitch("#sideMenuToggleSwitch");
    }

    private void looseStateArmy(State state, double soldiersNumber, ARMY_TYPE type) {
        state.getArmy().looseSoldiers(soldiersNumber, type);
    }

    private void looseStateArmy(ArrayList<State> states, double soldiersNumber, ARMY_TYPE type) {
        for (State state : states) {
            looseStateArmy(state, soldiersNumber / states.size(), type);
        }
    }

    private int calcAttackPrice(ArrayList<Pair<State, Boolean>> attackingStates, State defenderState) {
        for (Pair<State, Boolean> s : attackingStates) {
            if (s.getKey().hasSeaBorder(defenderState)) return Price.SEA_ATTACK_PRICE_PER_DICE;
        }
        return Price.LAND_ATTACK_PRICE_PER_DICE;
    }

    private void payAttackCost(ArrayList<State> attackingStates, int cost) {
        double totalMoney = 0;
        for(State s : attackingStates) {
            totalMoney += s.getMoney();
        }
        for (State s : attackingStates) {
            s.subMoney(cost * s.getMoney() / totalMoney);
        }
    }

    private Pair<Integer, Integer> attackByArmyType(ARMY_TYPE type, Army attackingArmy, Army defendingArmy) {
        int attackerWon = 0;
        int defenderWon = 0;
        for(int i = 0; i < attackingArmy.getTroupsByType(type) / Army.SOLDIERS_PER_DICE; i++) {
            if(attackingArmy.attack(type) > defendingArmy.defend(defendingArmy.getBestArmyType())) attackerWon++; else defenderWon++;
        }
        return new Pair<Integer,Integer>(attackerWon, defenderWon);
    }

    private void refreshArmiesAfterBattle(ArrayList<State> attackingStates, State defenderState, int attackerWon, int defenderWon, ARMY_TYPE type) {
        for (int i = 0; i < attackerWon; i++) looseStateArmy(attackingStates, Army.SOLDIERS_PER_DICE, type);
        for (int i = 0; i < defenderWon; i++) looseStateArmy(defenderState, Army.SOLDIERS_PER_DICE, type);
    }

    private boolean attackState(Army attackingArmy, State defenderState, ArrayList<State> attackingStates, int attackCost) {

        Army defendingArmy = defenderState.getArmy();

        int totalPoints = 0;
        Pair<Integer, Integer> curPoints;

        // every Attacking State splits proportionally the cost
        payAttackCost(attackingStates, attackCost);

        curPoints = attackByArmyType(ARMY_TYPE.INFANTRY, attackingArmy, defendingArmy);
        
        refreshArmiesAfterBattle(attackingStates, defenderState, curPoints.getKey(), curPoints.getValue(), ARMY_TYPE.INFANTRY);

        totalPoints += curPoints.getKey();

        curPoints = attackByArmyType(ARMY_TYPE.ARTILLERY, attackingArmy, defendingArmy);
        
        refreshArmiesAfterBattle(attackingStates, defenderState, curPoints.getKey(), curPoints.getValue(), ARMY_TYPE.ARTILLERY);


        totalPoints += curPoints.getKey();

        curPoints = attackByArmyType(ARMY_TYPE.TANK, attackingArmy, defendingArmy);
        
        refreshArmiesAfterBattle(attackingStates, defenderState, curPoints.getKey(), curPoints.getValue(), ARMY_TYPE.TANK);

        totalPoints += curPoints.getKey();

        if (attackingStates.contains(App.gameManager.getState("ATL"))) {
        
            curPoints = attackByArmyType(ARMY_TYPE.CHTULHU, attackingArmy, defendingArmy);
        
            refreshArmiesAfterBattle(attackingStates, defenderState, curPoints.getKey(), curPoints.getValue(), ARMY_TYPE.APACHE);

            totalPoints += curPoints.getKey();

            if (attackingStates.size() > 1) {
                
                curPoints = attackByArmyType(ARMY_TYPE.CHTULHU, attackingArmy, defendingArmy);
        
                refreshArmiesAfterBattle(attackingStates, defenderState, curPoints.getKey(), curPoints.getValue(), ARMY_TYPE.APACHE);
        
                totalPoints += curPoints.getKey(); 
            }
        } else {

            curPoints = attackByArmyType(ARMY_TYPE.APACHE, attackingArmy, defendingArmy);
        
            refreshArmiesAfterBattle(attackingStates, defenderState, curPoints.getKey(), curPoints.getValue(), ARMY_TYPE.APACHE);
    
            totalPoints += curPoints.getKey(); 
        }

        if (totalPoints > 0) return true;
        return false;
    }

    @FXML
    void cancelAttack(ActionEvent event) {
        
        System.out.println("Attack Canceled");

        attackMenu.setVisible(false);

        ((StackPane)attackMenu.getParent()).getChildren().remove(attackMenu);
        
        attackMenu = null;

        getElementByCssSelector("#playerMenu").setVisible(true);

        enableButton("#sideMenuFirstButton");
        
    }

    @FXML
    void cancelRecruit(ActionEvent event) {
        
        System.out.println("Recruit Canceled");

        recruitMenu.setVisible(false);

        ((StackPane)recruitMenu.getParent()).getChildren().remove(recruitMenu);
        
        recruitMenu = null;

        getElementByCssSelector("#playerMenu").setVisible(true);

        enableButton("#sideMenuThirdButton");
    }


    public ArrayList<State> getArrayListFromArrayListPair(ArrayList<Pair<State, Boolean>> input) {
        ArrayList<State> states = new ArrayList<>();

        for (Pair<State, Boolean> s : input) {
            states.add(s.getKey());
        }

        return states;
    }


    @FXML
    void recruit(ActionEvent event) {
        // TODO: Scrivere la funzione che effettua il reclutamento
    }

    @FXML
    void attack(ActionEvent event) {

        Army attackerArmy = calcArmyFromSliders();

        if (!attackerArmy.isEnoughBig()) return;

        curSelectedState.incrementAttacksDone();

        refreshSideMenu(curSelectedState);

        System.out.println(getHumanPlayer().getName() + " Attacks " + curSelectedState.getName());
        
        ArrayList<Pair<State, Boolean>> attackingStates = getHumanPlayer().getNeighboringStates(GameManager.curSelectedState);

        int attackCost = calcAttackPrice(attackingStates, curSelectedState);

        boolean outcome = attackState(attackerArmy, GameManager.curSelectedState, getArrayListFromArrayListPair(attackingStates), attackCost);

        if (outcome) {
            System.out.println("Attacker Won");

            try { getHumanPlayer().occupyState(curSelectedState); } catch(Exception e) { e.printStackTrace(); }
            try {
                 addStringToListView("#playerStateConqueredTerritoriesListView", curSelectedState.getName()); 
                System.out.println("colorato");
                } catch(Exception e) { }

            // Si aggiornano le truppe perse
        }
        else {
            System.out.println("Defender Won");

        }

        addStringToListView("#playerBattlesListView", "Battle of " + curSelectedState.getRandomCityName() + ": " + (outcome ? "Won" : "Lost"));

        ((AnchorPane)getElementByCssSelector("#attackMenu")).setVisible(false);

        ((StackPane)attackMenu.getParent()).getChildren().remove(attackMenu);
        
        attackMenu = null;
    
        ((Pane)getElementByCssSelector("#playerMenu")).setVisible(true);

        enableButton("#sideMenuFirstButton");

        refreshTooltips();

    }

    private Army calcArmyFromSliders() {
        
        double values[] = {0, 0, 0, 0};

        int i = 0;
        for (Node elem : ((AnchorPane)attackMenu.lookup("#ArmySelectorContainer")).getChildren()) {
            values[i] = ((Slider)((AnchorPane)elem).getChildren().get(1)).getValue();
            i++;
        }
        

        int highestAttackModifier = 0;

        for (Pair<State, Boolean> s : getHumanPlayer().getNeighboringStates(curSelectedState)) {

            int curAttackModifier = s.getKey().getArmy().getAttackModifier();

            if (curAttackModifier > highestAttackModifier) highestAttackModifier = curAttackModifier;

        }

        return new Army(values[0], values[1], values[2], values[3], highestAttackModifier);


    }

    private void disableButton(String selector) {
        ((Button)getElementByCssSelector(selector)).setMouseTransparent(true);
    }

    private void enableButton(String selector) {
        ((Button)getElementByCssSelector(selector)).setMouseTransparent(false);
    }

    private void showEnemySideMenu(State state) {

        EventHandler<ActionEvent> negotiateHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                // TODO: INSERIRE FUNZIONE CHE GESTISCE LA NEGOZIAZIONE CON GLI ALTRI STATI
                System.out.println("Adesso Negozio con " + state.getName());
            }
        };

        if (getHumanPlayer().hasNeighboringState(state) && state.getlastTurnAttacksDone() <= getHumanPlayer().getLevel()) {  // The Player can attack the Selected State


            EventHandler<ActionEvent> attackHandler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
    
                    Pane playerMenu = (Pane)getElementByCssSelector("#playerMenu");
    
                    StackPane bottomMenu = (StackPane)getElementByCssSelector("#bottomMenu");
    
                    AnchorPane attackMenu;
    
                    try { 
                        attackMenu = (AnchorPane)App.createRoot("/com/assets/fxml/attackMenu");
                    } catch (IOException e) { e.printStackTrace(); return; }
    
    
                    ObservableList<Node> armySelectors = ((AnchorPane)attackMenu.lookup("#ArmySelectorContainer")).getChildren();
    
            
                    for (Node armySelector : armySelectors) {                    
    
                        Slider curSlider = ((Slider)armySelector.lookup("#soldierSlider"));
                       
                        curSlider.valueProperty().addListener(new ChangeListener<Number>() {
                            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                                    App.gameManager.refreshAttackMenu();
                                }
                        });
                    }
    
    
    
                    bottomMenu.getChildren().add(attackMenu);
    
                    GameManager.curSelectedState = state;
    
                    refreshAttackMenu();
    
                    playerMenu.setVisible(false);
    
                    attackMenu.setVisible(true);

                    disableButton("#sideMenuFirstButton");
    
    
                }
            };
    

            setButton("#sideMenuFirstButton", "Attack", attackHandler);
            setButton("#sideMenuSecondButton", "Negotiate", negotiateHandler);
        
        }
        else {  // The Player isn't Neighboring the selected State

            setButton("#sideMenuFirstButton", "Negotiate", negotiateHandler);
            hideButton("#sideMenuSecondButton");

        }

        hideButton("#sideMenuThirdButton");
        hideButton("#sideMenuFourthButton");
        hideToggleSwitch("#sideMenuToggleSwitch");

    
    }

    @FXML
    void refreshVoidRecruitMenu(MouseEvent event) {
        App.gameManager.refreshRecruitMenu();
    }

    @FXML
    void refreshVoidAttackMenu(MouseEvent event) {
        App.gameManager.refreshAttackMenu();
    }

    private void refreshAttackMenu() {

        ObservableList<Node> armySelectors = ((AnchorPane)App.gameManager.scene.lookup("#ArmySelectorContainer")).getChildren();

        ArrayList<Pair<State, Boolean>> neighboringStates = getHumanPlayer().getNeighboringStates(curSelectedState);

        Army totalArmy = new Army(0);

        for (Pair<State, Boolean> s : neighboringStates) {

            Army curStateArmy = s.getKey().getArmy();

            totalArmy.addSoldiers(curStateArmy.getInfantry(), curStateArmy.getArtillery(), curStateArmy.getTanks(), curStateArmy.getApaches());
        }


        int i = 0; 
        for (Node armySelector : armySelectors) {

            double maxSoldiers = totalArmy.toArray()[i];
            
            Slider curSlider = ((Slider)armySelector.lookup("#soldierSlider"));

            curSlider.setMax(((int)maxSoldiers / Army.SOLDIERS_PER_DICE) * Army.SOLDIERS_PER_DICE);
            curSlider.setMajorTickUnit(Army.SOLDIERS_PER_DICE);
            curSlider.setMinorTickCount(0);
            curSlider.setShowTickMarks(false);
            curSlider.setSnapToTicks(true);

            ((Label)armySelector.lookup("#selectedSoldiersLabel")).setText(formatHighNumber(((Slider)armySelector.lookup("#soldierSlider")).getValue()));
            ((Label)armySelector.lookup("#maxSoldiersLabel")).setText(formatHighNumber(maxSoldiers));
            
            i++;
        }

        refreshAttackMenuDices();
    }

    private void refreshDiceContainer(String diceContainerSelector) {
        
        AnchorPane diceContainer = ((AnchorPane)getElementByCssSelector(diceContainerSelector));

        String diceContainerHexColor = "#4d6555";

        if (diceContainerSelector.startsWith("#attacker")) diceContainerHexColor = players.get(selectedPlayerIndex).getHexColor();
        else if (diceContainerSelector.startsWith("#defender")) try { diceContainerHexColor = getOwner(curSelectedState).getHexColor(); } catch (Exception e) { }

        diceContainer.setStyle("-fx-background-color: " + diceContainerHexColor + ";");

        String dirNames[] = { "d6", "d8", "d10", "d12", "d20" };

        // TODO: Ovviamente l'URL che ho messo non è valido, ovviamente non so cosa metterci, ovviamente odio JavaFX

        int i = 0;
        for (Node curImageView : diceContainer.getChildren()) {
            
            ((ImageView)curImageView).setImage(new Image("/../../../../resources/com/icons/dices/" + dirNames[i] + "/" +  dirNames[i] + "_0.png"));
            i++;
        }
        
    }

    private void refreshAttackMenuDices() {
        
        ObservableList<Node> diceImageView = ((AnchorPane)getElementByCssSelector("#DiceIconContainer")).getChildren();

        refreshDiceContainer("#attackerDiceContainer");

        refreshDiceContainer("#defenderDiceContainer");

    }



    private Player getOwner(State state) {
        for (Player p : App.gameManager.players) if (p.hasOccupied(state)) return p;
        return null;
    }

    private void refreshSideMenu(State selectedState) {

        setLabelContent("#menuStateNameLabel", selectedState.getName());

        Player selectedStateOwner = getOwner(selectedState);

        setLabelContent("#menuStateLvlLabel", String.valueOf(String.valueOf((selectedStateOwner == null) ? 1 : selectedStateOwner.getLevel())));
        
        setTrapezoidXScale("#sideMenu", selectedState.getName().length() * 0.12);    // 0.12 è un numero magico che ho calcolato

        setLabelContent("#sideMenuStateMoneyLabel", String.valueOf(formatHighNumber(selectedState.getMoney())));
        setLabelContent("#sideMenuStateArmyLabel", String.valueOf(formatHighNumber(selectedState.getTotalArmy())));

        if (getHumanPlayer().hasOccupied(selectedState)) {
            showOccupiedStateSideMenu(selectedState);
        }
        else if (getHumanPlayer().isAllied(selectedState)) {
            showAllySideMenu(selectedState);
        }
        else {
            showEnemySideMenu(selectedState);
            
        }
        
        
    }


    private void refreshPlayerMenu() {
        refreshPlayerMenuByState(this.getHumanPlayer().getTotalState().getId());
    }



    public void refreshPlayerMenuByState(String stateId) {

        if (stateId.equals("ATL") && !getHumanPlayer().hasOccupied(App.gameManager.getState("ATL"))) return;

        State state = this.states.get(stateId);

        setLabelContent("#playerStateNameLabel", state.getName());
        setLabelContent("#playerStateLvlLabel", String.valueOf(getHumanPlayer().getLevel()));
        setTrapezoidXScale("#playerMenu", state.getName().length() * 0.12);
        setLabelContent("#playerStateMoneyLabel", String.valueOf(formatHighNumber(state.getMoney())));
        setLabelContent("#playerStateArmyLabel", String.valueOf(formatHighNumber(state.getTotalArmy())));
        setLabelContent("#playerStateNaturalResourcesLabel" , String.valueOf(formatHighNumber(state.getNaturalResources())));
        setLabelContent("#playerStateRefinedResourcesLabel", String.valueOf(formatHighNumber(state.getRefinedResources())));
        setLabelContent("#playerStateWorkForceLabel", String.valueOf(formatHighNumber(state.getWorkForce())));

        showPlayerMenu();
        handleHover(state.getPath());

    }


    private void showSideMenu() {
        getElementByCssSelector("#sideMenu").setVisible(true);
        ((Pane)getElementByCssSelector("#sideMenu")).getChildren().forEach(node -> { node.setVisible(true); });
    }

    private void showPlayerMenu() {
        getElementByCssSelector("#playerMenu").setVisible(true);
        ((Pane)getElementByCssSelector("#playerMenu")).getChildren().forEach(node -> { node.setVisible(true); });
    }

    public void handleHoverEnd(SVGPath curPath) {

        if (App.gameManager.getState("ATL").getPath().equals(curPath) && !getHumanPlayer().hasOccupied(App.gameManager.getState("ATL"))) return;

        curPath.setFill(((Color)(curPath.getFill())).brighter());
    }

    public State getState(String Id) {
        return this.states.get(Id);
    }

    public void handleHover(SVGPath curPath) {

        if (App.gameManager.getState("ATL").getPath().equals(curPath) && !getHumanPlayer().hasOccupied(App.gameManager.getState("ATL"))) return;

        curPath.setFill(((Color)(curPath.getFill())).darker());
    }

    private void changeHoverHandler() {
        Pane mapContainer = (Pane)getElementByCssSelector("#mapContainer");
        mapContainer.getChildren().forEach(svgPath -> svgPath.setOnMouseEntered(e -> { try { handleHover((SVGPath)svgPath); } catch(ClassCastException exception) { } }));
    
    }

    private void setPlayerOriginalState(State clickedState) {

        for (Player p : this.players) if (p.hasOccupied(clickedState)) return;
        
        this.getHumanPlayer().setOriginalState(clickedState);
            
        addStringToListView("#playerStateConqueredTerritoriesListView", clickedState.getName());

        refreshPlayerMenuByState(clickedState.getId());
        refreshSideMenu(clickedState);
        showPlayerMenu();
        refreshTooltips();
        showSideMenu();
        changeHoverHandler();

        setBotsOriginalState();

    }


    private boolean stateIsValid(State state) {
        if(state == null) return false;
        if(state.getId().equalsIgnoreCase("ATL")) return false;

        for(Player p : this.getPlayers()) {
            if (p.getOriginalState() == null) continue;
            if(state.getId() == p.getOriginalState().getId()) {
                return false;
            }
        }
        return true;
    }

    private void setBotsOriginalState() {
        
        for(int i = 1; i < this.getPlayers().size(); i++) {
            State state = null;

            do { state = this.getRandomState(); } while (!stateIsValid(state));
            
            this.getPlayers().get(i).setOriginalState(state); 
        }
    }

    public void manageStateClicked(String id) {
                
        State curState = this.states.get(id);

        if (!this.getHumanPlayer().hasOriginalState()) {
            
            setPlayerOriginalState(curState);
            
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

    public State getRandomState() { 
        return this.states.get(states.keySet().toArray()[RandomGenerator.getDefault().nextInt(states.size())]);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    private void hideSideMenu() {
        ((AnchorPane)getElementByCssSelector("#sideMenu")).getChildren().forEach(node -> { node.setVisible(false); });
    }

    private void hidePlayerMenu() {
        ((Pane)getElementByCssSelector("#playerMenu")).getChildren().forEach(node -> { node.setVisible(false); });
    }


    public void refreshLevelLabel() {
        ((Label)getElementByCssSelector("#playerStateLvlLabel")).setText(String.valueOf(getHumanPlayer().getLevel()));
    }

    public ArrayList<Player> getActivePlayers() {
        
        ArrayList<Player> activePlayers = new ArrayList<>();

        for (Player p : this.getPlayers()) if (p.isActive()) activePlayers.add(p);

        return activePlayers;

    }

    private void manageHumanTurn() {
        showSideMenu();
    }

    private void playTurn() {

        System.out.println(players.get(selectedPlayerIndex).getName() + " is Playing");

        if (this.selectedPlayerIndex == 0) { 
            App.gameManager.calendar.update();
            manageHumanTurn();
            return;
            
        }

        hideSideMenu();

        GameManager.botThread.interrupt();

        GameManager.botThread = new Thread((Bot)players.get(selectedPlayerIndex));

        botThread.start();
        
        return;

    }

    public void passTurn() {
        
        this.selectedPlayerIndex = (this.selectedPlayerIndex + 1) % this.getActivePlayers().size();

        this.playTurn();

        // TODO: bisogna spostare l'aumento di livello qui, però non ora perché è utile per il debug
        
    }

    public void refreshTooltips() {

        Tooltip.install(getElementByCssSelector("#playerStateMoneyImageView"), new Tooltip("Stage Money: " + formatHighNumber(getHumanPlayer().getTotalState().getStageMoney())));
        Tooltip.install(getElementByCssSelector("#playerStateArmyImageView"), new Tooltip("Stage Army: "  + formatHighNumber(getHumanPlayer().getTotalState().getStageArmy())));
        Tooltip.install(getElementByCssSelector("#playerStateNaturalResourcesImageView"), new Tooltip("Stage Natural Resources: " + formatHighNumber(getHumanPlayer().getTotalState().getStageNaturalResources())));
        Tooltip.install(getElementByCssSelector("#playerStateRefinedResourcesImageView"), new Tooltip("Stage Refined Resources: " + formatHighNumber(getHumanPlayer().getTotalState().getStageRefinedResources())));

        Tooltip.install(getElementByCssSelector("#sideMenuStateMoneyLabel"), new Tooltip("Stage Money: " + formatHighNumber(getHumanPlayer().getTotalState().getStageMoney())));
        Tooltip.install(getElementByCssSelector("#sideMenuStateArmyLabel"), new Tooltip("Stage Army: " + formatHighNumber(getHumanPlayer().getTotalState().getStageArmy())));

        ((ToggleSwitch)getElementByCssSelector("#sideMenuToggleSwitch")).attachTooltip(new Tooltip("Military Conscription"));

    }


}
