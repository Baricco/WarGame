package com.assets.generalAssets.graphics;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ToggleSwitch extends HBox {
	
	private final Label label = new Label();
	private final Button button = new Button();
	
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
	


    public boolean isOn() {
        return this.switchedOn.get();
    } 

	private void init() {
		
		label.setText("OFF");
		getChildren().addAll(label, button);	
		button.setOnAction((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		label.setOnMouseClicked((e) -> {
			switchedOn.set(!switchedOn.get());

		});
		setStyle();
		bindProperties();
	}
	
	private void setStyle() {
		//Default Width
		setWidth(80);
		label.setAlignment(Pos.CENTER);
        setStyleClass("toggleSwitchOFF");
		//setStyle("-fx-background-color: grey; -fx-text-fill:black; -fx-background-radius: 4;");
		setAlignment(Pos.CENTER);
	}

    private void setStyleClass(String newStyleClass) {
        getStyleClass().clear();
        getStyleClass().add(newStyleClass);
    }
	
	private void bindProperties() {
		label.prefWidthProperty().bind(widthProperty().divide(2));
		label.prefHeightProperty().bind(heightProperty());
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
	}

    public void setAction(EventHandler<Event> actionHandler) {
        this.setOnMouseClicked(actionHandler);
        
        button.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Esegui la gestione dell'evento usando actionHandler
				switchedOn.set(!switchedOn.get());
                actionHandler.handle(event);
            }
        }));
    }
	
	public ToggleSwitch() {
		init();
		switchedOn.addListener((a,b,c) -> {
			if (c) {
                		label.setText("ON");
                		//setStyle("-fx-background-color: green;");
                        setStyleClass("toggleSwitchON");


                        label.toFront();
            		}
            		else {
            			label.setText("OFF");
        			    //setStyle("-fx-background-color: grey;");
                        setStyleClass("toggleSwitchOFF");
                        button.toFront();
            		}
		});

        // TODO: QUI BISOGNA FARE this.addListener O QUALCOSA DEL GENERE MA NON SO COME FARE DI PRECISO

	}
}