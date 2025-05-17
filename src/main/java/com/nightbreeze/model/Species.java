package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Species {

    private String name;
    private Map<String, Integer> abilityScoreIncrease;
    private String size;
    private int speed;
    private List<Trait> traits = new ArrayList<>();
    private List<Language> languages = new ArrayList<>();
    private List<Subrace> subraces = new ArrayList<>();

    // Getters and Setters for all fields...
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getAbilityScoreIncrease() {
        return abilityScoreIncrease;
    }

    public void setAbilityScoreIncrease(Map<String, Integer> abilityScoreIncrease) {
        this.abilityScoreIncrease = abilityScoreIncrease;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = (traits != null) ? traits : new ArrayList<>();
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = (languages != null) ? languages : new ArrayList<>();
    }

    public List<Subrace> getSubraces() {
        return subraces;
    }

    public void setSubraces(List<Subrace> subraces) {
        this.subraces = (subraces != null) ? subraces : new ArrayList<>();
    }

    @Override
    public String toString() {
        // Useful for debugging and potentially ComboBox display
        return name;
    }
}
