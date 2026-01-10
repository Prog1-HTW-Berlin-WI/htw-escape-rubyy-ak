package model;

import java.io.Serializable;

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

    // Name des Spielers
    private String name;

    // Standard-Konstruktor
    public Hero() {
    }

    // Getter für den Namen
    public String getName() {
        return name;
    }

    // Setter für den Namen
    public void setName(String name) {
        this.name = name;
    }
}