package com.assets.generalAssets;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;




public class MainPageController implements Initializable{

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
    private SVGPath SA;

    @FXML
    private SVGPath TR;

    @FXML
    private SVGPath US;

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
    private Label playerStateLvlLabel;

    @FXML
    private ListView<String> playerStateConqueredTerritoriesListView;

    @FXML
    private Pane sideMenu;

    @FXML
    private Label calendarLabel;

    @FXML
    private Label menuStateLvlLabel;

    @FXML
    private Label menuStateNameLabel;

    @FXML
    private ListView<String> playerStateAlliedStatesListView;

    @FXML
    private ListView<String> playerBattlesListView;

    @FXML
    private ImageView playerStateMoneyImageView;

    @FXML
    private ImageView playerStateArmyImageView;

    @FXML
    private ImageView playerStateNaturalResourcesImageView;

    @FXML
    private ImageView playerStateRefinedResourcesImageView;

    @FXML
    private ImageView playerStateWorkForceImageView;

    @FXML
    private Label sideMenuStateArmyLabel;

    @FXML
    private Label sideMenuStateMoneyLabel;

    private void attachTooltips() {
        Tooltip.install(playerStateMoneyImageView, new Tooltip("Stage Money: "));
        Tooltip.install(playerStateArmyImageView, new Tooltip("Stage Army: "));
        Tooltip.install(playerStateNaturalResourcesImageView, new Tooltip("Stage Natural Resources: "));
        Tooltip.install(playerStateRefinedResourcesImageView, new Tooltip("Stage Refined Resources: "));
        Tooltip.install(playerStateWorkForceImageView, new Tooltip("Stage Work Force: "));

        Tooltip.install(sideMenuStateMoneyLabel, new Tooltip("Stage Money: "));
        Tooltip.install(sideMenuStateArmyLabel, new Tooltip("Stage Army: "));
    }

    private void hidePlayerMenu() {
        playerMenu.getChildren().forEach(node -> { node.setVisible(false); });
    }

    private void showPlayerMenu() {
        playerMenu.getChildren().forEach(node -> { node.setVisible(true); });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        hidePlayerMenu();
        
        // qui in mezzo ci va il codice che permette al player di scegliere lo stato con cui vuole partire

        showPlayerMenu();

        attachTooltips();


    }

}
