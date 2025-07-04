package com.nightbreeze.controller.characterDisplay;

import com.nightbreeze.model.Character;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.Dice;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.Utils;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

public class MainSheetTabController{

    @FXML
    private Label characterNameLabel;
    @FXML
    private Label raceClassLevelLabel;
    @FXML
    private Label hpLabel;
    @FXML
    private Label acLabel;
    @FXML
    private Label xpLabel, xpLabel1;
    @FXML
    private ProgressBar hpProgressBar;
    @FXML
    private ProgressBar xpProgressBar;
    @FXML
    private Label strScoreLabel;
    @FXML
    private Label strModifierLabel;
    @FXML
    private Label dexScoreLabel;
    @FXML
    private Label dexModifierLabel;
    @FXML
    private Label conScoreLabel;
    @FXML
    private Label conModifierLabel;
    @FXML
    private Label intScoreLabel;
    @FXML
    private Label intModifierLabel;
    @FXML
    private Label wisScoreLabel;
    @FXML
    private Label wisModifierLabel;
    @FXML
    private Label chaScoreLabel;
    @FXML
    private Label chaModifierLabel;
    @FXML
    private VBox savingThrowsBox;
    @FXML
    private VBox armorProficiencyBox;
    @FXML
    private VBox weaponProficiencyBox;
    @FXML
    private VBox toolProficiencyBox;
    @FXML
    private TextField healTextField;
    @FXML
    private TextField damageTextField;
    @FXML
    private TextField tempHpTextField;
    @FXML
    private TextField xpTextField;
    @FXML
    private ComboBox<String> savingThrowComboBox;
    @FXML
    private ComboBox<String> attackAbilityComboBox;
    @FXML private Button backButton;

    private Character currentCharacter;
    private final CharacterData characterData = new CharacterData();

    public void initData(Character character) {
        this.currentCharacter = character;
        if (this.currentCharacter != null) {
            refreshAllUi();
        }
    }

    @FXML
    void handleHealButton(ActionEvent event) {
        handleHpChange(healTextField, true);
    }

    @FXML
    void handleDamageButton(ActionEvent event) {
        handleHpChange(damageTextField, false);
    }

    @FXML
    void handleSetTempHpButton(ActionEvent event) {
        try {
            int amount = Integer.parseInt(tempHpTextField.getText());
            currentCharacter.setTemporaryHP(Math.max(0, amount));
            characterData.saveCharacterData(currentCharacter);
            updateHpUi();
            tempHpTextField.clear();
        } catch (NumberFormatException e) {
            Utils.showErrorAlert("Invalid Input", "Please enter a valid number for Temp HP.");
        }
    }

    @FXML
    void handleSavingThrowRollButton(ActionEvent event) {
        String selectedAbility = savingThrowComboBox.getValue();
        if (selectedAbility == null) {
            Utils.showErrorAlert("No Selection", "Please select an ability for the saving throw.");
            return;
        }

        int modifier = getAbilityModifierFromString(selectedAbility);
        if (isProficientInSave(selectedAbility)) {
            modifier += currentCharacter.getProficiencyBonus();
        }

        int roll = Dice.rollD20(modifier);
        Utils.showInfoAlert("Saving Throw Roll", String.format("%s Saving Throw Result: %d", selectedAbility, roll));
    }

    @FXML
    void handleAttackRollButton(ActionEvent event) {
        String selectedAbility = attackAbilityComboBox.getValue();
        if (selectedAbility == null) {
            Utils.showErrorAlert("No Selection", "Please select an ability for the attack roll.");
            return;
        }

        int modifier = getAbilityModifierFromString(selectedAbility);
        modifier += currentCharacter.getProficiencyBonus();

        int roll = Dice.rollD20(modifier);
        Utils.showInfoAlert("Attack Roll", String.format("%s Attack Roll Result: %d", selectedAbility, roll));
    }

    @FXML
    void handleAddXpButton(ActionEvent event) {
        try {
            int amount = Integer.parseInt(xpTextField.getText());
            if (amount <= 0) return;
            currentCharacter.setExperiencePoints(currentCharacter.getExperiencePoints() + amount);

            if (currentCharacter.checkForLevelUp()) {
                refreshAllUi();
            } else {
                updateXpUi();
            }

            characterData.saveCharacterData(currentCharacter);
            xpTextField.clear();
        } catch (NumberFormatException e) {
            Utils.showErrorAlert("Invalid Input", "Please enter a valid number for XP.");
        }
    }

    @FXML
    void handleBackButtonAction(ActionEvent event) {
        try {
            GUIManager.changeScene((Node) event.getSource(), "home-page");
        } catch (IOException e) {
            System.err.println("Failed to load home-page.fxml");
            e.printStackTrace();
            Utils.showErrorAlert("Navigation Error", "Could not return to the home screen.");
        }
    }


    private void refreshAllUi() {
        populateHeader();
        updateHpUi();
        updateAcUi();
        populateAbilityScores();
        populateProficiencies();
        populateActionsPanel();
        updateXpUi();
    }

    public void refreshAllUiPublic() {
        populateHeader();
        updateHpUi();
        updateAcUi();
        populateAbilityScores();
        populateProficiencies();
        populateActionsPanel();
        updateXpUi();
    }

    private void populateHeader() {
        characterNameLabel.setText(currentCharacter.getName());
        String subRace = currentCharacter.getSubRace() != null && !currentCharacter.getSubRace().isEmpty() ? currentCharacter.getSubRace() + " " : "";
        raceClassLevelLabel.setText(String.format("(%s%s | %s %d)", subRace, currentCharacter.getRace(), currentCharacter.getClassName(), currentCharacter.getLevel()));
    }

