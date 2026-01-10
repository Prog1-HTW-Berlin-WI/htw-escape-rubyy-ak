package app;

import model.Hero;

import java.util.Scanner;

import model.HTWRoom;

/**
 * Klasse für den Spielablauf des Escape-Games.
 * @author ruby
 * @author onur
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
        System.out.println();
        System.out.println("The game has started. Or not?");
        System.out.println();
        System.out.println("========================================");
        System.out.println("CHOOSE YOUR NAME");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Before your journey at HTW begins...");
        System.out.println("What is your name, brave student?");
        System.out.println();
        System.out.print("Enter your name: ");
        
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine().trim();
        hero.setName(name);

        // Überleitung zum Intro
        System.out.println();
        System.out.println("________________________________________");
        System.out.println();
        System.out.println("Not everything is as it seems...");
        System.out.println("Look closer, " + hero.getName() + ".");
        System.out.println();
        System.out.println("(Press Enter to continue)");
        scanner.nextLine();
    }

    /**
     * Gibt den Helden des Spiels zurück.
     * @return Held des Spielers
     */
    public Hero getHero() {
        return hero;
    }
}
