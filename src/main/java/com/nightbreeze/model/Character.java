package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nightbreeze.util.Utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javafx.beans.property.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("Character")
public class Character {

    @JsonIgnore
    private static final int[] XP_THRESHOLDS = {0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000, 85000, 100000, 120000, 140000, 165000, 195000, 225000, 265000, 305000, 355000};

    // --- All original properties ---
    private final StringProperty characterName = new SimpleStringProperty();
    private final StringProperty className = new SimpleStringProperty();
    private final IntegerProperty classLevel = new SimpleIntegerProperty(1);
    private final StringProperty background = new SimpleStringProperty();
    private final StringProperty playerName = new SimpleStringProperty();
    private final StringProperty race = new SimpleStringProperty();
    private final StringProperty subRace = new SimpleStringProperty();
    private final StringProperty alignment = new SimpleStringProperty();
    private final IntegerProperty experiencePoints = new SimpleIntegerProperty(0);
    private final IntegerProperty speed = new SimpleIntegerProperty();
    private final BooleanProperty inspiration = new SimpleBooleanProperty(false);
    private final BooleanProperty isSpellCasting = new SimpleBooleanProperty(false);
    private final IntegerProperty maxHP = new SimpleIntegerProperty();
    private final IntegerProperty currentHP = new SimpleIntegerProperty();
    private final IntegerProperty temporaryHP = new SimpleIntegerProperty();
    private final StringProperty hitDice = new SimpleStringProperty();
    private final IntegerProperty strength = new SimpleIntegerProperty();
    private final IntegerProperty dexterity = new SimpleIntegerProperty();
    private final IntegerProperty constitution = new SimpleIntegerProperty();
    private final IntegerProperty intelligence = new SimpleIntegerProperty();
    private final IntegerProperty wisdom = new SimpleIntegerProperty();
    private final IntegerProperty charisma = new SimpleIntegerProperty();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty height = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty eyes = new SimpleStringProperty();
    private final StringProperty skin = new SimpleStringProperty();
    private final StringProperty hair = new SimpleStringProperty();
    private final List<String> language = new ArrayList<>();
    private final List<String> racialTraits = new ArrayList<>();
    private final List<String> features = new ArrayList<>();
    private Hashtable<String, ArrayList<String>> proficiency = new Hashtable<>();

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    private List<Equipment> inventory = new ArrayList<>();

    @JsonIgnore
    private Equipment equippedArmor;

    private String equippedArmorName;

