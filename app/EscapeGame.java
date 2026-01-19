package app;

import model.Alien;
import model.FriendlyAlien;
import model.Hero;
import model.HostileAlien;
import model.HTWRoom;
import model.Lecturer;

import java.util.Random;
import java.util.Scanner;

/**
 * Klasse für den Spielablauf des Escape-Games.
 * @author ruby
 * @author onur
 */

public class EscapeGame {
    private static final int MAX_ROUNDS = 24;               // Maximale Runden (24 Stunden)
    private final Hero hero;                                // Held des Spielers.
    private final HTWRoom[] rooms;                          // Räume des Spiels.
    private boolean gameRunning = true;                     // Gibt an, ob das Spiel läuft.
    private boolean gameFinished = false;                   // Gibt an, ob das Spiel beendet ist.
    private int round = 1;                                  // Aktuelle Runde.
    
    private final Random random = new Random();             // Zufallsgenerator

    private final Alien[] aliens = new Alien[] {            // Aliens im Spiel
        new FriendlyAlien(),
        new HostileAlien()
    };

    private final Lecturer[] lecturers = new Lecturer[] {   // Übungsgruppenleiter*innen
        new Lecturer("Gärtner, Janine", "A calm lecturer with a warm smile and glasses."),
        new Lecturer("Gnaui, Salim", "Serious and professional, dressed formally and very composed."),
        new Lecturer("Poser, Thomas", "Medium height and slim, with a short three-day beard and medium-length brown hair."),
        new Lecturer("Safitri, Reni, Amelia", "Calm and friendly, with delicate features, glasses dark black hair."),
        new Lecturer("Vaseva, Lyudmila", "Small and slim, wearing glasses, with long dark hair.")
    };


    /**
     * Erstellt ein neues Spiel und initialisiert den Helden.
     */
    public EscapeGame() {
        this.hero = new Hero();
        this.rooms = new HTWRoom[8];
        initRooms();
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
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("The game has started. Or not?");
        System.out.println();

        askForName(scanner);
        showIntro(scanner);
        startGameMenu(scanner);
    }

    /**
     * Fragt den Namen ab und speichert ihn im Hero.
     * 
     * @param scanner Scanner
     */
    private void askForName(Scanner scanner) {
        System.out.println("========================================");
        System.out.println("CHOOSE YOUR NAME");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Before your journey at HTW begins...");
        System.out.println("What is your name, brave student?");
        System.out.println();
        System.out.print("Enter your name: ");
        
        String name = scanner.nextLine().trim();
        hero.setName(name);

        // Überleitung zum Intro
        System.out.println();
        System.out.println("________________________________________");
        System.out.println();
        System.out.println("Not everything is as it seems...");
        System.out.println("Look closer, " + hero.getName() + ".");
        System.out.println();
    }

    private void showIntro(Scanner scanner) {
        System.out.println("====================================================");
        System.out.println("THE AWAKENING – JANUARY 5th, 2026");
        System.out.println("====================================================");
        System.out.println();
        System.out.println("You wake up, pack your bag and head to HTW.");
        System.out.println("At the station you grab your first coffee, still half asleep.");
        System.out.println("Lost in thought, you enter Building A.");
        System.out.println();
        System.out.println("Something is wrong. The air feels heavy.");
        System.out.println("From the shadows, a human-sized snail appears.");
        System.out.println();
        System.out.println("'Excuse me...'' it gurgles.");
        System.out.println("'Do you know where I can hide?");
        System.out.println("The mist... the change...'");
        System.out.println();
        System.out.println("A sickly smell of decay fills your lungs.");
        System.out.println("Before you can react, it presses an envelope into your hand");
        System.out.println("and vanishes into the darkness.");
        System.out.println();
        System.out.println("A bright green envelope. The HTW logo still glows.");
        System.out.println("You open it:");
        System.out.println();
        System.out.println("----------------------------------------------------");
        System.out.println("PROJECT 'VOID' – NO EXIT GRANTED");
        System.out.println("You have 24 hours.");
        System.out.println("The exits are erased.");
        System.out.println();
        System.out.println("Only one remains in control: MAJUNTKE.");
        System.out.println("She holds the Master Key.");
        System.out.println();
        System.out.println("To reach her, collect 5 Lecturer signatures.");
        System.out.println("They prove you are still 'worthy'.");
        System.out.println();
        System.out.println("WARNING:");
        System.out.println("If you hear breathing in the silence...");
        System.out.println("do not look back.");
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println("Inside lies a blank form.");
        System.out.println("Your run sheet.");
        System.out.println();
        System.out.println("(Press Enter to continue)");
        scanner.nextLine();
    }

