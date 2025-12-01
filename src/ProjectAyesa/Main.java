package ProjectAyesa;
import ProjectAyesa.Core.Game;
import ProjectAyesa.Core.Console;
import ProjectAyesa.Core.Graphics;
import ProjectAyesa.Games.GameOfClubs;
import ProjectAyesa.Games.GameOfHearts;
import ProjectAyesa.Games.GameOfDiamonds;
import ProjectAyesa.Games.GameOfSpades;
import java.util.Scanner;
import java.util.HashSet; // NEW
import java.util.Set; // NEW
// We need to import the Player and Intro classes for the main logic
// Since they are in the same package (no package declaration), they are available.

public class Main {
    private final Scanner scanner;
    private Intro intro;
    private Player player;
    private Set<String> suitsAttempted = new HashSet<>();// NEW: Tracker for games attempted

    public Main() {
        // Initialize the Scanner to read user input from the console
        this.scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Main game = new Main();
        game.startGame();
    }
    /**
     * Main game setup and flow controller.
     */
    public void startGame() {
        // 1. Run the Intro sequence (delegated to the Intro class)
        Intro intro = new Intro(scanner);
        player = intro.startIntro();
        this.intro = intro;

        // 2. Start the main game menu once the player object is created
        if (player != null) {
            mainGameLoop();
        } else {
            System.out.println("Game could not be started. Exiting.");
        }
    }
    /**
     *
     * The main loop that presents the Player Info / Destination selection.
     */
    private void mainGameLoop() {
        while (true) {
            Graphics.mainTitle();
            System.out.println("[1] - Player Information");
            System.out.println("[2] - Select your Destination");
            System.out.print("\nEnter Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    player.displayInfo();
                    Console.Menu();
                     // Wait for user acknowledgment
                    Console.clearConsole();
                    break;
                case "2":
                    selectDestination();
                    // Once a destination is selected, we break this loop
                    // and let the game continue in that destination's logic.
                    break;
                default:
                    Console.InvalidB();
            }
        }
    }

    /**
     * Handles the selection of the first destination.
     */
    private void selectDestination() {
        Console.clearConsole();
        if (player.isGameOver()) {
            Graphics.GameOver();
            System.out.println(Console.RED + "SYSTEM: Your visa has reached zero. The game has claimed you." + Console.RESET);
            System.out.println(Console.RED +"\tThank you for playing. Exiting...\n"+ Console.RESET);
            System.exit(0); // Exit the application
        }
        if (suitsAttempted.size() == 4 && player.getCardCount() >= 2) {
                // If the player meets the conditions and survived (not Game Over), trigger Outro.
            intro.displayOutro();
            System.exit(0); // Exits the loop and the method
            }
        Graphics.mainTitle();
        Console.typewriter("The sterile space shatters. The sky above rips open, revealing massive, pulsating neon holograms of familiar, yet unnerving, Philippine locations.\n", 35);
        Console.flushPendingSkip();
        System.out.println("\nSYSTEM: Begin your game. Choose your first Destination.\n");
        System.out.println("\t[1] - Tondo");
        System.out.println("\t[2] - Divisoria");
        System.out.println("\t[3] - Malacañang");
        System.out.println("\t[4] - MRT");
        System.out.print("\nEnter Choice: ");
        String choice = scanner.nextLine().trim();
       
        Game gameToPlay = null;
        String suitKey = "";
       
        switch (choice) {
            case "1":
                suitKey = "SPADES";
                gameToPlay = new GameOfSpades();
                break;
            case "2":
                suitKey = "HEARTS";
                gameToPlay = new GameOfHearts();
                break;
            case "3":
                suitKey = "DIAMONDS";
                gameToPlay = new GameOfDiamonds();
                break;
            case "4":
                suitKey = "CLUBS";
                gameToPlay = new GameOfClubs();
                break;

                // When IntroClubs returns, the player returns to the mainGameLoop
            default:
                Console.InvalidA();
                selectDestination();
                return;
        }
        if (gameToPlay != null) {
            launchGame(gameToPlay, suitKey);
        }
    }

private void launchGame(Game game, String suitKey) {
        // 1. ONE-SHOT CHECK: Have we played this before? (Win OR Loss)
        if (suitsAttempted.contains(suitKey)) {
            Console.clearConsole();
            Graphics.mainTitle();
            System.out.println("ACCESS DENIED: " + Console.BOLD +  game.getGameName() + Console.RESET);
            System.out.println("\nYou have already attempted this location.");
            System.out.println("The Borderland does not offer second chances.");
            Console.Menu();
            return; // Stops the player from entering
        }
        // 2. Proceed to Game
        Console.clearConsole();
        Graphics.mainTitle();
        System.out.println("SYSTEM: Heading towards " + Console.BOLD + game.getGameName() + Console.RESET + "... The game begins!\n");
        Console.Start();
        Console.clearConsole();

        // 3. Play Logic (Polymorphism)
        boolean wonGame = game.play();

        // 4. Update Status
        player.updateProfile(suitKey, wonGame);
      
        // 5. MARK AS PLAYED: This ensures they can never play this suit again
        suitsAttempted.add(suitKey);
    }
}