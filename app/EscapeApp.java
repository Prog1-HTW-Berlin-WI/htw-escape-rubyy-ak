package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Hauptklasse des Escape-Spiels.
 * Zeigt das Hauptmenuü des Spiels an.
 * @author onur
 * @author ruby
 */
public class EscapeApp {
   
    /** Name der Datei, die das Spiel speichert. */
    public static final String SAVE_FILE_NAME = "save";
   
    /** Das aktuelle Spiel. */
    private EscapeGame game;
   
    /** Gibt an, ob das Spiel läuft. */
    private static boolean gameRunning = true;

    /** Startet das Spiel und zeigt das Hauptmenü an. */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (gameRunning) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /** Gibt das Hauptmenü des Spiels auf der Konsole aus. */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");
        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /** Liest die Benutzereingabe ein. */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }
    /** Verarbeitet hier die Benutzereingabe. */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startGame();
                break;
            case "2":
                break;
            // ...
            case "6":
                gameRunning = false;
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /** Startet ein neues Spiel. */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /** Setzt das aktuelle Spiel fort. */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /** Löscht den gespeicherten Spielstand. */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /** Speichert das aktuelle Spiel. */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /** Lädt ein gespeichertes Spiel. */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }
    /** Prüft, ob das Spiel läuft. */
    private boolean isGameRunning() {
        return game != null;
    }
    /** Prüft, ob das Spiel zu Ende ist. */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }
    /** Prüft, ob ein Spiel gespeichert ist. */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
