package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.util.Utils.showErrorAlert;

import com.nightbreeze.model.Character;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class characterNameController implements Initializable {

    @FXML
    private TextField characterName;

    public static Character character = new Character();
    CharacterData characterData = new CharacterData();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void createCharacterButtonOnAction(ActionEvent actionEvent) throws IOException {
        if (characterName.getText().isEmpty()) {
            showErrorAlert("Error", "Please enter a character name");
            return;
        }

        character.setName(characterName.getText());
        characterData.saveCharacterData(character);

        Parent root = GUIManager.loadFXML("character-species");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(root);
    }
}
