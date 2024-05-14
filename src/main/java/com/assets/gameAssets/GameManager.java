package com.assets.gameAssets;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.random.RandomGenerator;
import com.assets.gameAssets.basics.Calendar;
import com.assets.generalAssets.graphics.ToggleSwitch;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    }

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
            public void handle(ActionEvent event){
                    // TODO: INSERIRE FUNZIONE CHE GESTISCE LA RECLUTA DEI CITTADINI
                    System.out.println("Adesso Recluto i cittadini del " + state.getName());
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

    private void showEnemySideMenu(State state) {
       
        EventHandler<ActionEvent> attackHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                    // TODO: INSERIRE FUNZIONE CHE GESTISCE L'ATTACCO
                    System.out.println("Adesso Attacco " + state.getName());
            }
        };

        EventHandler<ActionEvent> negotiateHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                    // TODO: INSERIRE FUNZIONE CHE GESTISCE LA NEGOZIAZIONE CON GLI ALTRI STATI
                    System.out.println("Adesso Negozio con " + state.getName());
            }
        };

        setButton("#sideMenuFirstButton", "Attack", attackHandler);
        setButton("#sideMenuSecondButton", "Negotiate", negotiateHandler);
        hideButton("#sideMenuThirdButton");
        hideButton("#sideMenuFourthButton");
        hideToggleSwitch("#sideMenuToggleSwitch");
    
    }

    private void refreshSideMenu(State selectedState) {

        setLabelContent("#menuStateNameLabel", selectedState.getName());
        setLabelContent("#menuStateLvlLabel", String.valueOf(String.valueOf(selectedState.getLevel())));
        
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
            // TODO: AGGIUGERE EVENTUALMENTE IL CONTROLLO PER VEDERE SE LO STATO E' DI UN BOT (NEMICO) O SE GIOCA PER SE' (NEUTRALE)
            showEnemySideMenu(selectedState);
            
        }
        
        
    }


    private void refreshPlayerMenu() {
        refreshPlayerMenuByState(this.getHumanPlayer().getTotalState().getId());
    }



    public void refreshPlayerMenuByState(String stateId) {

        State state = this.states.get(stateId);

        setLabelContent("#playerStateNameLabel", state.getName());
        setLabelContent("#playerStateLvlLabel", String.valueOf(state.getLevel()));
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
        ((Pane)getElementByCssSelector("#sideMenu")).getChildren().forEach(node -> { node.setVisible(true); });
    }

    private void showPlayerMenu() {
        ((Pane)getElementByCssSelector("#playerMenu")).getChildren().forEach(node -> { node.setVisible(true); });
    }

    public void handleHoverEnd(SVGPath curPath) {
        curPath.setFill(((Color)(curPath.getFill())).brighter());
    }

    public void handleHover(SVGPath curPath) {
        curPath.setFill(((Color)(curPath.getFill())).darker());
    }

    private void changeHoverHandler() {
        Pane mapContainer = (Pane)getElementByCssSelector("#mapContainer");
        mapContainer.getChildren().forEach(svgPath -> svgPath.setOnMouseEntered(e -> { try { handleHover((SVGPath)svgPath); } catch(ClassCastException exception) { } }));
    
    }

    private void setPlayerOriginalState(State clickedState) {

        for (Player p : this.players) if (p.hasOccupied(clickedState)) return;
        
        this.getHumanPlayer().setOriginalState(clickedState);
            
        addStringToListView("#playerStateAlliedStatesListView", clickedState.getName());

        refreshPlayerMenuByState(clickedState.getId());
        refreshSideMenu(clickedState);
        showPlayerMenu();
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

}
