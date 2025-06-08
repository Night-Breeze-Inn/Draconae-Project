package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classes {

    private String name;

    @JsonProperty("hit_die")
    private int hitDie;

    @JsonProperty("proficiencies")
    private List<ApiReference> proficiencies;

    @JsonProperty("saving_throws")
    private List<ApiReference> savingThrows;

//     @JsonProperty("proficiency_choices")
//     private List<ProficiencyChoice> proficiencyChoices;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitDie() {
        return hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    public List<ApiReference> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(List<ApiReference> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public List<ApiReference> getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(List<ApiReference> savingThrows) {
        this.savingThrows = savingThrows;
    }

    @Override
    public String toString() {
        return name;
    }
}