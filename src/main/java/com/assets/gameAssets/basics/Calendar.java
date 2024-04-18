package com.assets.gameAssets.basics;

import java.time.LocalDateTime;

import javafx.scene.control.Label;

public class Calendar {
    

    private LocalDateTime date;
    private Label calendarLabel;
    private final int TURN_PERIOD = 7;  // This variable contains the number days that passes for each turn in the game

    public Calendar(Label calendarLabel) {
        this.date = LocalDateTime.now();
        this.calendarLabel = calendarLabel;
        updateLabel();
    }

    // this function adds a zero before day and month if they are less than 10
    private String formatDate(int day, int month, int year) {
        return (day > 9 ? "" : "0") + day + "/" + (month > 9 ? "" : "0") + month + "/" + year;
    }

    private void updateLabel() {
        this.calendarLabel.setText(formatDate(this.date.getDayOfMonth(), this.date.getMonthValue(), this.date.getYear()));
    }

    public void update() {
        this.date = this.date.plusDays(this.TURN_PERIOD);
        this.updateLabel();
    }

}
