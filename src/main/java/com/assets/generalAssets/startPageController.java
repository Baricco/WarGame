package com.assets.generalAssets;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayList;

import com.assets.SVGAssets.SVGPathElement;
import com.assets.SVGAssets.SVGPathLoader;
import com.assets.gameAssets.GameManager;
import com.assets.gameAssets.Human;

public class StartPageController {

    @FXML
    private Button quitGameButton;

    @FXML
    private Button startGameButton;

    @FXML
    private ColorPicker startGameColorPicker;

    @FXML
    private Label startGameTitle;
    
    private static GameManager gameManager;


    @FXML
    void quitGame(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void setPlayerColor(ActionEvent event) {
        System.out.println("Prima di scrivere il codice qui dentro vorrei modificare il colorpicker perchè fa schifo");

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
        

        // Add the Human Player to the gameManager
        try { gameManager.addPlayer(new Human("Human Player", "#FF0000")); } catch(Exception e) { e.printStackTrace(); }

        // TODO: Add Bots to the gameManager
        


        for(SVGPathElement p : paths) {
            try {
                SVGPath curPath = (SVGPath)(scene.lookup("#" + p.getId()));
                gameManager.addState(p.getId(), curPath);
                curPath.setContent(p.getContent());
                curPath.setLayoutX(xShift);
                curPath.setLayoutY(yShift);
                curPath.getStyleClass().add("State");
                curPath.setOnMouseClicked(e -> gameManager.manageStateClicked(curPath.getId()));
                curPath.setOnMouseEntered(e -> gameManager.refreshPlayerMenuByState(curPath.getId()));
                curPath.setClip(new Rectangle(mapContainer.getLayoutX() - xShift, mapContainer.getLayoutY() - yShift, mapContainer.getPrefWidth(), mapContainer.getPrefHeight()));
            } catch(Exception e) {
                System.out.println("Error on " + p.getId() + "\n");
                e.printStackTrace();
            }
        }


    }

}
