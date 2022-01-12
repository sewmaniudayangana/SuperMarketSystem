package controller;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SettingFormController {


    public Pane exit;
    public Pane mode;

    public void initialize() {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
}