        private void startGameMenu(Scanner scanner) {
            while (gameRunning) {
                System.out.println("========================================");
                System.out.println("What do you want to do?");
                System.out.println("(1) Explore the university");
                System.out.println("(2) Show hero status");
                System.out.println("(3) Show run sheet");
                System.out.println("(4) Take a break");
                System.out.println("(5) Leave the game");
                System.out.print("Please choose a number between 1 and 5: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        exploreUniversity(scanner);
                        break;
                    case "2":
                        showHeroStatus();
                        break;
                    case "3":
                        showRunSheet();
                        break;
                    case "4":
                        takeBreak(scanner);
                        break;
                    case "5":
                        System.out.println("You leave the Game. Goodbye!");
                        gameRunning = false;
                        gameFinished = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please choose a number between 1 and 5: ");
                        break;
                }

                System.out.println();
            }
        }


        /**
         * Initialisiert Räume
         */
        private void initRooms() {
            rooms[0] = new HTWRoom("A214", "Darkness-filled computer lab with neatly arranged PCs.", lecturers[0]);
            rooms[1] = new HTWRoom("A238", "A huge auditorium. The seats rise like steps into the shadows, all facing the lonely podium and whiteboard at the front.", lecturers[1]);
            rooms[2] = new HTWRoom("Infront of A114", "Small seating area and a coffee machine.", lecturers[2]);
            rooms[3] = new HTWRoom("Stairwell near Room 132  ", "Dark stairwell with four staircases next to each other., ", lecturers[3]);
            rooms[4] = new HTWRoom("A K31","Bright reading room with long tables, bookshelves, PCs and a quiet study atmosphere.", lecturers[4]);
            rooms[5] = new HTWRoom("A143", "Seminar room with tables, foldable PCs and swivel chairs.", null);
            rooms[6] = new HTWRoom("Basement hallway", "Dark cold and dirty with a slightly stuffy smell.", null);
            rooms[7] = new HTWRoom("Basement", "Dark lounge near the reading room with many cozy couches and small low tables.", null);
        }


