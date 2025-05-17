package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subrace {

    private String name;
    private Map<String, Integer> abilityScoreIncrease;
    private List<Trait> traits;
    private Proficiency proficiency; // Subraces can also grant proficiencies

    // Getters and Setters...
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

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public Proficiency getProficiency() {
        return proficiency;
    }

    public void setProficiency(Proficiency proficiency) {
        this.proficiency = proficiency;
    }

    @Override
    public String toString() {
        // Useful for debugging and potentially ComboBox display
        return name;
    }
}
