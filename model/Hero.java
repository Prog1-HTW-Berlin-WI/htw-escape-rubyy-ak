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

    private static final int Max_HP = 50; // Maximale Lebenspunkte des Helden
    private static final int Signatures = 5; // Maximale Anzahl Unterschriften

    // Attribute des Spielers
    private String name;
    private int healthPoints = Max_HP; // Aktuelle Lebenspunkte
    private int experiencePoints = 0; // Aktuelle Erfahrungspunkte
    private Lecturer[] signedExerciseLeaders; // Unterschriebene Übungsleiter

    private boolean shortRestUsed; // Ob kurze Verschnaufspause genutzt wurde
    
    private final Random random = new Random(); // Zufallsgenerator für Angriff/Flucht

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
    public boolean flee() {
        return random.nextDouble() < 0.42; // 42% Chance zu entkommen
    }

    /** 
     * Angriff: Grundschaden = EP * 2,3 + 1
     * 13% Miss -> Schaden = 0
     * 12% Crit -> Schaden = Grundschaden * 2
     * Rückgabe als int. 
     */
    public int attack() {
        int baseDamage = (int) (experiencePoints * 2.3) + 1;
        double chance = random.nextDouble(); // Zufallswert zwischen 0.0 und 1.0

        if (chance < 0.13) { // 13% Miss
            return 0;
        } else if (chance < 0.25) { // 12% Crit
            return baseDamage * 2;
        } else {
            return baseDamage; // Normaler Schaden
        }
    }

    public void addExperiencePoints(int amount) {
        if (amount > 0) {
            experiencePoints += amount;
        }
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void signedExerciseLeaders(Lecturer lecturer) {
        for (int i = 0; i < signedExerciseLeaders.length; i++) { // Ganze Array durchgehen 
            if (signedExerciseLeaders[i] == lecturer) { // Prüft ob dieser Lecturer bereis unterschrieben hat
            }
        }
        for (int i = 0; i < signedExerciseLeaders.length; i++) { // Ganze Array durchgehen 
            if (signedExerciseLeaders[i] == null) { // Sucht den ersten freien Platz
                signedExerciseLeaders[i] = lecturer; // dann hier eintragen
                return; // Unterschrift erfolgreich hinzugefügt
            }
        }
        System.out.println("All signatures have already been collected."); // Vielleicht das einfügen damit der Spieler weiß, dass er bereits alle Unterschriften hat
    }

    public boolean isOperational() {
        return healthPoints > 0;
    }




}