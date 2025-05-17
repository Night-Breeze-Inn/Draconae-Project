package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;
import static com.nightbreeze.controller.characterCreation.characterSpeciesController.selectedSpecies;
import static com.nightbreeze.util.Utils.showErrorAlert;

import com.nightbreeze.model.Subrace;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

public class CharacterSubSpeciesController implements Initializable {

    @FXML
    private Label speciesNameLabel;

    @FXML
    private Label speciesDescriptionLabel;

    @FXML
    private Button subSpecies1Button;

    @FXML
    private Button subSpecies2Button;

    @FXML
    private Button subSpecies3Button;

    @FXML
    private Button subSpecies4Button;

    @FXML
    private Button subSpecies5Button;

    @FXML
    private Button subSpecies6Button;

    @FXML
    private Button subSpecies7Button;

    @FXML
    private Button subSpecies8Button;

    @FXML
    private Button subSpecies9Button;

    @FXML
    private Button subSpecies10Button;

    private final CharacterData characterData = new CharacterData();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Button> subSpeciesButtons = List.of(
            subSpecies1Button,
            subSpecies2Button,
            subSpecies3Button,
            subSpecies4Button,
            subSpecies5Button,
            subSpecies6Button,
            subSpecies7Button,
            subSpecies8Button,
            subSpecies9Button,
            subSpecies10Button
        );

        subSpeciesButtons.forEach(button -> {
            button.setVisible(false);
            button.setText("");
            button.setOnAction(null);
        });

        speciesNameLabel.setText(selectedSpecies.getName());
        speciesDescriptionLabel.setText("Choose a " + selectedSpecies.getName() + "SubSpecies");

        List<Subrace> subraces = selectedSpecies.getSubraces();

        if (subraces == null || subraces.isEmpty()) {
            speciesNameLabel.setText(selectedSpecies.getName() + "as no subspecies");
            subSpecies1Button.setText("Proceed to class selection");
            subSpecies1Button.setVisible(true);
            subSpecies1Button.setOnAction(this::proceedToClassCreation);
        } else {
            for (int i = 0; i < subraces.size() && i < subSpeciesButtons.size(); i++) {
                Subrace subrace = subraces.get(i);
                Button subSpeciesButton = subSpeciesButtons.get(i);

                subSpeciesButton.setText(subrace.getName());
                subSpeciesButton.setVisible(true);
                subSpeciesButton.setOnAction(event -> {
                    try {
                        subSpeciesSelection(subrace, event);
                    } catch (IOException e) {
                        System.err.println("Failed to navigate after sub-species selection.");
                        e.printStackTrace();
                        showErrorAlert("Navigation Error", "Could not load the next screen.");
                    }
                });
            }
        }
    }

    private void subSpeciesSelection(Subrace subrace, ActionEvent event) throws IOException {
        character.setSubRace(subrace.getName());

        characterData.saveCharacterData(character);

        proceedToClassCreation(event);
    }

    private void proceedToClassCreation(ActionEvent event) {
        try {
            Parent root = GUIManager.loadFXML("character-class");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.err.println("Failed to load the next screen.");
            e.printStackTrace();
            showErrorAlert("Navigation Error", "Could not load the next screen.");
        }
    }
}
