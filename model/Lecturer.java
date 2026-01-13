package model;

import java.io.Serializable;

/**
 * Repräsentiert einen Dozenten im Spiel
 * @author ruby
 * @author onur
 */
public class Lecturer implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;

    // Attribute
    private String name;
    private boolean hasSigned;

    /**
     * Konstruktor
     * 
     * @param name Name des Lecturers
     */
    public Lecturer(String name) {
        this.name = name;
        this.hasSigned = false;
    }

    // Getter
    /**
     * Gibt den Namen des Lecturers zurück.
     * 
     * @return Name des Lecturers
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt zurück, ob der Lecturer bereits unterschrieben hat.
     * 
     * @return true, wenn der Lecturer unterschrieben hat, sonst false
     */
    public boolean hasSigned() {
        return this.hasSigned;
    }

    /**
     * Gibt an, ob der Lecturer bereit ist, den Laufzettel zu unterschreiben.
     * 
     * @return true, wenn der Lecturer noch nicht unterschrieben hat
     */
    public boolean isReadyToSign() {
        return !hasSigned;
    }

    /**
     * Unterschreibt den Laufzettel.
     */
    public void sign() {
        if (!hasSigned) {
            hasSigned = true;
            System.out.println(name + " has signed your sheet.");
        }
    }
}
