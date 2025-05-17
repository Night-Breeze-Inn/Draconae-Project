package com.nightbreeze.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nightbreeze.model.ClassesListWrapper;
import com.nightbreeze.model.SpeciesListWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class JsonFileReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List readJsonDataFile(String fileName) {
        String resourcePath = "/com/nightbreeze/data/" + fileName + ".json";

        try (InputStream inputStream = JsonFileReader.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.err.println("Cannot find resource file: " + resourcePath);
                return Collections.emptyList();
            }

            if (fileName.equalsIgnoreCase("species")) {
                SpeciesListWrapper wrapper = mapper.readValue(inputStream, SpeciesListWrapper.class);
                if (wrapper != null && wrapper.getSpecies() != null) {
                    System.out.println("Successfully loaded " + wrapper.getSpecies().size() + " species.");
                    return wrapper.getSpecies();
                } else {
                    System.err.println("Species data is null or empty in the JSON file: " + resourcePath);
                    return Collections.emptyList();
                }
            } else if (fileName.equalsIgnoreCase("classes")) {
                ClassesListWrapper wrapper = mapper.readValue(inputStream, ClassesListWrapper.class);
                if (wrapper != null && wrapper.getClasses() != null) {
                    System.out.println("Successfully loaded " + wrapper.getClasses().size() + " classes.");
                    return wrapper.getClasses();
                } else {
                    System.err.println("Classes data is null or empty in the JSON file: " + resourcePath);
                    return Collections.emptyList();
                }
            }
            //            else-if (fileName.equalsIgnoreCase("armors")) {}
            //            else-if (fileName.equalsIgnoreCase("weapons")) {}
            else {
                System.err.println("Unsupported data file type requested: " + fileName);
                return Collections.emptyList();
            }
        } catch (IOException e) {
            System.err.println("Error reading or parsing " + fileName + " JSON file: " + resourcePath);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
