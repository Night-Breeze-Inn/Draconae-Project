package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classes {

    @JsonProperty("name")
    private String className;

    @JsonProperty("hitDice")
    private String hitDice;

    @JsonProperty("hitPointsLevel1")
    private int hitPoints;

    @JsonProperty("proficiencies")
    private Proficiency proficiencies;

    @JsonProperty("features")
    private List<Features> features;

    // Getters & Setters
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHitDice() {
        return hitDice;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Proficiency getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(Proficiency proficiencies) {
        this.proficiencies = proficiencies;
    }

    public List<Features> getFeatures() {
        if (this.features == null) {
            this.features = new ArrayList<>();
        }
        return features;
    }

    public void setFeatures(List<Features> features) {
        if (this.features == null) {
            this.features = new ArrayList<>();
        } else {
            this.features.clear();
        }
        if (features != null) {
            this.features.addAll(features);
        }
    }

    @Override
    public String toString() {
        return className;
    }

    public List<String> getProficienciesArmor() {
        return this.proficiencies.getArmor();
    }

    public List<String> getProficienciesTools() {
        return this.proficiencies.getTools();
    }

    public List<String> getProficienciesWeapons() {
        return this.proficiencies.getWeapons();
    }
}
