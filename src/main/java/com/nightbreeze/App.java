package com.nightbreeze;

import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CharacterData.createDataFolder();
        GUIManager.initializePrimaryStage("home-page");
    }

    public static void main(String[] args) {
        launch();
    }
}
