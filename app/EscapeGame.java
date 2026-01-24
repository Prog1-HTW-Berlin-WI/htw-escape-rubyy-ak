package app;

import model.Alien;
import model.FriendlyAlien;
import model.Hero;
import model.HostileAlien;
import model.HTWRoom;
import model.Lecturer;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 * Klasse für den Spielablauf des Escape-Games.
 * @author ruby
 * @author onur
 */

public class EscapeGame implements Serializable {
    private static final long serialVersionUID = 1L;

    // Attribute
    private static final int MAX_ROUNDS = 24;               // Maximale Runden (24 Stunden)
    private final Hero hero;                                // Held des Spielers.
    private boolean introShown = false;
    private final HTWRoom[] rooms;                          // Räume des Spiels.
    private boolean gameRunning = true;                     // Gibt an, ob das Spiel läuft.
    private boolean gameFinished = false;                   // Gibt an, ob das Spiel beendet ist.
    private int round = 1;                                  // Aktuelle Runde.
    private boolean majuntkeUnlocked = false;               // Gibt an, ob Majuntke freigeschaltet wurde.

    private transient Random random = new Random();         // Zufallsgenerator

    private final Lecturer[] lecturers = new Lecturer[] {   // Übungsgruppenleiter*innen
        new Lecturer("Gärtner, Janine", "A calm lecturer with a warm smile and glasses."),
        new Lecturer("Gnaui, Salim", "Serious and professional, dressed formally and very composed."),
        new Lecturer("Poser, Thomas", "Medium height and slim, with a short three-day beard and medium-length brown hair."),
        new Lecturer("Safitri, Reni, Amelia", "Calm and friendly, with delicate features, glasses dark black hair."),
        new Lecturer("Vaseva, Lyudmila", "Small and slim, wearing glasses, with long dark hair.")
    };
                                            
    private final String[] majuntkeQuestions = {            // Majuntke Fragen
        "What is a variable?",
        "Which loop is guaranteed to run at least once?",
        "What are primitive data types?"
    };

    private final String[][] majuntkeAnswers = {            // Antworten (Zwei-Dimensinales Array: erste Dimension -> welche Frage, zweite Dimension -> welche Antwort zu dieser Frage)
        {
            "A memory location used to store values.",
            "A command to end a program.",
            "An error in the code.",
            "A method without a return value"
        },
        {
            "for loop",
            "while loop",
            "do-while loop",
            "foreach loop"
        },
        {
            "Basic data types that store simple values like int, double, char or boolean.",
            "Special classes used for graphics",
            "Only text-based data types",
            "Complex, objects that contain methods"
        }
    };

    private final int[] majuntkeCorrect = {1, 3, 1};           //Richtige Antworten

    
    // Konstruktor
    public EscapeGame() {
        this.hero = new Hero();
        this.rooms = new HTWRoom[8];
        initRooms();
    }


