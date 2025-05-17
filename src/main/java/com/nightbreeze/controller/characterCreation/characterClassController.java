package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;

import com.nightbreeze.model.Classes;
import com.nightbreeze.model.Proficiency;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.JsonFileReader;
import com.nightbreeze.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
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

    CharacterData characterData = new CharacterData();

    private static List<Classes> classesList;

    public static Classes classSelected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (classesList == null) {
            classesList = JsonFileReader.readJsonDataFile("classes");
            if (classesList.isEmpty()) {
                Utils.showErrorAlert("Data error", "No classes found");
                disableAllButton(true);
            } else {
                System.out.println("Classes data loaded successfully: " + classesList.size() + " classes.");
            }
        }
        classSelected = null;
    }

    public void barbarianButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Barbarian", actionEvent);
    }

    public void bardButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Bard", actionEvent);
    }

    public void clericButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Cleric", actionEvent);
    }

    public void druidButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Druid", actionEvent);
    }

    public void fighterButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Fighter", actionEvent);
    }

    public void monkButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Monk", actionEvent);
    }

    public void paladinButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Paladin", actionEvent);
    }

    public void rangerButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Ranger", actionEvent);
    }

    public void rogueButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Rogue", actionEvent);
    }

    public void sorcererButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Sorcerer", actionEvent);
    }

    public void warlockButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Warlock", actionEvent);
    }

    public void wizardButtonOnAction(ActionEvent actionEvent) throws IOException {
        classSelection("Wizard", actionEvent);
    }

    public void classSelection(String className, ActionEvent actionEvent) throws IOException {
        if (classesList == null || classesList.isEmpty()) {
            Utils.showErrorAlert("Data error", "Data not loaded");
            return;
        }

        Optional<Classes> optionalClass = classesList.stream().filter(c -> c.getClassName().equals(className)).findFirst();
        if (optionalClass.isPresent()) {
            classSelected = optionalClass.get();
            Hashtable<String, ArrayList<String>> proficiencies = new Hashtable<>();
            proficiencies.put("Armor", new ArrayList<>());
            proficiencies.put("Weapons", new ArrayList<>());
            proficiencies.put("Tools", new ArrayList<>());
            for (String prof : classSelected.getProficienciesArmor()) {
                if (!proficiencies.get("Armor").contains(prof)) {
                    proficiencies.get("Armor").add(prof);
                }
            }
            for (String prof : classSelected.getProficienciesWeapons()) {
                if (!proficiencies.get("Weapons").contains(prof)) {
                    proficiencies.get("Weapons").add(prof);
                }
            }
            for (String prof : classSelected.getProficienciesTools()) {
                if (!proficiencies.get("Tools").contains(prof)) {
                    proficiencies.get("Tools").add(prof);
                }
            }
            character.setClassName(classSelected.getClassName());
            character.setMaxHP(classSelected.getHitPoints());
            character.setCurrentHP(classSelected.getHitPoints());
            character.setHitDice(classSelected.getHitDice());
            character.setProficiency(proficiencies);

            characterData.saveCharacterData(character);
            Boolean asSubClass = false; // Placeholder
            String nextScreen = asSubClass ? "character-sub-classes" : "character-stats";
            Parent root = GUIManager.loadFXML(nextScreen);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
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