    // --- JavaFX Properties ---
    public StringProperty CharacterNameProperty() {
        return characterName;
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public StringProperty raceProperty() {
        return race;
    }

    public IntegerProperty classLevelProperty() {
        return classLevel;
    }

    public StringProperty backgroundProperty() {
        return background;
    }

    public StringProperty playerNameProperty() {
        return playerName;
    }

    public StringProperty alignmentProperty() {
        return alignment;
    }

    public IntegerProperty speedProperty() {
        return speed;
    }

    public BooleanProperty inspirationProperty() {
        return inspiration;
    }

    public BooleanProperty isSpellCastingProperty() {
        return isSpellCasting;
    }

    public StringProperty heightProperty() {
        return height;
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public StringProperty eyesProperty() {
        return eyes;
    }

    public StringProperty skinProperty() {
        return skin;
    }

    public StringProperty hairProperty() {
        return hair;
    }

    public StringProperty hitDiceProperty() {
        return hitDice;
    }

    public StringProperty ageProperty() {
        return age;
    }

    public IntegerProperty experiencePointsProperty() {
        return experiencePoints;
    }

    public IntegerProperty maxHPProperty() {
        return maxHP;
    }

    public IntegerProperty currentHPProperty() {
        return currentHP;
    }

    public IntegerProperty temporaryHPProperty() {
        return temporaryHP;
    }

    public IntegerProperty strengthProperty() {
        return strength;
    }

    public IntegerProperty dexterityProperty() {
        return dexterity;
    }

    public IntegerProperty constitutionProperty() {
        return constitution;
    }

    public IntegerProperty intelligenceProperty() {
        return intelligence;
    }

    public IntegerProperty wisdomProperty() {
        return wisdom;
    }

    public IntegerProperty charismaProperty() {
        return charisma;
    }

    public String getName() {
        return characterName.get();
    }

    public void setName(String name) {
        this.characterName.set(name);
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public int getLevel() {
        return classLevel.get();
    }

    public void setLevel(int level) {
        this.classLevel.set(level);
    }

    public String getRace() {
        return race.get();
    }

    public void setRace(String race) {
        this.race.set(race);
    }

    public String getSubRace() {
        return subRace.get();
    }

    public void setSubRace(String subRace) {
        this.subRace.set(subRace);
    }

    public int getExperiencePoints() {
        return experiencePoints.get();
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints.set(experiencePoints);
    }

    public int getMaxHP() {
        return maxHP.get();
    }

    public void setMaxHP(int maxHP) {
        this.maxHP.set(maxHP);
    }

    public int getCurrentHP() {
        return currentHP.get();
    }

    public void setCurrentHP(int hp) {
        currentHP.set(Math.min(hp, maxHP.get()));
        if (currentHP.get() < 0) {
            currentHP.set(0);
        }
    }

    public int getTemporaryHP() {
        return temporaryHP.get();
    }

    public void setTemporaryHP(int hp) {
        temporaryHP.set(Math.max(0, hp));
    }

    public int getStrength() {
        return strength.get();
    }

    public void setStrength(int score) {
        this.strength.set(score);
    }

    public int getDexterity() {
        return dexterity.get();
    }

    public void setDexterity(int score) {
        this.dexterity.set(score);
    }

    public int getConstitution() {
        return constitution.get();
    }

    public void setConstitution(int score) {
        this.constitution.set(score);
    }

    public int getIntelligence() {
        return intelligence.get();
    }

    public void setIntelligence(int score) {
        this.intelligence.set(score);
    }

    public int getWisdom() {
        return wisdom.get();
    }

    public void setWisdom(int score) {
        this.wisdom.set(score);
    }

    public int getCharisma() {
        return charisma.get();
    }

    public void setCharisma(int score) {
        this.charisma.set(score);
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language.clear();
        if (language != null) {
            this.language.addAll(language);
        }
    }

    public List<String> getRacialTraits() {
        return racialTraits;
    }

    public void setRacialTraits(List<String> traits) {
        this.racialTraits.clear();
        if (traits != null) {
            this.racialTraits.addAll(traits);
        }
    }

    public Hashtable<String, ArrayList<String>> getProficiency() {
        return proficiency;
    }

    public void setProficiency(Hashtable<String, ArrayList<String>> proficiency) {
        this.proficiency = proficiency;
    }

    public List<Equipment> getInventory() {
        return inventory;
    }

    public void setInventory(List<Equipment> inventory) {
        this.inventory = inventory;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features.clear();
        if (features != null) {
            this.features.addAll(features);
        }
    }

    public String getCharacterName() {
        return characterName.get();
    }

    public void setCharacterName(String characterName) {
        this.characterName.set(characterName);
    }

    public String getClassNane() {
        return className.get();
    }

    public void setClassNane(String classNane) {
        this.className.set(classNane);
    }

    public int getClassLevel() {
        return classLevel.get();
    }

    public void setClassLevel(int classLevel) {
        this.classLevel.set(classLevel);
    }

    public String getBackground() {
        return background.get();
    }

    public void setBackground(String background) {
        this.background.set(background);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public String getAlignment() {
        return alignment.get();
    }

    public void setAlignment(String alignment) {
        this.alignment.set(alignment);
    }

    public int getSpeed() {
        return speed.get();
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    public boolean isInspiration() {
        return inspiration.get();
    }

    public void setInspiration(boolean inspiration) {
        this.inspiration.set(inspiration);
    }

    public boolean isSpellCasting() {
        return isSpellCasting.get();
    }

    public void setSpellCasting(boolean isSpellCasting) {
        this.isSpellCasting.set(isSpellCasting);
    }

    public String getHitDice() {
        return hitDice.get();
    }

    public void setHitDice(String hitDice) {
        this.hitDice.set(hitDice);
    }

    public String getAge() {
        return age.get();
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getHeight() {
        return height.get();
    }

    public void setHeight(String height) {
        this.height.set(height);
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public String getEyes() {
        return eyes.get();
    }

    public void setEyes(String eyes) {
        this.eyes.set(eyes);
    }

    public String getSkin() {
        return skin.get();
    }

    public void setSkin(String skin) {
        this.skin.set(skin);
    }

    public String getHair() {
        return hair.get();
    }

    public void setHair(String hair) {
        this.hair.set(hair);
    }

    public void addLanguage(String lang) {
        if (lang != null && !this.language.contains(lang)) {
            this.language.add(lang);
        }
    }

    public void removeLanguage(String lang) {
        if (lang != null) {
            this.language.remove(lang);
        }
    }

    public void addRacialTrait(String trait) {
        if (trait != null && !this.racialTraits.contains(trait)) {
            this.racialTraits.add(trait);
        }
    }

    public void removeRacialTrait(String trait) {
        if (trait != null) {
            this.racialTraits.remove(trait);
        }
    }

    public void addFeature(String feature) {
        if (feature != null && !this.features.contains(feature)) {
            this.features.add(feature);
        }
    }

    public void removeFeature(String feature) {
        if (feature != null) {
            this.features.remove(feature);
        }
    }

    @JsonIgnore
    public void setEquippedArmor(Equipment armor) {
        if (armor != null) {
            if (!this.inventory.contains(armor)) {
                System.err.println("Cannot equip armor that is not in the inventory.");
                return;
            }
            this.equippedArmor = armor;
            this.equippedArmorName = armor.getName();
        } else {
            this.equippedArmor = null;
            this.equippedArmorName = null;
        }
    }

    @JsonIgnore
    public Equipment getEquippedArmor() {
        return this.equippedArmor;
    }

    @JsonIgnore
    public int getArmorClass() {
        if (equippedArmor != null && equippedArmor.getArmorClass() != null) {
            Equipment.ArmorClass acDetails = equippedArmor.getArmorClass();
            int ac = acDetails.base;
            if (acDetails.dexBonus) {
                int dexMod = getDexterityModifier();
                if (acDetails.maxBonus != null) {
                    ac += Math.min(dexMod, acDetails.maxBonus);
                } else {
                    ac += dexMod;
                }
            }
            return ac;
        } else {
            return 10 + getDexterityModifier();
        }
    }

    @JsonIgnore
    public int getStrengthModifier() {
        return (getStrength() - 10) / 2;
    }

    @JsonIgnore
    public int getDexterityModifier() {
        return (getDexterity() - 10) / 2;
    }

    @JsonIgnore
    public int getConstitutionModifier() {
        return (getConstitution() - 10) / 2;
    }

    @JsonIgnore
    public int getIntelligenceModifier() {
        return (getIntelligence() - 10) / 2;
    }

    @JsonIgnore
    public int getWisdomModifier() {
        return (getWisdom() - 10) / 2;
    }

    @JsonIgnore
    public int getCharismaModifier() {
        return (getCharisma() - 10) / 2;
    }

    @JsonIgnore
    public int getProficiencyBonus() {
        int level = getLevel();
        if (level >= 17) {
            return 6;
        }
        if (level >= 13) {
            return 5;
        }
        if (level >= 9) {
            return 4;
        }
        if (level >= 5) {
            return 3;
        }
        return 2;
    }

    @JsonIgnore
    public static int getXpForLevel(int level) {
        if (level < 2 || level > 20) {
            return 0;
        }
        return XP_THRESHOLDS[level - 1];
    }

    @JsonIgnore
    public int getXpForNextLevel() {
        int currentLevel = getLevel();
        if (currentLevel < 20) {
            return XP_THRESHOLDS[currentLevel];
        }
        return getExperiencePoints();
    }

    @JsonIgnore
    public boolean checkForLevelUp() {
        int currentLevel = getLevel();
        if (currentLevel < 20 && getExperiencePoints() >= getXpForNextLevel()) {
            setLevel(currentLevel + 1);
            Utils.showInfoAlert("Level Up!", "Congratulations! You are now level " + getLevel() + "!");
            return true;
        }
        return false;
    }

    public void damage(int damageValue) {
        if (damageValue <= 0) {
            return;
        }
        int tempDamage = Math.min(damageValue, getTemporaryHP());
        setTemporaryHP(getTemporaryHP() - tempDamage);
        int remainDamage = damageValue - tempDamage;
        if (remainDamage > 0) {
            setCurrentHP(getCurrentHP() - remainDamage);
        }
    }

    public void heal(int healValue) {
        if (healValue <= 0) {
            return;
        }
        setCurrentHP(Math.min(getMaxHP(), getCurrentHP() + healValue));
    }

    public void relinkEquippedItems() {
        if (equippedArmorName != null && !equippedArmorName.isEmpty()) {
            for (Equipment item : this.inventory) {
                if (item.getName().equals(equippedArmorName)) {
                    this.equippedArmor = item;
                    System.out.println("Relinked equipped armor: " + equippedArmorName);
                    break;
                }
            }
        }
    }

    public String getEquippedArmorName() {
        return equippedArmorName;
    }

    public void setEquippedArmorName(String equippedArmorName) {
        this.equippedArmorName = equippedArmorName;
    }
}
