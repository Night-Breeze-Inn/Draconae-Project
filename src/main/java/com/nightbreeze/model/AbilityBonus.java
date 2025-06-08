package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityBonus {

    @JsonProperty("ability_score")
    private ApiReference abilityScore;

    @JsonProperty("bonus")
    private int bonus;

    // Getters and Setters
    public ApiReference getAbilityScore() { return abilityScore; }
    public void setAbilityScore(ApiReference abilityScore) { this.abilityScore = abilityScore; }
    public int getBonus() { return bonus; }
    public void setBonus(int bonus) { this.bonus = bonus; }
}