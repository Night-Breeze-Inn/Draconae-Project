package com.nightbreeze.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javafx.beans.property.*;

public class Character {

    // Basic Information
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

    // Hit Points
    private final IntegerProperty maxHP = new SimpleIntegerProperty();
    private final IntegerProperty currentHP = new SimpleIntegerProperty();
    private final IntegerProperty temporaryHP = new SimpleIntegerProperty();
    private final StringProperty hitDice = new SimpleStringProperty();

    // Abilities
    private final IntegerProperty strength = new SimpleIntegerProperty();
    private final IntegerProperty dexterity = new SimpleIntegerProperty();
    private final IntegerProperty constitution = new SimpleIntegerProperty();
    private final IntegerProperty intelligence = new SimpleIntegerProperty();
    private final IntegerProperty wisdom = new SimpleIntegerProperty();
    private final IntegerProperty charisma = new SimpleIntegerProperty();

    // Customisation Options
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty height = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty eyes = new SimpleStringProperty();
    private final StringProperty skin = new SimpleStringProperty();
    private final StringProperty hair = new SimpleStringProperty();

    // Features, Traits and Other Info
    private final List<String> language = new ArrayList<>();
    private final List<String> racialTraits = new ArrayList<>();
    private final List<String> features = new ArrayList<>();
    private Hashtable<String, ArrayList<String>> proficiency = new Hashtable<>();

    // Constructor
    public Character() {}

    // JavaFX Properties
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

    public StringProperty ageProperty() {
        return age;
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

    public IntegerProperty speedProperty() {
        return speed;
    }

    // Getters & Setters -- Basic Info
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

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public String getPlayerName() {
        return playerName.get();
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

    public int getLevel() {
        return classLevel.get();
    }

    public void setLevel(int level) {
        this.classLevel.set(level);
    }

    public String getBackground() {
        return background.get();
    }

    public void setBackground(String background) {
        this.background.set(background);
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

    // Getter & Setters -- Inspiration and Spellcasting
    public boolean getInspiration() {
        return inspiration.get();
    }

    public void setInspiration(boolean inspiration) {}

    public boolean getIsSpellCasting() {
        return isSpellCasting.get();
    }

    public void setIsSpellCasting(boolean isSpellCasting) {}

    // Getters & Setters -- Max Hit Points
    public int getMaxHP() {
        return maxHP.get();
    }

    public void setMaxHP(int maxHP) {
        this.maxHP.set(maxHP);
    }

    // Getters & Setters -- Current Hit Points
    public int getCurrentHP() {
        return currentHP.get();
    }

    public void setCurrentHP(int hp) {
        currentHP.set(Math.min(hp, maxHP.get()));
        if (currentHP.get() < 0) {
            currentHP.set(0);
        }
    }

    // Getters & Setters -- Temporary Hit Points
    public int getTemporaryHP() {
        return temporaryHP.get();
    }

    public void setTemporaryHP(int hp) {
        temporaryHP.set(Math.max(0, hp));
    }

    // Getters & Setters -- HitDice
    public String getHitDice() {
        return hitDice.get();
    }

    public void setHitDice(String hitDice) {
        this.hitDice.set(hitDice);
    }

    // Getters & Setters -- Ability
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

    // Getters & Setters -- Customisation Options
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

    // Getters & Setters -- Features, Traits and Other Info
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

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features.clear();
        if (features != null) {
            this.features.addAll(features);
        }
    }

    public Hashtable<String, ArrayList<String>> getProficiency() {
        return proficiency;
    }

    public void setProficiency(Hashtable<String, ArrayList<String>> proficiency) {
        this.proficiency = proficiency;
    }

    // Getters -- Ability Modifier
    @JsonIgnore
    public int getStrengthModifier() {
        return Math.floorDiv(getStrength() - 10, 2);
    }

    @JsonIgnore
    public int getDexterityModifier() {
        return Math.floorDiv(getDexterity() - 10, 2);
    }

    @JsonIgnore
    public int getConstitutionModifier() {
        return Math.floorDiv(getConstitution() - 10, 2);
    }

    @JsonIgnore
    public int getIntelligenceModifier() {
        return Math.floorDiv(getIntelligence() - 10, 2);
    }

    @JsonIgnore
    public int getWisdomModifier() {
        return Math.floorDiv(getWisdom() - 10, 2);
    }

    @JsonIgnore
    public int getCharismaModifier() {
        return Math.floorDiv(getCharisma() - 10, 2);
    }

    @Override
    public String toString() {
        return (
            "Character Name: " +
            getName() +
            "\n" +
            "\tPlayer Name: " +
            getPlayerName() +
            "\tClass: " +
            getClassName() +
            "\tLevel: " +
            getLevel() +
            "\n" +
            "\tSpecies: " +
            getRace() +
            "\n" +
            "\tSubSpecies: " +
            getSubRace() +
            "\n" +
            "\tAbilities: " +
            "\n\t\tStrength: \t" +
            getStrength() +
            "\n\t\tDexterity: \t" +
            getDexterity() +
            "\n\t\tConstitution: \t" +
            getConstitution() +
            "\n\t\tIntelligence: \t" +
            getIntelligence() +
            "\n\t\tWisdom: \t" +
            getWisdom() +
            "\n\t\tCharisma: \t" +
            getCharisma() +
            "\n" +
            "\tHit Points: " +
            getMaxHP() +
            "\n" +
            "\tHit Dice: " +
            getHitDice() +
            "\n" +
            "\tBackground:" +
            getBackground() +
            "\n" +
            "\tAlignment:" +
            getAlignment() +
            "\n" +
            "\tSpeed: " +
            getSpeed() +
            "\n" +
            "\tLanguages: " +
            getLanguage().toString() +
            "\n" +
            "\tSpellcaster: " +
            getIsSpellCasting()
        );
    }

    // Methods

    // Take Damage
    public void damage(int damageValue) {
        if (damageValue <= 0) return;

        int tempDamage = Math.min(damageValue, getTemporaryHP());
        setTemporaryHP(getCurrentHP() - tempDamage);

        int remainDamage = damageValue - tempDamage;
        if (remainDamage > 0) {
            setCurrentHP(getCurrentHP() - remainDamage);
        }
    }

    // Receive healing
    public void heal(int healValue) {
        if (healValue <= 0) return;
        setCurrentHP(Math.min(getMaxHP(), getCurrentHP() + healValue));
    }

    // Receive temporary hit points
    public void temporaryHP(int temporaryHPValue) {
        if (temporaryHPValue <= 0) return;

        setTemporaryHP(getTemporaryHP() + temporaryHPValue);
    }
}
