package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Proficiency {

    private List<String> weapons;
    private List<String> tools;
    private List<String> armor;

    // Getters and Setters...
    public List<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<String> weapons) {
        this.weapons = weapons;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public List<String> getArmor() {
        return armor;
    }

    public void setArmor(List<String> armor) {
        this.armor = armor;
    }
}
