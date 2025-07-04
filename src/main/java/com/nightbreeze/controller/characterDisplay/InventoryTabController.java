package com.nightbreeze.controller.characterDisplay;

import com.nightbreeze.model.Character;
import com.nightbreeze.model.Equipment;
import com.nightbreeze.util.CharacterData;
import com.nightbreeze.util.JsonFileReader;
import com.nightbreeze.util.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryTabController implements Initializable {

    @FXML private ListView<Equipment> inventoryListView;
    @FXML private ComboBox<Equipment> addEquipmentComboBox;

    private Character currentCharacter;
    private final CharacterData characterData = new CharacterData();
    private static List<Equipment> allEquipment;
    private MainSheetTabController mainSheetController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (allEquipment == null) {
            allEquipment = JsonFileReader.readJsonDataFile("equipment");
        }
        if (allEquipment != null) {
            addEquipmentComboBox.setItems(FXCollections.observableArrayList(allEquipment));
        }
    }

    public void initData(Character character, MainSheetTabController mainSheetController) {
        this.currentCharacter = character;
        this.mainSheetController = mainSheetController;
        if (this.currentCharacter != null) {
            updateInventoryUi();
        }
    }

    @FXML
    void handleAddItemButton(ActionEvent event) {
        Equipment selectedItem = addEquipmentComboBox.getValue();
        if (selectedItem == null) {
            Utils.showErrorAlert("No Selection", "Please select an item from the dropdown to add.");
            return;
        }
        currentCharacter.getInventory().add(selectedItem);
        characterData.saveCharacterData(currentCharacter);
        updateInventoryUi();
        addEquipmentComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    void handleEquipItemButton(ActionEvent event) {
        Equipment selected = inventoryListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Utils.showErrorAlert("No Selection", "Please select an item from the inventory to equip.");
            return;
        }

        if (selected.getCategory() != null && selected.getCategory().getName().equals("Armor")) {
            currentCharacter.setEquippedArmor(selected);
            characterData.saveCharacterData(currentCharacter);
            mainSheetController.updateAcUi();
            Utils.showInfoAlert("Item Equipped", selected.getName() + " has been equipped.");
        } else {
            Utils.showInfoAlert("Cannot Equip", "You can only equip items categorized as 'Armor'.");
        }
    }

    @FXML
    void handleUnequipItemButton(ActionEvent event) {
        if (currentCharacter.getEquippedArmor() != null) {
            String unequippedItemName = currentCharacter.getEquippedArmor().getName();
            currentCharacter.setEquippedArmor(null);
            characterData.saveCharacterData(currentCharacter);
            mainSheetController.updateAcUi();
            Utils.showInfoAlert("Item Unequipped", unequippedItemName + " has been unequipped.");
        } else {
            Utils.showInfoAlert("Nothing to Unequip", "No armor is currently equipped.");
        }
    }

    private void updateInventoryUi() {
        if (currentCharacter.getInventory() != null) {
            inventoryListView.setItems(FXCollections.observableArrayList(currentCharacter.getInventory()));
        }
    }

    @FXML
    void handleRemoveItemButton(ActionEvent event) {
        Equipment selectedItem = inventoryListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Utils.showErrorAlert("No Selection", "Please select an item from the inventory to remove.");
            return;
        }
        if (selectedItem.equals(currentCharacter.getEquippedArmor())) {
            currentCharacter.setEquippedArmor(null);
            mainSheetController.refreshAllUiPublic();
        }

        // Remove the item from the inventory list
        currentCharacter.getInventory().remove(selectedItem);

        // Save the updated character and refresh the UI
        characterData.saveCharacterData(currentCharacter);
        updateInventoryUi(); // This refreshes the ListView

        Utils.showInfoAlert("Item Removed", selectedItem.getName() + " has been removed from your inventory.");
    }
}