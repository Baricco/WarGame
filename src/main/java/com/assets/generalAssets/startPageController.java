package com.assets.generalAssets;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.assets.SVGAssets.SVGPathElement;
import com.assets.SVGAssets.SVGPathLoader;
import com.assets.gameAssets.GameManager;
import com.assets.gameAssets.Human;
import com.assets.generalAssets.graphics.ColorPickerManager;
import com.assets.generalAssets.graphics.ToggleSwitch;

public class StartPageController implements Initializable {

    @FXML
    private Button quitGameButton;

    @FXML
    private Button startGameButton;

    @FXML
    private AnchorPane startGameColorPickerContainer;

    @FXML
    private Label startGameTitle;
    
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

       /*
        startGameColorPicker = new CustomColorPicker();
        
        startGameColorPicker.setId("startGameColorPicker");
        
        startGameColorPickerContainer.getChildren().add(startGameColorPicker);

        startGameColorPicker.setVisible(true);
        */
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
        

        // Add the Human Player to the gameManager
        try { gameManager.addPlayer(new Human("Human Player", colorPickerManager.getCurHexColor())); } catch(Exception e) { e.printStackTrace(); }

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
