package app;

import model.Hero;
import model.HTWRoom;

/**
 * Klasse für den Spielablauf des Escape-Games.
 * @author ruby
 */

public class EscapeGame {
    /** Held des Spielers. */
    private final Hero hero;
    /** Räume des Spiels. */
    private final HTWRoom[] rooms = new HTWRoom[3];
    /** Gibt an, ob das Spiel läuft. */
    private boolean gameRunning = true;
    /** Gibt an, ob das Spiel beendet ist. */
    private boolean gameFinished = false;

    /**
     * Erstellt ein neues Spiel und initialisiert den Helden.
     */
    public EscapeGame() {
        this.hero = new Hero();
    }

    /**
     * Prüft, ob das Spiel läuft.
     * @return true, wenn das Spiel läuft
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Setzt den Status, ob das Spiel läuft.
     * @param gameRunning neuer Spielstatus
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Prüft, ob das Spiel beendet ist.
     * @return true, wenn das Spiel beendet ist
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Setzt den Status, ob das Spiel beendet ist.
     * @param gameFinished neuer Beendigungsstatus
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Startet den Spielablauf.
     */
    public void run() {
        System.out.println("The game has started. Or not?");
    }

    /**
     * Gibt den Helden des Spiels zurück.
     * @return Held des Spielers
     */
    public Hero getHero() {
        return hero;
    }
}
