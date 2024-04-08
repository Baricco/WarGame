package com.assets.generalAssets;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.assets.SVGAssets.SVGPathElement;
import com.assets.SVGAssets.SVGPathLoader;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(createRoot("/com/assets/fxml/startPage"), 1280, 720);
        scene.getStylesheets().add(this.getClass().getResource("/com/assets/fxml/startPageStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("WarGame");
        stage.show();
        SVGPathLoader svgPathLoader = new SVGPathLoader();
        ArrayList<SVGPathElement> paths = svgPathLoader.loadPaths();

        for(SVGPathElement p : paths) {
            try {
                SVGPath curPath = (SVGPath)(scene.lookup("#" + p.getId()));
                curPath.setContent(p.getContent());
                curPath.setLayoutX(0);
                curPath.setLayoutY(0);
            } catch(Exception e) {System.out.println(p.getId());};

            
        }


/*
        for(int i = 0; i<paths.size(); i++){
            try {
            ((SVGPath)scene.lookup(paths.get(i).getId())).setContent(paths.get(i).getContent());
            } catch(Exception e) {}
        }

        */
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
