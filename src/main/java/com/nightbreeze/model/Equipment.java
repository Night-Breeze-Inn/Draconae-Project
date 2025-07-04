package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Equipment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Equipment {

    private String name;

    @JsonProperty("equipment_category")
    private ApiReference category;

    // Common fields
    private double weight;

    // Weapon-specific fields
    @JsonProperty("weapon_category")
    private String weaponCategory;

    @JsonProperty("weapon_range")
    private String weaponRange;

    @JsonProperty("damage")
    private Damage damage;

    // Armor-specific fields
    @JsonProperty("armor_category")
    private String armorCategory;

    @JsonProperty("armor_class")
    private ArmorClass armorClass;

    @JsonProperty("str_minimum")
    private int strengthMinimum;

    @JsonProperty("stealth_disadvantage")
    private boolean stealthDisadvantage;


    // --- Nested static classes for complex fields ---
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Damage {
        @JsonProperty("damage_dice")
        public String damageDice;
        @JsonProperty("damage_type")
        public ApiReference damageType;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArmorClass {
        public int base;
        @JsonProperty("dex_bonus")
        public boolean dexBonus;
        @JsonProperty("max_bonus")
        public Integer maxBonus;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ApiReference getCategory() { return category; }
    public void setCategory(ApiReference category) { this.category = category; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getWeaponCategory() { return weaponCategory; }
    public void setWeaponCategory(String weaponCategory) { this.weaponCategory = weaponCategory; }
    public String getWeaponRange() { return weaponRange; }
    public void setWeaponRange(String weaponRange) { this.weaponRange = weaponRange; }
    public Damage getDamage() { return damage; }
    public void setDamage(Damage damage) { this.damage = damage; }
    public String getArmorCategory() { return armorCategory; }
    public void setArmorCategory(String armorCategory) { this.armorCategory = armorCategory; }
    public ArmorClass getArmorClass() { return armorClass; }
    public void setArmorClass(ArmorClass armorClass) { this.armorClass = armorClass; }
    public int getStrengthMinimum() { return strengthMinimum; }
    public void setStrengthMinimum(int strengthMinimum) { this.strengthMinimum = strengthMinimum; }
    public boolean isStealthDisadvantage() { return stealthDisadvantage; }
    public void setStealthDisadvantage(boolean stealthDisadvantage) { this.stealthDisadvantage = stealthDisadvantage; }

    @Override
    public String toString() {
        return name;
    }
}