    private void updateHpUi() {
        int currentHp = currentCharacter.getCurrentHP();
        int maxHp = currentCharacter.getMaxHP();
        int tempHp = currentCharacter.getTemporaryHP();
        String hpText = String.format("HP: %d / %d", currentHp, maxHp);
        if (tempHp > 0) {
            hpText += String.format(" (+%d Temp)", tempHp);
        }
        hpLabel.setText(hpText);
        hpProgressBar.setProgress(maxHp > 0 ? (double) currentHp / maxHp : 0.0);
    }

    public void updateAcUi() {
        acLabel.setText("AC: " + currentCharacter.getArmorClass());
    }

    private void updateXpUi() {
        int currentXp = currentCharacter.getExperiencePoints();
        int nextLevelXp = currentCharacter.getXpForNextLevel();
        xpLabel.setText(String.format("XP: %d / %d", currentXp, nextLevelXp));
        if (currentCharacter.getLevel() < 20) {
            int prevLevelXp = Character.getXpForLevel(currentCharacter.getLevel());
            double xpIntoCurrentLevel = currentXp - prevLevelXp;
            double xpNeededForThisLevel = nextLevelXp - prevLevelXp;
            xpProgressBar.setProgress(xpNeededForThisLevel > 0 ? (xpIntoCurrentLevel / xpNeededForThisLevel) : 0);
        } else {
            xpProgressBar.setProgress(1.0);
        }
    }

    private void populateAbilityScores() {
        strScoreLabel.setText(String.valueOf(currentCharacter.getStrength()));
        strModifierLabel.setText(formatModifier(currentCharacter.getStrengthModifier()));
        dexScoreLabel.setText(String.valueOf(currentCharacter.getDexterity()));
        dexModifierLabel.setText(formatModifier(currentCharacter.getDexterityModifier()));
        conScoreLabel.setText(String.valueOf(currentCharacter.getConstitution()));
        conModifierLabel.setText(formatModifier(currentCharacter.getConstitutionModifier()));
        intScoreLabel.setText(String.valueOf(currentCharacter.getIntelligence()));
        intModifierLabel.setText(formatModifier(currentCharacter.getIntelligenceModifier()));
        wisScoreLabel.setText(String.valueOf(currentCharacter.getWisdom()));
        wisModifierLabel.setText(formatModifier(currentCharacter.getWisdomModifier()));
        chaScoreLabel.setText(String.valueOf(currentCharacter.getCharisma()));
        chaModifierLabel.setText(formatModifier(currentCharacter.getCharismaModifier()));
    }

    private void populateProficiencies() {
        Hashtable<String, ArrayList<String>> profs = currentCharacter.getProficiency();
        if (profs == null) return;
        addProficienciesToBox(savingThrowsBox, profs.get("Saving Throws"));
        addProficienciesToBox(armorProficiencyBox, profs.get("Armor"));
        addProficienciesToBox(weaponProficiencyBox, profs.get("Weapons"));
        addProficienciesToBox(toolProficiencyBox, profs.get("Tools"));
    }

    private void populateActionsPanel() {
        List<String> abilities = List.of("Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma");
        savingThrowComboBox.setItems(FXCollections.observableArrayList(abilities));
        attackAbilityComboBox.setItems(FXCollections.observableArrayList(abilities));
    }

    private void handleHpChange(TextField sourceField, boolean isHealing) {
        try {
            int amount = Integer.parseInt(sourceField.getText());
            if (amount <= 0) return;
            if (isHealing) {
                currentCharacter.heal(amount);
            } else {
                currentCharacter.damage(amount);
            }
            characterData.saveCharacterData(currentCharacter);
            updateHpUi();
            sourceField.clear();
        } catch (NumberFormatException e) {
            Utils.showErrorAlert("Invalid Input", "Please enter a valid number.");
        }
    }

    private void addProficienciesToBox(VBox box, List<String> proficiencyList) {
        box.getChildren().clear();
        if (proficiencyList == null || proficiencyList.isEmpty()) {
            box.getChildren().add(new Label("None"));
        } else {
            for (String prof : proficiencyList) {
                box.getChildren().add(new Label(prof));
            }
        }
    }

    private String formatModifier(int modifier) {
        return modifier >= 0 ? "+" + modifier : String.valueOf(modifier);
    }

    private int getAbilityModifierFromString(String abilityName) {
        return switch (abilityName) {
            case "Strength" -> currentCharacter.getStrengthModifier();
            case "Dexterity" -> currentCharacter.getDexterityModifier();
            case "Constitution" -> currentCharacter.getConstitutionModifier();
            case "Intelligence" -> currentCharacter.getIntelligenceModifier();
            case "Wisdom" -> currentCharacter.getWisdomModifier();
            case "Charisma" -> currentCharacter.getCharismaModifier();
            default -> 0;
        };
    }

    private boolean isProficientInSave(String abilityName) {
        Hashtable<String, ArrayList<String>> profs = currentCharacter.getProficiency();
        if (profs == null || profs.get("Saving Throws") == null) {
            return false;
        }
        return profs.get("Saving Throws").contains(abilityName);
    }

    public void applyStylingAfterLoad() {
        hpProgressBar.lookup(".bar").setStyle("-fx-background-color: red;");
        xpProgressBar.lookup(".bar").setStyle("-fx-background-color: #1F78D1;");
    }



}