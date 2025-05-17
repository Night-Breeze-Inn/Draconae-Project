package com.nightbreeze.util;

import static com.nightbreeze.util.Utils.showErrorAlert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nightbreeze.model.Character;
import java.io.File;
import java.io.IOException;

public class CharacterData {

    // Config
    private static final String DATA_FOLDER = "DnDAppCharacters";
    private static final String DEFAULT_FILENAME = "character_data.json";

    // Jackson
    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    // File Management
    public static void createDataFolder() {
        String userHome = System.getProperty("user.home") + "/documents";
        if (userHome == null) {
            System.out.println("Cannot find system property user home directory");
            return;
        }

        File appFolder = new File(userHome, DATA_FOLDER);

        // Create the directory if not exist
        if (!appFolder.exists()) {
            if (!appFolder.mkdirs()) {
                System.out.println("Cannot create data folder at " + appFolder.getAbsolutePath());
                return;
            }
            System.out.println("Created data folder at " + appFolder.getAbsolutePath());
        }
    }

    public static File getDataFolder() {
        String userHome = System.getProperty("user.home") + "/documents";
        if (userHome == null) {
            System.out.println("Cannot find system property user home directory");
            return null;
        }

        File dataFolder = new File(userHome, DATA_FOLDER);

        // Create the directory if not exist
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                System.out.println("Cannot create data folder at " + dataFolder.getAbsolutePath());
                return null;
            }
            System.out.println("Created data folder at " + dataFolder.getAbsolutePath());
        }
        if (!dataFolder.isDirectory() || !dataFolder.canWrite()) {
            System.out.println("Application data directory is not valid or not writable: " + dataFolder.getAbsolutePath());
            return null;
        }

        return dataFolder;
    }

    public static File getCharacterData() {
        File dataFolder = getDataFolder();
        if (dataFolder == null) {
            System.out.println("Cannot find system property data folder");
            return null;
        }
        return new File(dataFolder, DEFAULT_FILENAME);
    }

    // Save
    public void saveCharacterData(Character character) {
        File dataFile = getCharacterData();
        if (dataFile == null) {
            showErrorAlert("Save Error", "Could not determine save location.");
            return;
        }
        if (character == null) {
            showErrorAlert("Save Error", "character data is null.");
            return;
        }

        try {
            System.out.println("Saving character data to " + dataFile.getAbsolutePath());
            mapper.writeValue(dataFile, character);
            System.out.println("Saved character data to " + dataFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not save character data to " + dataFile.getAbsolutePath());
            e.printStackTrace();
            showErrorAlert("Save Error", "Could not write character data to file:\n" + e.getMessage());
        }
    }

    // Load
    public static Character loadCharacterData() {
        File dataFile = getCharacterData();
        if (dataFile == null) {
            showErrorAlert("Load Error", "Could not determine load location.");
            return null;
        }

        if (dataFile.exists() && dataFile.canRead() && dataFile.isFile()) {
            try {
                System.out.println("Loading character data from " + dataFile.getAbsolutePath());
                Character loadedCharacter = mapper.readValue(dataFile, Character.class);
                System.out.println("Loaded character data from " + dataFile.getAbsolutePath());
                return loadedCharacter;
            } catch (IOException e) {
                System.out.println("Could not load character data from " + dataFile.getAbsolutePath());
                e.printStackTrace();
                showErrorAlert(
                    "Load Error",
                    "Could not read or parse character data file:\n" +
                    e.getMessage() +
                    "\n\nA new character will be created."
                );
                return null;
            }
        } else {
            System.out.println("Character data file not found or not accessible: " + dataFile.getAbsolutePath());
            return null;
        }
    }

    public static void deleteCharacterData() {
        File dataFile = getCharacterData();
        if (dataFile == null) {
            showErrorAlert("Delete Error", "Could not determine delete location.");
            return;
        }

        if (dataFile.exists() && dataFile.canWrite() && dataFile.isFile()) {
            System.out.println("Deleting character data from " + dataFile.getAbsolutePath());
            dataFile.delete();
            System.out.println("Deleted character data from " + dataFile.getAbsolutePath());
        }
    }
}
