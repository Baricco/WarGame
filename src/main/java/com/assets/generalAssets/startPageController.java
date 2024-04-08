package com.assets.generalAssets;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;




public class startPageController implements Initializable{
    
    @FXML
    private SVGPath AE;

    
    

    @FXML
    private Pane mapContainer;    
 


    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Hai premuto il pulsantozzo");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

}
