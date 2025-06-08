package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;

import com.nightbreeze.model.ApiReference;
import com.nightbreeze.model.Classes;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.JsonFileReader;
import com.nightbreeze.util.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
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

public class characterClassController implements Initializable {

    @FXML
    private Button barbarianButton;
    @FXML
    private Button bardButton;
    @FXML
    private Button clericButton;
    @FXML
    private Button druidButton;
    @FXML
    private Button fighterButton;
    @FXML
    private Button monkButton;
    @FXML
    private Button paladinButton;
    @FXML
    private Button rangerButton;
    @FXML
    private Button rogueButton;
    @FXML
    private Button sorcererButton;
    @FXML
    private Button warlockButton;
    @FXML
    private Button wizardButton;

    private final CharacterData characterData = new CharacterData();
    private static List<Classes> classesList; // Cache for all class data
    public static Classes classSelected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load class data from JSON if it hasn't been loaded yet
        if (classesList == null) {
            classesList = JsonFileReader.readJsonDataFile("classes");
            if (classesList.isEmpty()) {
                Utils.showErrorAlert("Data Error", "No classes found in classes.json. Cannot proceed.");
                disableAllButton(true);
            } else {
                System.out.println("Classes data loaded successfully: " + classesList.size() + " classes.");
            }
        }
        classSelected = null;
    }

    // --- Button Actions ---
    public void barbarianButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Barbarian", e);
    }

    public void bardButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Bard", e);
    }

    public void clericButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Cleric", e);
    }

    public void druidButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Druid", e);
    }

    public void fighterButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Fighter", e);
    }

    public void monkButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Monk", e);
    }

    public void paladinButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Paladin", e);
    }

    public void rangerButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Ranger", e);
    }

    public void rogueButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Rogue", e);
    }

    public void sorcererButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Sorcerer", e);
    }

    public void warlockButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Warlock", e);
    }

    public void wizardButtonOnAction(ActionEvent e) throws IOException {
        classSelection("Wizard", e);
    }

    public void classSelection(String className, ActionEvent actionEvent) throws IOException {
        if (classesList == null || classesList.isEmpty()) {
            Utils.showErrorAlert("Data error", "Class data has not been loaded.");
            return;
        }

        Optional<Classes> optionalClass = classesList.stream().filter(c -> c.getName().equals(className)).findFirst();

        if (optionalClass.isPresent()) {
            classSelected = optionalClass.get();

            character.setClassName(classSelected.getName());

            character.setHitDice("1d" + classSelected.getHitDie());

            character.setMaxHP(classSelected.getHitDie());
            character.setCurrentHP(classSelected.getHitDie());

            if (character.getProficiency() == null) {
                character.setProficiency(new Hashtable<>());
            }
            Hashtable<String, ArrayList<String>> proficiencies = character.getProficiency();
            proficiencies.computeIfAbsent("Armor", k -> new ArrayList<>());
            proficiencies.computeIfAbsent("Weapons", k -> new ArrayList<>());
            proficiencies.computeIfAbsent("Tools", k -> new ArrayList<>());
            proficiencies.computeIfAbsent("Saving Throws", k -> new ArrayList<>());
            proficiencies.computeIfAbsent("Skills", k -> new ArrayList<>());

            List<String> savingThrows = classSelected.getSavingThrows().stream()
                    .map(ApiReference::getName)
                    .map(s -> s.replace("Saving Throw: ", "")) // Clean up the name
                    .collect(Collectors.toList());
            proficiencies.get("Saving Throws").addAll(savingThrows);

            // Add fixed proficiencies (Armor, Weapons, etc.)
            for (ApiReference prof : classSelected.getProficiencies()) {
                String profName = prof.getName();
                if (profName.contains("Armor:")) {
                    proficiencies.get("Armor").add(profName.replace("Armor: ", "").trim());
                } else if (profName.contains("Weapons:")) {
                    proficiencies.get("Weapons").add(profName.replace("Weapons: ", "").trim());
                } else if (profName.contains("Tools:")) {
                    proficiencies.get("Tools").add(profName.replace("Tools: ", "").trim());
                }
            }

            characterData.saveCharacterData(character);

            GUIManager.changeScene((Node) actionEvent.getSource(), "character-stats");
        } else {
            Utils.showErrorAlert("Error", "Could not find data for class: " + className);
            System.err.println("Class not found in loaded data: " + className);
        }
    }

    public void disableAllButton(boolean disable) {
        if (barbarianButton != null) barbarianButton.setDisable(disable);
        if (bardButton != null) bardButton.setDisable(disable);
        if (clericButton != null) clericButton.setDisable(disable);
        if (druidButton != null) druidButton.setDisable(disable);
        if (fighterButton != null) fighterButton.setDisable(disable);
        if (monkButton != null) monkButton.setDisable(disable);
        if (paladinButton != null) paladinButton.setDisable(disable);
        if (rangerButton != null) rangerButton.setDisable(disable);
        if (rogueButton != null) rogueButton.setDisable(disable);
        if (sorcererButton != null) sorcererButton.setDisable(disable);
        if (warlockButton != null) warlockButton.setDisable(disable);
        if (wizardButton != null) wizardButton.setDisable(disable);
    }
}