        private void exploreUniversity(Scanner scanner) {
            
            // Wenn der Tag vorbei ist -> verloren
            if (round > MAX_ROUNDS) {
                // Game over Text + Majuntke fliegt weg und sagt ein Spruch hier
                gameRunning = false;
                gameFinished = true;
                return;
            }

            HTWRoom room;

            // Zufallszahl 0..99 für Ereignisse
            int roll = random.nextInt(100);

            // Einfach zufälligen Raum wählen
            room = rooms[random.nextInt(rooms.length)];


            // Rundenanzeige
            System.out.println("============================================================");
            System.out.println("EXPLORE HTW  |  Round: " + round + " / " + MAX_ROUNDS);
            System.out.println("Hours left: " + (MAX_ROUNDS - round));
            System.out.println("Location: " + room.getIdentifier());
            System.out.println("----------------------------------------------------");
            System.out.println(room.getDescription());
            System.out.println("============================================================");


            /**
             * 0-19:  20% nichts passiert
             * 20-71: 52% Alien
             * 72-99: 28% Lecturer
             */
            if (roll < 20) {              
                System.out.println("Nothing happens... just mist and silence.");
            } else if (roll < 72) {     
                encounterAlien(scanner);
            } else {              
                if (room.hasLecturer() && !room.getLecturer().hasSigned()) {               // Lecturer im Raum     
                    encounterLecturer(room.getLecturer());
                } else {
                    System.out.println("There is no lecturer who can help you."); // passiert nur wenn alle Lecturers schon unterschrieben haben
                }
            }

            round++; // Jede Erkundung zählt als eine Runde/Stunde
            hero.startNewRound(); // setzt short Rest zurück
        }

            
        private void encounterLecturer(Lecturer lecturer) {
            System.out.println("You meet a lecturer:");
            System.out.println("Name " + lecturer.getName());
            System.out.println("Description: " + lecturer.getDescription());
            System.out.println();
            
            if (lecturer.hasSigned()) {
                System.out.println("Already signed.");
                return;
            }

            if (lecturer.isReadyToSign()) {
                hero.signExerciseLeader(lecturer);
                lecturer.sign();
                System.out.println("Signature added to run sheet.");
            } else {
                System.out.println("Lecturer is not ready to sign yet.");
            }
        }


        // wird später weiter bearbeitet "Kämpfen oder Fliehen" oder freundliches Alien
        private void encounterAlien(Scanner scanner) {
            Alien alien = aliens[random.nextInt(aliens.length)];
        }


        private void showHeroStatus() {
            System.out.println("========================================");
            System.out.println("HERO STATUS");
            System.out.println("========================================");
            System.out.println("CHARACTER INFORMATION");
            System.out.println("Name:   " + hero.getName());
            System.out.println("Health: " + hero.getHealthPoints() + " / 50 HP");
            System.out.println("EXP:    " + hero.getExperiencePoints());
            System.out.println();
            System.out.println("CURRENT ROUND");
            System.out.println("Round: " + round + " / " + MAX_ROUNDS);
            System.out.println();
            System.out.println("RUN SHEET PROGRESS");
            Lecturer[] signed = hero.getSignedExerciseLeaders();
            int signatureCount = 0;
            for (int i = 0; i < signed.length; i++) {
                if (signed[i] != null) {
                    signatureCount++;
                    System.out.println(signed[i].getName());
                }
            }

            int missingSigs = 5 - signatureCount;
            System.out.println("Signatures collected: " + signatureCount);
            System.out.println("Signatures needed: " + missingSigs);

            if (missingSigs == 0) {
                System.out.println();
                System.out.println("You have collected all signatures! You can now search for Professor Majuntke.");
            }
        }

        private void showRunSheet() {
            System.out.println("========================================");
            System.out.println("RUN SHEET");
            System.out.println("========================================");

            Lecturer[] signed = hero.getSignedExerciseLeaders();
            boolean empty = true;

            for (int i = 0; i < signed.length; i++) {
                if (signed[i] != null) {
                    System.out.println("- " + signed[i].getName());
                    empty = false;
                }
            }

            if (empty) {
                System.out.println("No signatures yet.");
            }

            System.out.println();
        }

        private void takeBreak(Scanner scanner) {
            System.out.println("========================================");
            System.out.println("TAKE A BREAK");
            System.out.println("========================================");
            System.out.println("(1) Short rest (+3 HP) [once per round]");
            System.out.println("(2) Long rest (+10 HP) [costs a round]");
            System.out.print("Please choose a number between 1 and 2: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                hero.regenerate(false);
                System.out.println("You take a short rest.");
            } else if (choice.equals("2")) {
                hero.regenerate(true);
                System.out.println("You take a long rest.");
                hero.startNewRound();
            } else {
                System.out.println("Invalid input.");
            }

            System.out.println();
        }


    /**
     * Gibt den Helden des Spiels zurück.
     * @return Held des Spielers
     */
    public Hero getHero() {
        return hero;
    }
}
