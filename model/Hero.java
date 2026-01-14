package model;

import java.io.Serializable;
import java.util.Random;

/**
 * Repräsentiert die Spielfigur (Hero) im Spiel
 * @author ruby
 * @author onur
 */
public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    // Maximale Lebenspunkte des Helden
    private static final int Max_HP = 50;
    private static final int Signatures = 5; //

    // Name des Spielers
    private String name;

    // Attribute des Helden
    private int healthPoints = Max_HP; // Aktuelle Lebenspunkte
    private int experiencePoints = 0; // Aktuelle Erfahrungspunkte
    private Lecturer[] signedExerciseLeaders; // Unterschriebene Übungsleiter

    private boolean shortRestUsed; // Ob kurze Verschnaufspause genutzt wurde
    
    private final Random random = new Random(); // Zufallsgenerator

    // Konstruktor
    public Hero() {
        this.name = "";
        this.healthPoints = Max_HP;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[Signatures];
        this.shortRestUsed = false;
    }

    // Getter für den Namen
    public String getName() {
        return name;
    }

    // Setter für den Namen
    public void setName(String name) {
        this.name = name;
    }

    public void takeDamage(int amount) {
        this.healthPoints -= amount;
        if (this.healthPoints < 0) { // Verhindert negative Lebenspunkte
            this.healthPoints = 0;
        }
    }

    public void regenerate(boolean longRest) {
        int amount;
        if (longRest) { 
            amount = 10; // verbraucht eine ganze Runde
        } else { // wenn Nutzer kurze Verschnaufpause wählt
            if (shortRestUsed) {
                return; // kurze Verschnaufpause bereits genutzt -> abbruch
        }
        shortRestUsed = true; // kurze Verschnaufpause wird genutzt
        amount = 3;
        }

        healthPoints += amount;
        if (healthPoints > Max_HP) { // Verhindert Überschreitung der maximalen Lebenspunkte
            healthPoints = Max_HP;
        }
    }

    public void StartNewRound() {
        shortRestUsed = false; // kurze Verschnaufpause zurücksetzen
    }

}