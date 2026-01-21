package model;

import java.io.Serializable;

/**
 * Abstrakte Basisklasse für Aliens im Spiel
 * @author ruby
 * @author onur
 */
public abstract class Alien implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;

    // Attribute des Aliens
    private String name;
    private int lifePoints;
    private boolean friendly;
    private String greeting;


    // Getter
    public String getName() {
        return this.name;
    }

    public int getLifePoints() {
        return this.lifePoints;
    }

    public boolean getFriendly() {
        return this.friendly;
    }

    public String getGreeting() {
        return this.greeting;
    }


    /**
     * Konstruktor für ein Alien.
     * 
     * @param name Name des Aliens
     * @param lifePoints Lebenspunkte des Aliens
     * @param friendly true, wenn freundlich sonst false
     * @param greeting Begrüßungstext
     */
    public Alien(String name, int lifePoints, boolean friendly, String greeting) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
        this.greeting = greeting;
    }


    /**
     * Reduziert die Lebenspunkte des Aliens um den angegebenen Schadenswert.
     * Lebenspunkte dürfen nicht unter 0 fallen.
     * Es wird ausgegeben, wir viel Schaden verursacht wurde und wie viele Lebenspunkte noch verbleiben.
     * 
     * @param amount Schadenswert
     */
    public void takeDamage(int amount) {
        lifePoints -= amount;

        if (lifePoints < 0) {
            lifePoints = 0;
        }

        System.out.println(name + " took " + amount + " damage. ");
        System.out.println("Remaining life points: " + lifePoints);
    }


    /**
     * Prüft, ob das Alien besiegt ist.
     * 
     * @return true, wenn lifePoints <= 0, sonst false
     */
    public boolean isDefeated() {
        return lifePoints <= 0;
    }

}