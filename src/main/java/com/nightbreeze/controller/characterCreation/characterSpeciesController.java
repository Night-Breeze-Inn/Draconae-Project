package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;

import com.nightbreeze.model.Language;
import com.nightbreeze.model.Species;
import com.nightbreeze.model.Trait;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.JsonFileReader;
import com.nightbreeze.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
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

    CharacterData characterData = new CharacterData();

    private static List<Species> speciesList;

    public static Species selectedSpecies = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (speciesList == null) {
            speciesList = JsonFileReader.readJsonDataFile("species");
            if (speciesList.isEmpty()) {
                Utils.showErrorAlert("Data error", "No species found");
                disableAllButton(true);
            } else {
                System.out.println("Species data loaded successfully: " + speciesList.size() + " species.");
            }
        }
        selectedSpecies = null;
    }

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

    private void speciesSelection(String species, ActionEvent actionEvent) throws IOException {
        if (speciesList == null || speciesList.isEmpty()) {
            Utils.showErrorAlert("Data error", "Data not loaded");
            return;
        }

        Optional<Species> speciesOptional = speciesList.stream().filter(s -> s.getName().equals(species)).findFirst();
        if (speciesOptional.isPresent()) {
            selectedSpecies = speciesOptional.get();

            character.setRace(selectedSpecies.getName());
            character.setHeight(selectedSpecies.getSize());
            character.setSpeed(selectedSpecies.getSpeed());

            Map<String, Integer> abilityScore = selectedSpecies.getAbilityScoreIncrease();
            for (Map.Entry<String, Integer> entry : abilityScore.entrySet()) {
                if (entry.getKey().equals("Strength")) {
                    character.setStrength(entry.getValue());
                }
                if (entry.getKey().equals("Dexterity")) {
                    character.setDexterity(entry.getValue());
                }
                if (entry.getKey().equals("Constitution")) {
                    character.setConstitution(entry.getValue());
                }
                if (entry.getKey().equals("Intelligence")) {
                    character.setIntelligence(entry.getValue());
                }
                if (entry.getKey().equals("Wisdom")) {
                    character.setWisdom(entry.getValue());
                }
                if (entry.getKey().equals("Charisma")) {
                    character.setCharisma(entry.getValue());
                }
            }

            List<String> languages = selectedSpecies
                .getLanguages()
                .stream()
                .map(Language::getName)
                .collect(Collectors.toList());
            character.setLanguage(languages);

            List<String> traits = selectedSpecies.getTraits().stream().map(Trait::getName).collect(Collectors.toList());
            character.setRacialTraits(traits);
            characterData.saveCharacterData(character);

            boolean hasSubraces = selectedSpecies.getSubraces() != null && !selectedSpecies.getSubraces().isEmpty();
            String nextScreen = hasSubraces ? "character-sub-species" : "character-class";
            Parent root = GUIManager.loadFXML(nextScreen);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } else {
            Utils.showErrorAlert("Error", "Could not find data for species: " + species);
            System.err.println("Species not found in loaded data: " + species);
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
