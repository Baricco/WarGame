package com.example;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        scene = new Scene(createRoot("mainPage"), 1280, 720);

        stage.setScene(scene);
        stage.setTitle("WarGame");
        stage.show();
    }

    static Parent createRoot(String fxml) throws IOException {
        
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