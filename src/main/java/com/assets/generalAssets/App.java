package com.assets.generalAssets;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

import com.assets.gameAssets.GameManager;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static GameManager gameManager;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(createRoot("/com/assets/fxml/startPage"), 1280, 720);

        scene.getStylesheets().add(this.getClass().getResource("/com/assets/fxml/startPageStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("WarGame");
        stage.show();
        
    }

    public void stop() {
        
        try { GameManager.botThread.interrupt(); } catch (Exception e) { e.printStackTrace(); }
                
        Platform.exit();

    }

    public static Parent createRoot(String fxml) throws IOException {
        
        Parent root = loadFXML(fxml);
        return root;

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));      
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
