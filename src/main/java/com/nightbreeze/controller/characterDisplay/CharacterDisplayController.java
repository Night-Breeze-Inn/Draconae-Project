package com.nightbreeze.controller.characterDisplay;

import com.nightbreeze.model.Character;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class CharacterDisplayController {

    @FXML private TabPane rootPane;

    @FXML private MainSheetTabController mainSheetTabController;
    @FXML private InventoryTabController inventoryTabController;

    @FXML
    public void initialize() {
        Character currentCharacter = CharacterData.loadCharacterData();
        if (currentCharacter == null) {
            Utils.showErrorAlert("Load Error", "Could not load character data.");
            rootPane.setDisable(true);
            return;
        }

        mainSheetTabController.initData(currentCharacter);
        inventoryTabController.initData(currentCharacter, mainSheetTabController);
        javafx.application.Platform.runLater(() -> {
            mainSheetTabController.applyStylingAfterLoad();
        });
    }

}