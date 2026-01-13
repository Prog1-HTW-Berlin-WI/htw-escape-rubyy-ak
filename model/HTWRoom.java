package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Repräsentiert einen Raum der HTW im Spiel
 * @author ruby
 * @author onur
 */
public class HTWRoom implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 9065680017147292999L;

    //Attribute
    private String identifier;
    private String description;
    private Lecturer lecturer;

    /**
     * Konstruktor für einen Raum mit Lecturer.
     * 
     * @param identifier Eindeutiger Bezeichner des Raumes (z.B. A210)
     * @param description Beschreibung des Raums - Text beim Betreten ausgegeben
     * @param lecturer Lecturer der sich eventuell in dem Raum befindet
     */
    public HTWRoom(String identifier, String description, Lecturer lecturer) {
        this.identifier = identifier;
        this.description = description;
        this.lecturer = lecturer;
    }

    /**
     * Gibt den Bezeichner des Raums zurück.
     * 
     * @return Identifier des Raums
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gibt die Beschreibung des Raums zurück.
     * 
     * @return Beschreibung des Raums
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gibt den Lecturer im Raum zurück.
     * 
     * @return Lecturer im Raum oder null, falls keiner vorhanden ist
     */
    public Lecturer getLecturer(){
        return lecturer;
    }

    /**
     * Prüft, ob sich ein Lecturer im Raum befindet.
     * 
     * @return true, wenn ein Lecturer im Raum ist, sonst false
     */
    public boolean hasLecturer() {
        return lecturer != null;
    }
}