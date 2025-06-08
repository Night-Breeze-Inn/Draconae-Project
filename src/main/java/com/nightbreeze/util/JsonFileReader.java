package com.nightbreeze.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nightbreeze.model.Classes;
import com.nightbreeze.model.Species;
import com.nightbreeze.model.Subrace;

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
                List<Species> speciesList = mapper.readValue(inputStream, new TypeReference<List<Species>>() {
                });
                System.out.println("Successfully loaded " + speciesList.size() + " species.");
                return speciesList;

            } else if (fileName.equalsIgnoreCase("classes")) {
                List<Classes> classesList = mapper.readValue(inputStream, new TypeReference<List<Classes>>() {
                });
                System.out.println("Successfully loaded " + classesList.size() + " classes.");
                return classesList;
            } else if (fileName.equalsIgnoreCase("subraces")) {
                List<Subrace> subraceList = mapper.readValue(inputStream, new TypeReference<List<Subrace>>() {
                });
                System.out.println("Successfully loaded " + subraceList.size() + " subraces.");
                return subraceList;
            } else {
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