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
    private void showStoryIntro() {
        System.out.println("============================================================");
        System.out.println("THE AWAKENING – JANUARY 5th, 2026");
        System.out.println("============================================================");
        System.out.println("The alarm goes off. You pack your bag and head to HTW.");
        System.out.println("At the station, you grab your first coffee of the day, still half asleep.");
        System.out.println();
        System.out.println("Lost in thought, you enter Building A.");
        System.out.println("Something feels wrong. The air is heavy. Too heavy.");
        System.out.println("");
        System.out.println("From the shadows, something moves."); 
        System.out.println("A human-sized snail crawls toward you.");
        System.out.println();
        System.out.println("You stumble back in shock, spilling your hot coffee over yourself.");
        System.out.println("Pain flashes through your body.");
        System.out.println();
        System.out.println("You turn and run for the exit.");
        System.out.println("The door slams shut. Hermetically sealed.");
        System.out.println();
        System.out.println("On the glass, written in crooked letters:");
        System.out.println("\"NO WAY OUT.\"");
        System.out.println();
        System.out.println("Your heart races. The snail is getting closer.");  
        System.out.println("You glance at your bag. Could anything inside save you?");
        System.out.println("The last drops of coffee in your cup… could they be a weapon?");
        System.out.println();
        System.out.println("You clutch your pencil tightly.");
        System.out.println("It might be the only thing standing between you and death.");
        System.out.println("As you prepare yourself, the creature tilts its head and says:");
        System.out.println();
        System.out.println("\"Excuse me, do you happen to know where I can find...\"");
        System.out.println();
        System.out.println("____________________");
        System.out.println();
        System.out.println("What do you want to do?");
        System.out.println();
        System.out.println("(1) Talk to the snail");
        System.out.println("(2) Attack the snail");
    }
    /**
     * Gibt den Helden des Spiels zurück.
     * @return Held des Spielers
     */
    public Hero getHero() {
        return hero;
    }
}
