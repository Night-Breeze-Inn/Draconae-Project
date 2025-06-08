package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Species {

    private String name;
    private int speed;
    private String size;

    @JsonProperty("ability_bonuses")
    private List<AbilityBonus> abilityBonuses;

    @JsonProperty("starting_proficiencies")
    private List<ApiReference> startingProficiencies;

    @JsonProperty("languages")
    private List<ApiReference> languages;

    @JsonProperty("traits")
    private List<ApiReference> traits;

    @JsonProperty("subraces")
    private List<ApiReference> subraces;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public List<AbilityBonus> getAbilityBonuses() { return abilityBonuses; }
    public void setAbilityBonuses(List<AbilityBonus> abilityBonuses) { this.abilityBonuses = abilityBonuses; }
    public List<ApiReference> getStartingProficiencies() { return startingProficiencies; }
    public void setStartingProficiencies(List<ApiReference> startingProficiencies) { this.startingProficiencies = startingProficiencies; }
    public List<ApiReference> getLanguages() { return languages; }
    public void setLanguages(List<ApiReference> languages) { this.languages = languages; }
    public List<ApiReference> getTraits() { return traits; }
    public void setTraits(List<ApiReference> traits) { this.traits = traits; }
    public List<ApiReference> getSubraces() { return subraces; }
    public void setSubraces(List<ApiReference> subraces) { this.subraces = subraces; }

    @Override
    public String toString() {
        return name;
    }
}