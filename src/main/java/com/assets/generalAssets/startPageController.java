package com.assets.generalAssets;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.random.RandomGenerator;
import java.util.Random;

import com.assets.SVGAssets.SVGPathElement;
import com.assets.SVGAssets.SVGPathLoader;
import com.assets.gameAssets.Bot;
import com.assets.gameAssets.GameManager;
import com.assets.gameAssets.Human;
import com.assets.gameAssets.Player;
import com.assets.gameAssets.State;
import com.assets.generalAssets.graphics.ColorPickerManager;

public class StartPageController implements Initializable {

    @FXML
    private Button quitGameButton;

    @FXML
    private Button startGameButton;

    @FXML
    private AnchorPane startGameColorPickerContainer;

    @FXML
    private Label startGameTitle;

    @FXML
    private Slider botSlider;
    
    private static GameManager gameManager;

    private ColorPickerManager colorPickerManager;

    @FXML
    void quitGame(ActionEvent event) {
        Platform.exit();
    }

    private void initColorPicker() {
        AnchorPane colorPicker;
                       
        try { 
            colorPicker = (AnchorPane)App.createRoot("/com/assets/fxml/colorPicker");
        } catch (IOException e) { e.printStackTrace(); return; }
        
        startGameColorPickerContainer.getChildren().add(colorPicker);

        colorPickerManager = ColorPickerController.getColorPickerManager();

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        initColorPicker();
        
    }

    @FXML
    void switchToMainPage(ActionEvent event) {
        
        Scene scene;
        
        Stage stage;
               
        try { 
            Parent root = App.createRoot("/com/assets/fxml/mainPage");
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
         } catch (IOException e) { e.printStackTrace(); return; }
         
        initMainPage(scene);

        scene.getStylesheets().add(this.getClass().getResource("/com/assets/fxml/mainPageStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        

    
    }

    private void initMainPage(Scene scene) {

        gameManager = new GameManager("src/main/resources/com/statesData.xml", scene);

        SVGPathLoader svgPathLoader = new SVGPathLoader("src\\main\\resources\\com\\worldLow.svg");
        ArrayList<SVGPathElement> paths = svgPathLoader.loadPaths();
        Pane mapContainer = (Pane)(scene.lookup("#mapContainer"));
        double xShift = 50, yShift = -110;


        for(SVGPathElement p : paths) {
            try {
                SVGPath curPath = (SVGPath)(scene.lookup("#" + p.getId()));
                gameManager.addState(p.getId(), curPath);
                curPath.setContent(p.getContent());
                curPath.setLayoutX(xShift);
                curPath.setLayoutY(yShift);
                curPath.getStyleClass().add("State");
                curPath.setFill(Paint.valueOf("#4D6555"));
                curPath.setOnMouseClicked(e -> gameManager.manageStateClicked(curPath.getId()));
                curPath.setOnMouseEntered(e -> gameManager.refreshPlayerMenuByState(curPath.getId()));
                curPath.setOnMouseExited(e -> gameManager.handleHoverEnd(curPath));
                curPath.setClip(new Rectangle(mapContainer.getLayoutX() - xShift, mapContainer.getLayoutY() - yShift, mapContainer.getPrefWidth(), mapContainer.getPrefHeight()));
            } catch(Exception e) {
                System.out.println("Error on " + p.getId() + "\n");
                e.printStackTrace();
            }
        }

        
        // Add the Human Player to the gameManager
        try { gameManager.addPlayer(new Human("Human Player", colorPickerManager.getCurHexColor())); } catch(Exception e) { e.printStackTrace(); }

        RandomGenerator rnd = RandomGenerator.getDefault();

        for (int i = 1; i <= botSlider.getValue(); i++) {
            
            State state = null;
            
            do { state = gameManager.getRandomState(); } while (!stateIsValid(state));


            Color nextColor;
            
            do { nextColor = Color.hsb(rnd.nextInt(361), 0.5, 1); } while (!colorIsValid(nextColor));

            try { gameManager.addPlayer(new Bot(state, "Bot Player " + i, ColorPickerManager.getHexColor(nextColor))); } catch(Exception e) { e.printStackTrace(); }
        }


    }

    private boolean colorIsValid(Color color) {
        if (color == null) return false;

        for(Player p : gameManager.getPlayers()) {
            if (p.getHexColor().isEmpty()) continue;

            Color playerColor = Color.valueOf(p.getHexColor());

            if (Math.abs(playerColor.getHue() - color.getHue()) <= 10) return false;
        }

        return true;
    }

    private boolean stateIsValid(State state) {
        if(state == null) return false;
        if(state.getId() == "ATL") return false;

        for(Player p : gameManager.getPlayers()) {
            if (p.getOriginalState() == null) continue;
            if(state.getId() == p.getOriginalState().getId()) {
                return false;
            }
        }
        return true;
    }

}
