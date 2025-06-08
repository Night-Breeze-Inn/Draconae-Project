package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;

import com.nightbreeze.model.AbilityBonus;
import com.nightbreeze.model.ApiReference;
import com.nightbreeze.model.Species;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.JsonFileReader;
import com.nightbreeze.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class characterSpeciesController implements Initializable {

    // FXML Buttons
    @FXML
    private Button dragonbornButton;

    @FXML
    private Button dwarfButton;

    @FXML
    private Button elfButton;

    @FXML
    private Button gnomeButton;

    @FXML
    private Button halflingButton;

    @FXML
    private Button halfElfButton;

    @FXML
    private Button halfOrcButton;

    @FXML
    private Button humanButton;

    @FXML
    private Button tieflingButton;

    // Data Management
    private final CharacterData characterData = new CharacterData();
    private static List<Species> speciesList; // Cache for all species data
    public static Species selectedSpecies = null;
    public static List<ApiReference> selectedSpeciesSubraceRefs = null; // To pass to the next controller

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load species data from JSON if it hasn't been loaded yet
        if (speciesList == null) {
            speciesList = JsonFileReader.readJsonDataFile("species");
            if (speciesList.isEmpty()) {
                Utils.showErrorAlert("Data Error", "No species found in species.json. Cannot proceed.");
                disableAllButton(true);
            } else {
                System.out.println("Species data loaded successfully: " + speciesList.size() + " species.");
            }
        }
        // Reset static fields for a fresh selection
        selectedSpecies = null;
        selectedSpeciesSubraceRefs = null;
    }

    // --- Button Actions ---
    public void dragonbornButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Dragonborn", actionEvent);
    }

    public void dwarfButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Dwarf", actionEvent);
    }

    public void elfButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Elf", actionEvent);
    }

    public void gnomeButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Gnome", actionEvent);
    }

    public void halflingButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Halfling", actionEvent);
    }

    public void halfElfButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Half-Elf", actionEvent);
    }

    public void halfOrcButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Half-Orc", actionEvent);
    }

    public void humanButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Human", actionEvent);
    }

    public void tieflingButtonOnAction(ActionEvent actionEvent) throws IOException {
        speciesSelection("Tiefling", actionEvent);
    }


    private void speciesSelection(String speciesName, ActionEvent actionEvent) throws IOException {
        if (speciesList == null || speciesList.isEmpty()) {
            Utils.showErrorAlert("Data Error", "Species data has not been loaded.");
            return;
        }

        Optional<Species> speciesOptional = speciesList
                .stream()
                .filter(s -> s.getName().equalsIgnoreCase(speciesName))
                .findFirst();

        if (speciesOptional.isPresent()) {
            selectedSpecies = speciesOptional.get();

            character.setRace(selectedSpecies.getName());
            character.setHeight(selectedSpecies.getSize());
            character.setSpeed(selectedSpecies.getSpeed());

            for (AbilityBonus ab : selectedSpecies.getAbilityBonuses()) {
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

            List<String> languageNames = selectedSpecies
                    .getLanguages()
                    .stream()
                    .map(ApiReference::getName)
                    .collect(Collectors.toList());
            character.setLanguage(languageNames);

            List<String> traitNames = selectedSpecies
                    .getTraits()
                    .stream()
                    .map(ApiReference::getName)
                    .collect(Collectors.toList());
            character.setRacialTraits(traitNames);

            characterData.saveCharacterData(character);

            boolean hasSubraces = selectedSpecies.getSubraces() != null && !selectedSpecies.getSubraces().isEmpty();
            String nextScreen;

            if (hasSubraces) {
                selectedSpeciesSubraceRefs = selectedSpecies.getSubraces();
                nextScreen = "character-sub-species";
            } else {
                selectedSpeciesSubraceRefs = null;
                nextScreen = "character-class";
            }

            GUIManager.changeScene((Node) actionEvent.getSource(), nextScreen);
        } else {
            Utils.showErrorAlert("Error", "Could not find data for species: " + speciesName);
            System.err.println("Species not found in loaded data: " + speciesName);
        }
    }

    private void disableAllButton(boolean disable) {
        if (dragonbornButton != null) dragonbornButton.setDisable(disable);
        if (dwarfButton != null) dwarfButton.setDisable(disable);
        if (elfButton != null) elfButton.setDisable(disable);
        if (gnomeButton != null) gnomeButton.setDisable(disable);
        if (halflingButton != null) halflingButton.setDisable(disable);
        if (halfElfButton != null) halfElfButton.setDisable(disable);
        if (halfOrcButton != null) halfOrcButton.setDisable(disable);
        if (humanButton != null) humanButton.setDisable(disable);
        if (tieflingButton != null) tieflingButton.setDisable(disable);
    }
}