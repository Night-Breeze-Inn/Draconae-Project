package com.nightbreeze.util;

import atlantafx.base.theme.PrimerLight;
import com.nightbreeze.App;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIManager {

    public static String projectName = "Draconae Project";

    public static Parent loadFXML(String fxml) throws IOException {
        URL fxmlUrl = App.class.getResource(fxml + ".fxml");
        if (fxmlUrl == null) {
            throw new IOException("Cannot load FXML file: " + fxml + ".fxml");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        return fxmlLoader.load();
    }

    public static void loadStage(String fxml) throws IOException {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML(fxml));
        stage.setScene(scene);
        stage.setTitle(projectName);
        stage.show();
    }
}
