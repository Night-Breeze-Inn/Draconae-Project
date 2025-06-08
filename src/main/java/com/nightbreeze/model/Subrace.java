// src/main/java/com/nightbreeze/model/Subrace.java
package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subrace {

    private String name;

    @JsonProperty("race")
    private ApiReference parentRace;

    @JsonProperty("desc")
    private String description;

    @JsonProperty("ability_bonuses")
    private List<AbilityBonus> abilityBonuses;

    @JsonProperty("starting_proficiencies")
    private List<ApiReference> startingProficiencies;

    @JsonProperty("racial_traits")
    private List<ApiReference> racialTraits;

    // Getters and Setters...
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiReference getParentRace() {
        return parentRace;
    }

    public void setParentRace(ApiReference parentRace) {
        this.parentRace = parentRace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AbilityBonus> getAbilityBonuses() {
        return abilityBonuses;
    }

    public void setAbilityBonuses(List<AbilityBonus> abilityBonuses) {
        this.abilityBonuses = abilityBonuses;
    }

    public List<ApiReference> getStartingProficiencies() {
        return startingProficiencies;
    }

    public void setStartingProficiencies(List<ApiReference> startingProficiencies) {
        this.startingProficiencies = startingProficiencies;
    }

    public List<ApiReference> getRacialTraits() {
        return racialTraits;
    }

    public void setRacialTraits(List<ApiReference> racialTraits) {
        this.racialTraits = racialTraits;
    }

    @Override
    public String toString() {
        return name;
    }
}