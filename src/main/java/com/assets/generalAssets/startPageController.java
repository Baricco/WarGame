package com.assets.generalAssets;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;




public class startPageController implements Initializable{

    @FXML
    private SVGPath AE;

    @FXML
    private SVGPath AF;

    @FXML
    private SVGPath JG;

    @FXML
    private SVGPath CG;

    @FXML
    private SVGPath AR;

    @FXML
    private SVGPath AT;

    @FXML
    private SVGPath ATL;

    @FXML
    private SVGPath AU;

    @FXML
    private SVGPath ID;

    @FXML
    private SVGPath FR;

    @FXML
    private SVGPath SE;

    @FXML
    private SVGPath MZ;

    @FXML
    private SVGPath BN;

    @FXML
    private SVGPath CI;

    @FXML
    private SVGPath BR;

    @FXML
    private SVGPath AC;

    @FXML
    private SVGPath SU;
    
    @FXML
    private SVGPath CA;

    @FXML
    private SVGPath NI;

    @FXML
    private SVGPath EG;

    @FXML
    private SVGPath IT;

    @FXML
    private SVGPath CN;

    @FXML
    private SVGPath CO;

    @FXML
    private SVGPath GR;

    @FXML
    private SVGPath GE;

    @FXML
    private SVGPath MO;

    @FXML
    private SVGPath ET;

    @FXML
    private SVGPath SP;

    @FXML
    private SVGPath SC;

    @FXML
    private SVGPath IN;

    @FXML
    private SVGPath GB;

    @FXML
    private SVGPath GL;

    @FXML
    private SVGPath GM;

    @FXML
    private SVGPath PT;

    @FXML
    private SVGPath JP;

    @FXML
    private SVGPath KZ;

    @FXML
    private SVGPath TH;

    @FXML
    private SVGPath KP;

    @FXML
    private SVGPath LY;

    @FXML
    private SVGPath RO;

    @FXML
    private SVGPath MX;

    @FXML
    private SVGPath NZ;

    @FXML
    private SVGPath PL;

    @FXML
    private SVGPath TL;

    @FXML
    private SVGPath SA;

    @FXML
    private SVGPath TR;

    @FXML
    private SVGPath TT;

    @FXML
    private SVGPath US;

    @FXML
    private SVGPath BZ;

    @FXML
    private SVGPath IR;
    
    @FXML
    private Pane mapContainer;    

    @FXML
    private Pane playerMenu;

    @FXML
    private Label playerStateNameLabel;

    @FXML
    private Label playerStateMoneyLabel;

    @FXML
    private Label playerStateArmyLabel;

    @FXML
    private Label playerStateNaturalResourcesLabel;

    @FXML
    private Label playerStateRefinedResourcesLabel;


    @FXML
    private ListView<String> playerStateConqueredTerritoriesListView;

    @FXML
    private ListView<String> playerStateAlliedStatesListView;
 


    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Hai premuto il pulsantozzo");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {       

    }

}
