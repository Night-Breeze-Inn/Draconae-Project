package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;
import static com.nightbreeze.controller.characterCreation.characterSpeciesController.selectedSpecies;
import static com.nightbreeze.util.Utils.showErrorAlert;

import com.nightbreeze.model.AbilityBonus;
import com.nightbreeze.model.ApiReference;
import com.nightbreeze.model.Subrace;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.JsonFileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
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

    // FXML UI Elements
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
    private static List<Subrace> allSubraces;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (allSubraces == null) {
            allSubraces = JsonFileReader.readJsonDataFile("subraces");
        }

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
        speciesDescriptionLabel.setText("Choose a " + selectedSpecies.getName() + " Sub-species");

        List<ApiReference> requiredSubraceRefs = characterSpeciesController.selectedSpeciesSubraceRefs;

        if (requiredSubraceRefs == null || requiredSubraceRefs.isEmpty()) {
            showErrorAlert("Logic Error", "This screen was reached, but no sub-species are available for " + selectedSpecies.getName());
            return;
        }

        Set<String> requiredNames = requiredSubraceRefs.stream().map(ApiReference::getName).collect(Collectors.toSet());

        List<Subrace> availableSubraces = allSubraces
                .stream()
                .filter(subrace -> requiredNames.contains(subrace.getName()))
                .collect(Collectors.toList());
        for (int i = 0; i < availableSubraces.size() && i < subSpeciesButtons.size(); i++) {
            Subrace subrace = availableSubraces.get(i);
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

    private void subSpeciesSelection(Subrace subrace, ActionEvent event) throws IOException {
        character.setSubRace(subrace.getName());

        if (subrace.getAbilityBonuses() != null) {
            for (AbilityBonus ab : subrace.getAbilityBonuses()) {
                int bonus = ab.getBonus();
                String statName = ab.getAbilityScore().getName();
                switch (statName) {
                    case "STR" -> character.setStrength(character.getStrength() + bonus);
                    case "DEX" -> character.setDexterity(character.getDexterity() + bonus);
                    case "CON" -> character.setConstitution(character.getConstitution() + bonus);
                    case "INT" -> character.setIntelligence(character.getIntelligence() + bonus);
                    case "WIS" -> character.setWisdom(character.getWisdom() + bonus);
                    case "CHA" -> character.setCharisma(character.getCharisma() + bonus);
                }
            }
        }

        if (subrace.getRacialTraits() != null) {
            List<String> newTraits = subrace
                    .getRacialTraits()
                    .stream()
                    .map(ApiReference::getName)
                    .collect(Collectors.toList());
            character.getRacialTraits().addAll(newTraits);
        }


        characterData.saveCharacterData(character);
        proceedToClassCreation(event);
    }

    private void proceedToClassCreation(ActionEvent actionEvent) {
        try {
            GUIManager.changeScene((Node) actionEvent.getSource(), "character-class");
        } catch (IOException e) {
            System.err.println("Failed to load the character-class screen.");
            e.printStackTrace();
            showErrorAlert("Navigation Error", "Could not load the next screen.");
        }
    }
}