    // Getter / Setter
    /**
     * Gibt den Helden des Spiels zurück.
     * @return Held des Spielers
     */
    public Hero getHero() {
        return hero;
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

    private void ensureRandom() {
        if (random == null) {
            random = new Random();
        }
    }

    /**
     * Startet den Spielablauf.
     */
    public void run() {
        ensureRandom();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("The game has started. Or not?");
        System.out.println();

        if (!introShown) {
            askForName(scanner);
            showIntro(scanner);
            introShown = true;
        }

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
                        if (!canExplore()) {
                            printNoStrengthMessage();
                            break;
                        }
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
                        setGameRunning(false);
                        break;
                    default:
                        System.out.println("Invalid input. Please choose a number between 1 and 5: ");
                }

                System.out.println();
            }
        }


        /**
         * Initialisiert Räume mit ihren Beschreibungen und ggf. den zugehörigen Lecturer.
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

        /**
         * Führt eine Erkundungsrunde durch.
         * Erzeugt zufällige Ereignisse wie Alien, Lecturer oder nichts.
         * 
         * @param scanner Scanner
         */
        private void exploreUniversity(Scanner scanner) {
            ensureRandom();
            
            // Wenn der Tag vorbei ist -> verloren
            if (round > MAX_ROUNDS) {
                System.out.println("========================================");
                System.out.println("TIME IS UP");
                System.out.println("========================================");
                System.out.println("Suddenly something appears in front of you.");
                System.out.println("It is Professor Majuntke.");
                System.out.println();
                System.out.println("She smiles.. then her body begins to change.");
                System.out.println("Her skin cracks, her eyes glow. She is an alien.");
                System.out.println("'You students are so silly..'");
                System.out.println("'Those who do not study and organise themselves will always stay trapped here.'");
                System.out.println("'See you next year!'");
                System.out.println();
                System.out.println("She slowly enters her spaceship and disappears into the darkness.");
                System.out.println("-------------------------------------------------------");
                System.out.println("                    GAME OVER                          ");
                System.out.println("-------------------------------------------------------");
                gameRunning = false;
                gameFinished = true;
                return;
            }

            // wenn Majuntke freigeschafaltet ist, kommt sie in der nächsten Erkudnungsrunde
            if (majuntkeUnlocked) {
                encounterProfessorMajuntke(scanner);
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

         /**
          * Verarbeitet die Begegnung mit einem Lecturer.
          * Prüft, ob eine Unterschrift möglich ist und trägt sie ggf. ein.
          * 
          * @param lecturer Der getroffene Lecturer
          */   
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

                // Majuntke wird freigeschaltet (wenn alle 5 Unterschriften gesammelt wurden) und erscheint in der nächsten Erkudnungsrunde
                if (countSignatures() == 5) {
                    majuntkeUnlocked = true;
                }

            } else {
                System.out.println("Lecturer is not ready to sign yet.");
            }
        }

        private Alien createRandomAlien() {
            return random.nextBoolean() ? new FriendlyAlien() : new HostileAlien();
        }

        /**
         * Verarbeitet eine Begegnung mit einem Alien.
         * Bei feindlichen Aliens kann der Spieler kämpfen oder fliehen.
         * 
         * @param scanner
         */
        private void encounterAlien(Scanner scanner) {
            Alien alien = createRandomAlien();

            // Only handle HostileAlien for fight/flee
            if (!(alien instanceof HostileAlien)) {
                System.out.println("You meet a friendly alien. It greets you and disappears into the shadows.");
                return;
            }

            System.out.println("A hostile alien appears: " + alien.getName());
            System.out.println(alien.getGreeting());

            // Kampfschleife: Nach jedem Zug wieder fragen
            while (!alien.isDefeated() && hero.isOperational()) {
                System.out.println();
                System.out.println("What do you want to do?");
                System.out.println("(1) Fight");
                System.out.println("(2) Flee");
                System.out.print("Choose 1 or 2: ");
                String choice = scanner.nextLine().trim();

                if ("1".equals(choice)) {
                    // Hero greift an
                    int heroDamage = hero.attack();
                    if (heroDamage == 0) {
                        System.out.println("You missed!");
                    } else if (heroDamage == Math.round((hero.getExperiencePoints() * 2.3 + 1) * 2)) {
                        System.out.println("Critical hit! Double damage!");
                        alien.takeDamage(heroDamage);
                    } else {
                        System.out.println("You hit the alien for " + heroDamage + " damage.");
                        alien.takeDamage(heroDamage);
                    }

                    if (alien.isDefeated()) {
                        System.out.println("You defeated the alien!");
                        hero.addExperiencePoints(5);
                        System.out.println("You gained 5 experience points.");
                        return;
                    }

                    // Alien greift zurück
                    int alienDamage = 6;
                    System.out.println("The alien attacks you and deals " + alienDamage + " damage.");
                    hero.takeDamage(alienDamage);
                    System.out.println("Your health: " + hero.getHealthPoints());
                    if (!hero.isOperational()) {
                        System.out.println("You were defeated by the alien...");
                        hero.addExperiencePoints(1);
                        System.out.println("You gained 1 experience point.");
                        gameRunning = false;
                        gameFinished = true;
                        return;
                    }
                } else if ("2".equals(choice)) {
                    // Fluchtversuch
                    boolean escaped = hero.flee();
                    if (escaped) {
                        System.out.println("You successfully escaped from the alien!");
                        return;
                    } else {
                        System.out.println("You failed to escape! The alien attacks you!");
                        // Alien greift an
                        int alienDamage = 6;
                        System.out.println("The alien attacks you and deals " + alienDamage + " damage.");
                        hero.takeDamage(alienDamage);
                        System.out.println("Your health: " + hero.getHealthPoints());
                        if (!hero.isOperational()) {
                            System.out.println("You were defeated by the alien...");
                            hero.addExperiencePoints(1);
                            System.out.println("You gained 1 experience point.");
                            gameRunning = false;
                            gameFinished = true;
                            return;
                        }
                    }
                } else {
                    System.out.println("Invalid input. Please choose 1 or 2.");
                }
            }
        }


        // Spielende
        /**
         * Zählt die Anzahl der gesammelten Unterschriften.
         * 
         * @return Anzahl der bereits unterschriebenen Lecturer
         */
        private int countSignatures() {
            int count = 0;
            Lecturer[] signed = hero.getSignedExerciseLeaders();
            for (int i = 0; i < signed.length; i++) {
                if (signed[i] != null) {
                    count++;
                }
            }
            return count;
        }

        /**
         * Stellt eine zufällige Multiple-Choice-Frage von Professorin Majuntke.
         * 
         * @param scanner Scanner
         * @return true, wenn die Antwort korrekt ist, sonst false
         */
        private boolean askRandomMajuntkeQuestion(Scanner scanner) {
            int questionIndex = random.nextInt(majuntkeQuestions.length);

            System.out.println("----------------------------------------");
            System.out.println("MULTIPLE-CHOICE QUESTION");
            System.out.println("----------------------------------------");
            System.out.println(majuntkeQuestions[questionIndex]);
            System.out.println();

            for (int i = 0; i < 4; i++) {
                System.out.println("(" + (i+1) +") " + majuntkeAnswers[questionIndex][i]);
            }

            System.out.println();
            System.out.print("Your answer (1-4):" );
            String input = scanner.nextLine().trim();

            int choice ;
            try {
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > 4) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
            
            return choice  == majuntkeCorrect[questionIndex];
        }  


        /**
         * Führt die finale Begegnung mit Professorin Majuntke aus.
         * Enthält das Quiz und entscheidet über Sieg oder Niederlage.
         * 
         * @param scanner
         */
        private void encounterProfessorMajuntke(Scanner scanner) {
            System.out.println("========================================");
            System.out.println("PROFESSOR MAJUNTKE");
            System.out.println("========================================");
            System.out.println("Suddenly something appears in front of you.");
            System.out.println("Bathed in a strange, shimmering light, stands Professor Majuntke.");
            System.out.println();
            System.out.println("'So you have collected all signatures..'");
            System.out.println("'But a signature is just ink. To leave the Void, you must prove your knowledge, brave student.'");
            System.out.println("She raises her hand and a glowing screen appears.");
            System.out.println("'One final test. One question. Are you ready?'");

            System.out.println();

            // 1. Quizversuch
            boolean passed = askRandomMajuntkeQuestion(scanner);

            // 2. Quizversuch
            if (!passed) {
                System.out.println();
                System.out.println("Wrong! Second chance granted, human!");
                System.out.println("Don't disappoint me again..");
                passed = askRandomMajuntkeQuestion(scanner);
            }

            // Ergebnis
            if (passed) {
                System.out.println("-------------------------------------------------------");
                System.out.println("CORRECT!");
                System.out.println("-------------------------------------------------------");
                System.out.println("A golden light spreads through the room and the darkness vanishes.");
                System.out.println("The black slime disappears, the air becomes clear again.");
                System.out.println("The creatures turn back into students. The HTW is saved.");
                System.out.println();
                System.out.println("Professor Majuntke smiles and hands you the glowing certificate.");
                System.out.println("I knew you could do it, brave student.");
                System.out.println("-------------------------------------------------------");
                System.out.println("                    VICTORY                             ");
                System.out.println("-------------------------------------------------------");
            } else {
                System.out.println("Professor Majuntke smiles.. then her body begins to change.");
                System.out.println("Her skin cracks, her eyes glow. She is an alien.");
                System.out.println();
                System.out.println("'You students are so silly..");
                System.out.println("'Even after a second try, you still failed.'");
                System.out.println("'Those who do not study and organise themselves will always stay trapped here.'");
                System.out.println("'See you next year!'");
                System.out.println();
                System.out.println("She slowly enters her spaceship and disappears into the darkness.");
                System.out.println("-------------------------------------------------------");
                System.out.println("                    GAME OVER                          ");
                System.out.println("-------------------------------------------------------");
            }
        
        gameRunning = false;
        gameFinished = true;
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
            System.out.println();
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
                if (hero.regenerate(false)) {
                    System.out.println("You take a short rest.");
                    System.out.println("Your health restored to " + hero.getHealthPoints() + " HP.");
                }
            } else if (choice.equals("2")) {
                if (hero.regenerate(true)) {
                System.out.println("You take a long rest.");
                System.out.println("Your health restored to " + hero.getHealthPoints() + " HP.");
                    round++; // lange Pause = eine Runde
                    hero.startNewRound();
                }
            } else {
                System.out.println("Invalid input.");
            }

            System.out.println();
        }

        private boolean canExplore() {
            return hero.isOperational() && hero.getHealthPoints() > 0;
        }

        private void printNoStrengthMessage() {
            System.out.println();
            System.out.println("========================================");
            System.out.println("NO MORE STRENGTH");
            System.out.println("========================================");
            System.out.println("Your body feels heavy... the Void is pulling you down.");
            System.out.println("No more strength left. Take a break.");
            System.out.println("========================================");
            System.out.println();
        }
}