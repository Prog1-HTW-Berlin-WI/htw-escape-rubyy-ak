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

    // Konstanten (feste Spielregeln)
    private static final int MAX_HP = 50; // Maximale Lebenspunkte des Helden
    private static final int SIGNATURES = 5; // Maximale Anzahl Unterschriften


    // Attribute des Spielers
    private String name;
    private int healthPoints = MAX_HP; // Aktuelle Lebenspunkte
    private int experiencePoints = 0; // Aktuelle Erfahrungspunkte
    private Lecturer[] signExerciseLeaders; // Unterschriebene Übungsleiter

    private boolean shortRestUsed; // Ob kurze Verschnaufspause genutzt wurde
    
    private final Random random = new Random(); // Zufallsgenerator für Angriff/Flucht


    // Konstruktor (Startzustand des Helden)
    public Hero() {
        this.name = "";
        this.healthPoints = MAX_HP;
        this.experiencePoints = 0;
        this.signExerciseLeaders = new Lecturer[SIGNATURES];
        this.shortRestUsed = false;
    }


    // Getter und Setter
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    public Lecturer[] getSignedExerciseLeaders() {
        return this.signExerciseLeaders;
    }

    /** 
     * Reduziert die Lebenspunkte um den angegebenen Wert.
     * Die Lebenspunkte dürfen nicht unter 0 fallen.
     */
    public void takeDamage(int amount) {
        this.healthPoints -= amount;
        if (this.healthPoints < 0) { // Verhindert negative Lebenspunkte
            this.healthPoints = 0;
        }
    }


    /**
     * Ermöglicht dem Hero eine Verschnaufspause
     * 
     * @param longRest true: große Verschnaufspause (+10HP, dauert eine komplette Runde)
     *                 false: kleine Verschnaufspause (+3 HP, nur einmal pro Runde)
     * 
     * Die Lebenspunkte dürfen MAX_HP nicht überschreiten.
     */
    public void regenerate(boolean longRest) {
        int amount;
        if (longRest) { 
            amount = 10;
        } else { // wenn Nutzer kurze Verschnaufpause wählt (longRest == false)
            if (shortRestUsed) { // prüft, ob in dieser Runde eine kleine Pause schon benutzt wurde
                System.out.println("Your already used your short rest this round.");
                return; 
        }
        shortRestUsed = true; // kurze Verschnaufpause wird jetzt genutzt
        amount = 3; // regeneriert 3 Lebenspunkte
        }

        healthPoints += amount;

        if (healthPoints > MAX_HP) { // Verhindert Überschreitung der maximalen Lebenspunkte
            healthPoints = MAX_HP;
        }
    }


    /**
     * Wird am Ende jeder Runde aufgerufen.
     * Setzt die kurze Verschnaufspause zurück.
     */
    public void startNewRound() {
        shortRestUsed = false; // kurze Verschnaufpause zurücksetzen
    }


    /**
     * Fluchtversuch mit 42% Erfolgswahrscheinlichkeit.
     * @return true, wenn die Flucht erfolgreich ist,
     *         false, wenn die Flucht ein misserfolg ist
     */
    public boolean flee() {
        return random.nextDouble() < 0.42;
    }


    /**
     * Angriff:
     * Grundschaden = experiencePoints * 2.3 +1
     * 13% Miss -> Schaden = 0
     * 12% Crit -> Schaden = Grundschaden * 2
     * @return den berechneten Schaden als int
     */
    public int attack() {
        double baseDamage = experiencePoints * 2.3 + 1;
        double chance = random.nextDouble(); // Zufallswert zwischen 0.0 und 1.0 (für Wahrscheinlichkeiten)

        if (chance < 0.13) { 
            return 0; // 13% Miss
        } else if (chance < 0.25) {
            return (int) Math.round(baseDamage * 2); // 12% Crit, Math.round rundet auf/ab
        } else {
            return (int) Math.round(baseDamage); // Normaler Schaden
        }
    }


    /**
     * Erhöht die Erfahrungspunkte um den angegebenen Wert.
     * 
     * @param amount Anzahl der Erfahrungspunkte, die hinzugefügt werden sollen
     */
    public void addExperiencePoints(int amount) {
        if (amount > 0) {
            experiencePoints += amount;
        }
    }


    /**
     * trägt einen Lecturer in den Laufzettel ein.
     * Jeder Lecturer darf nur einmal unterschreiben
     * 
     * @param lecturer der Lecturer, der unterschreiben soll
     */
    public void signExerciseLeader(Lecturer lecturer) {

        // Prüft, ob der Lecturer schon unterschrieben hat
        for (int i = 0; i < signExerciseLeaders.length; i++) { // Ganze Array durchgehen 
            if (signExerciseLeaders[i] == lecturer) {
                System.out.println("This lecturer has already signed your sheet.");
                return;
            }
        }

        // Ersten freien Platz im Laufzettel suchen
        for (int i = 0; i < signExerciseLeaders.length; i++) { // Ganze Array durchgehen 
            if (signExerciseLeaders[i] == null) { // Sucht den ersten freien Platz
                signExerciseLeaders[i] = lecturer; // dann hier eintragen
                System.out.println(lecturer.getName() + "has signed your sheet.");
                return;
            }
        }

        // Falls kein Platz mehr frei ist
        System.out.println("All signatures have already been collected."); // Vielleicht das einfügen damit der Spieler weiß, dass er bereits alle Unterschriften hat
    }


    /** 
     * Gibt an, ob der Hero noch handlungsfähig ist.
     * 
     * @return true, wenn healthPoints > 0
     */
    public boolean isOperational() {
        return healthPoints > 0;
    }
}