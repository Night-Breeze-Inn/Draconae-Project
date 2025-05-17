package com.nightbreeze.controller;

import static com.nightbreeze.util.GUIManager.projectName;

import com.nightbreeze.model.Character;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomePageController implements Initializable {

    @FXML
    private Button characterButton;

    @FXML
    private Button deleteCharacterButton;

    @FXML
    private Label ProjectNameLabel;

    Character currentCharacter;

    @FXML
    public void characterButtonAction() throws IOException {
        if (currentCharacter == null) {
            GUIManager.loadStage("character-name");
        }
    }

    @FXML
    public void deleteCharacterAction(ActionEvent actionEvent) throws IOException {
        CharacterData.deleteCharacterData();
        Utils.showInfoAlert("Deleted Character", "Character deleted successfully");
        Parent root = GUIManager.loadFXML("home-page");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectNameLabel.setText(projectName);
        currentCharacter = CharacterData.loadCharacterData();

        if (currentCharacter == null) {
            characterButton.setText("Create a new Character");
            System.out.println("Characters' file empty.");
            deleteCharacterButton.setVisible(false);
        } else {
            characterButton.setText("Load Character: " + currentCharacter.getName());
            System.out.println("Loaded character: " + currentCharacter.getName());
            deleteCharacterButton.setVisible(true);
            deleteCharacterButton.setText("Delete Character: " + currentCharacter.getName());
        }
    }
}
