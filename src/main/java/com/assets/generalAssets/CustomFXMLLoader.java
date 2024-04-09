package com.assets.generalAssets;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class CustomFXMLLoader extends FXMLLoader {
    
    public CustomFXMLLoader(URL fxmlUrl) {
        super(fxmlUrl);
    }
    

    // VADO A MANGIARE IL SUSHI FANCULO
    @Override
    public void load() {
        super.load();
    
        // Recupera il mapContainer dalla scena
        Pane mapContainer = (Pane) getScene().lookup("#mapContainer");
    
        // Calcola l'area di ritaglio
        Rectangle clipRect = new Rectangle(0, 0, mapContainer.getWidth(), mapContainer.getHeight());
    
        // Crea una regione con l'area di ritaglio
        Region clipRegion = new Region();
        clipRegion.setClip(clipRect);
    
        // Imposta la clip sul mapContainer
        mapContainer.setClip(clipRegion);
    }
}
