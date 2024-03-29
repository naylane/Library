package org.example.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.example.Main;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    //função para trocas de telas (AnchoPanes)
    public SceneSwitch(AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
