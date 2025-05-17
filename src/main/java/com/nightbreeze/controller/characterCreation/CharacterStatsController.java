package com.nightbreeze.controller.characterCreation;

import static com.nightbreeze.controller.characterCreation.characterNameController.character;

import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.Dice;
import com.nightbreeze.util.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CharacterStatsController implements Initializable {

    @FXML
    private TextField strengthTextField;

    @FXML
    private TextField dexterityTextField;

    @FXML
    private TextField constitutionTextField;

    @FXML
    private TextField inteligenceTextField;

    @FXML
    private TextField wisdomTextField;

    @FXML
    private TextField charismaTextField;

    @FXML
    private Button strengthRandomButton;

    @FXML
    private Button dexterityRandomButton;

    @FXML
    private Button constitutionRandomButton;

    @FXML
    private Button inteligenceRandomButton;

    @FXML
    private Button wisdomRandomButton;

    @FXML
    private Button charismaRandomButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label strengthBonusLabel;

    @FXML
    private Label dexterityBonusLabel;

    @FXML
    private Label constitutionBonusLabel;

    @FXML
    private Label inteligenceBonusLabel;

    @FXML
    private Label wisdomBonusLabel;

    @FXML
    private Label charismaBonusLabel;

    CharacterData characterData = new CharacterData();

    int tempStrength;
    int tempDexterity;
    int tempConstitution;
    int tempIntelligence;
    int tempWisdom;
    int tempCharisma;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (character.getStrength() != 0) {
            tempStrength = character.getStrength();
            strengthBonusLabel.setText("Current Bonus: +" + tempStrength);
        }
        if (character.getDexterity() != 0) {
            tempDexterity = character.getDexterity();
            dexterityBonusLabel.setText("Current Bonus: +" + tempDexterity);
        }
        if (character.getConstitution() != 0) {
            tempConstitution = character.getConstitution();
            constitutionBonusLabel.setText("Current Bonus: +" + tempConstitution);
        }
        if (character.getIntelligence() != 0) {
            tempIntelligence = character.getIntelligence();
            inteligenceBonusLabel.setText("Current Bonus: +" + tempIntelligence);
        }
        if (character.getWisdom() != 0) {
            tempWisdom = character.getWisdom();
            wisdomBonusLabel.setText("Current Bonus: +" + tempWisdom);
        }
        if (character.getCharisma() != 0) {
            tempCharisma = character.getCharisma();
            charismaBonusLabel.setText("Current Bonus: +" + tempCharisma);
        }
    }

    public void strengthRandomButtonAction(ActionEvent event) {
        randomStatistic("Strength");
    }

    public void dexterityRandomButtonAction(ActionEvent event) {
        randomStatistic("Dexterity");
    }

    public void constitutionRandomButtonAction(ActionEvent event) {
        randomStatistic("Constitution");
    }

    public void intelligenceRandomButtonAction(ActionEvent event) {
        randomStatistic("Intelligence");
    }

    public void wisdomRandomButtonAction(ActionEvent event) {
        randomStatistic("Wisdom");
    }

    public void charismaRandomButtonAction(ActionEvent event) {
        randomStatistic("Charisma");
    }

    public void randomStatistic(String stat) {
        if (stat.equals("Strength")) {
            int temp = Dice.rollD20(tempStrength);
            if (temp >= 20) {
                temp = 20;
            }
            strengthTextField.setText(Integer.toString(temp));
        }
        if (stat.equals("Dexterity")) {
            int temp = Dice.rollD20(tempDexterity);
            if (temp >= 20) {
                temp = 20;
            }
            dexterityTextField.setText(Integer.toString(temp));
        }
        if (stat.equals("Constitution")) {
            int temp = Dice.rollD20(tempConstitution);
            if (temp >= 20) {
                temp = 20;
            }
            constitutionTextField.setText(Integer.toString(temp));
        }
        if (stat.equals("Intelligence")) {
            int temp = Dice.rollD20(tempIntelligence);
            if (temp >= 20) {
                temp = 20;
            }
            inteligenceTextField.setText(Integer.toString(temp));
        }
        if (stat.equals("Wisdom")) {
            int temp = Dice.rollD20(tempWisdom);
            if (temp >= 20) {
                temp = 20;
            }
            wisdomTextField.setText(Integer.toString(temp));
        }
        if (stat.equals("Charisma")) {
            int temp = Dice.rollD20(tempCharisma);
            if (temp >= 20) {
                temp = 20;
            }
            charismaTextField.setText(Integer.toString(temp));
        }
    }

    public void submitButtonOnAction(ActionEvent event) {
        if (strengthTextField.getText().isEmpty()) {
            Utils.showErrorAlert("Error", "Please enter strength's statistic");
        }
        if (dexterityTextField.getText().isEmpty()) {
            Utils.showErrorAlert("Error", "Please enter dexterity's statistic");
        }
        if (constitutionTextField.getText().isEmpty()) {
            Utils.showErrorAlert("Error", "Please enter constitution's statistic");
        }
        if (inteligenceTextField.getText().isEmpty()) {
            Utils.showErrorAlert("Error", "Please enter intelligence's statistic");
        }
        if (wisdomTextField.getText().isEmpty()) {
            Utils.showErrorAlert("Error", "Please enter wisdom's statistic");
        }
        if (charismaTextField.getText().isEmpty()) {
            Utils.showErrorAlert("Error", "Please enter charisma's statistic");
        }
        if (
            !strengthTextField.getText().isEmpty() ||
            !dexterityTextField.getText().isEmpty() ||
            !constitutionTextField.getText().isEmpty() ||
            !inteligenceTextField.getText().isEmpty() ||
            !wisdomTextField.getText().isEmpty() ||
            !charismaTextField.getText().isEmpty()
        ) {
            character.setStrength(Integer.parseInt(strengthTextField.getText()));
            character.setDexterity(Integer.parseInt(dexterityTextField.getText()));
            character.setConstitution(Integer.parseInt(constitutionTextField.getText()));
            character.setIntelligence(Integer.parseInt(inteligenceTextField.getText()));
            character.setWisdom(Integer.parseInt(wisdomTextField.getText()));
            character.setCharisma(Integer.parseInt(charismaTextField.getText()));

            characterData.saveCharacterData(character);
        }
        System.out.println(character.toString());
    }
}
