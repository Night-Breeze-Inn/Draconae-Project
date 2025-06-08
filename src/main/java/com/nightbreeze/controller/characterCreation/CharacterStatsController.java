package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;

import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.Dice;
import com.nightbreeze.util.GUIManager;
import com.nightbreeze.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CharacterStatsController implements Initializable {

    @FXML private TextField strengthTextField;
    @FXML private TextField dexterityTextField;
    @FXML private TextField constitutionTextField;
    @FXML private TextField intelligenceTextField;
    @FXML private TextField wisdomTextField;
    @FXML private TextField charismaTextField;

    @FXML private Label strengthBonusLabel;
    @FXML private Label dexterityBonusLabel;
    @FXML private Label constitutionBonusLabel;
    @FXML private Label intelligenceBonusLabel;
    @FXML private Label wisdomBonusLabel;
    @FXML private Label charismaBonusLabel;

    private final CharacterData characterData = new CharacterData();

    private int strengthBonus;
    private int dexterityBonus;
    private int constitutionBonus;
    private int intelligenceBonus;
    private int wisdomBonus;
    private int charismaBonus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        strengthBonus = character.getStrength();
        dexterityBonus = character.getDexterity();
        constitutionBonus = character.getConstitution();
        intelligenceBonus = character.getIntelligence();
        wisdomBonus = character.getWisdom();
        charismaBonus = character.getCharisma();

        addStatListener(strengthTextField, strengthBonusLabel, strengthBonus);
        addStatListener(dexterityTextField, dexterityBonusLabel, dexterityBonus);
        addStatListener(constitutionTextField, constitutionBonusLabel, constitutionBonus);
        addStatListener(intelligenceTextField, intelligenceBonusLabel, intelligenceBonus);
        addStatListener(wisdomTextField, wisdomBonusLabel, wisdomBonus);
        addStatListener(charismaTextField, charismaBonusLabel, charismaBonus);

        updateLabel(strengthBonusLabel, 0, strengthBonus);
        updateLabel(dexterityBonusLabel, 0, dexterityBonus);
        updateLabel(constitutionBonusLabel, 0, constitutionBonus);
        updateLabel(intelligenceBonusLabel, 0, intelligenceBonus);
        updateLabel(wisdomBonusLabel, 0, wisdomBonus);
        updateLabel(charismaBonusLabel, 0, charismaBonus);
    }

    private void addStatListener(TextField textField, Label label, int bonus) {
        textField
                .textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    int baseValue = 0;
                    try {
                        if (newValue != null && !newValue.isEmpty()) {
                            baseValue = Integer.parseInt(newValue);
                        }
                    } catch (NumberFormatException e) {
                        baseValue = 0;
                    }
                    updateLabel(label, baseValue, bonus);
                });
    }

    private void updateLabel(Label label, int baseValue, int bonus) {
        int finalScore = baseValue + bonus;
        int modifier = (finalScore - 10) / 2;
        String modifierSign = modifier >= 0 ? "+" : "";
        label.setText(String.format("Total: %d (%s%d)", finalScore, modifierSign, modifier));
    }

    // --- Random Roll Actions ---
    public void strengthRandomButtonAction(ActionEvent event) {
        strengthTextField.setText(String.valueOf(Dice.roll("4d6-L")));
    }

    public void dexterityRandomButtonAction(ActionEvent event) {
        dexterityTextField.setText(String.valueOf(Dice.roll("4d6-L")));
    }

    public void constitutionRandomButtonAction(ActionEvent event) {
        constitutionTextField.setText(String.valueOf(Dice.roll("4d6-L")));
    }

    public void intelligenceRandomButtonAction(ActionEvent event) {
        intelligenceTextField.setText(String.valueOf(Dice.roll("4d6-L")));
    }

    public void wisdomRandomButtonAction(ActionEvent event) {
        wisdomTextField.setText(String.valueOf(Dice.roll("4d6-L")));
    }

    public void charismaRandomButtonAction(ActionEvent event) {
        charismaTextField.setText(String.valueOf(Dice.roll("4d6-L")));
    }

    public void submitButtonOnAction(ActionEvent event) throws IOException {
        if (
                strengthTextField.getText().isEmpty() ||
                        dexterityTextField.getText().isEmpty() ||
                        constitutionTextField.getText().isEmpty() ||
                        intelligenceTextField.getText().isEmpty() ||
                        wisdomTextField.getText().isEmpty() ||
                        charismaTextField.getText().isEmpty()
        ) {
            Utils.showErrorAlert("Missing Stats", "Please enter a base value for all six ability scores.");
            return;
        }

        try {
            int finalStrength = Integer.parseInt(strengthTextField.getText()) + strengthBonus;
            int finalDexterity = Integer.parseInt(dexterityTextField.getText()) + dexterityBonus;
            int finalConstitution = Integer.parseInt(constitutionTextField.getText()) + constitutionBonus;
            int finalIntelligence = Integer.parseInt(intelligenceTextField.getText()) + intelligenceBonus;
            int finalWisdom = Integer.parseInt(wisdomTextField.getText()) + wisdomBonus;
            int finalCharisma = Integer.parseInt(charismaTextField.getText()) + charismaBonus;

            character.setStrength(finalStrength);
            character.setDexterity(finalDexterity);
            character.setConstitution(finalConstitution);
            character.setIntelligence(finalIntelligence);
            character.setWisdom(finalWisdom);
            character.setCharisma(finalCharisma);

            int constitutionModifier = (finalConstitution - 10) / 2;
            character.setMaxHP(character.getMaxHP() + constitutionModifier);
            character.setCurrentHP(character.getMaxHP());

            characterData.saveCharacterData(character);
            System.out.println("Character creation complete!");
            System.out.println(character.toString());

            GUIManager.changeScene((Node) event.getSource(), "home-page");

        } catch (NumberFormatException e) {
            Utils.showErrorAlert("Invalid Input", "Please ensure all stats are valid numbers.");
        }
    }
}