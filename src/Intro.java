package ProjectAyesa;
import ProjectAyesa.Core.Console;
import ProjectAyesa.Core.Graphics;
import java.util.Scanner;

/**
 * Intro class manages the initial text-based narrative and decision-making
 * before the main game loop begins.
 */
public class Intro {
    private Scanner scanner;
    private final String BORDERLAND_INFO = "SYSTEM: You begin with 2 Visas. A Visa is your chance to make your decisions right.\n\tSucceed in a game, and you are granted the card. Fail, and you lose one. Lose your last Visa, and the game takes everything.\n\tIn the Borderland, there is no escape. You play, or you die.";
                 
    public Intro(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Starts the main introduction sequence.
     * @return The newly created Player object once the username is entered.
     */
    public Player startIntro() {
        Graphics.mainTitle();
        Console.Start();
        
        return handleScene1();
    }




    // Handles the initial prompt with choices [1], [2], [3]
    private Player handleScene1() {
        Console.clearConsole();
        Graphics.mainTitle();
        System.out.println("You're violently awoken, not by comfort, but by blinding, sterile white light.");
        System.out.println("You find yourself alone in a vast, featureless space.");
       
        while (true) {
            System.out.println("\n[1] - Where am I?");
            System.out.println("[2] - What Borderland?");
            System.out.println("[3] - How to go back?");
            System.out.print("\nEnter Choice: ");




            String choice = scanner.nextLine().trim();




            switch (choice) {
                case "1":
                    Console.clearConsole();
                    Graphics.mainTitle();
                    System.out.println("SYSTEM: WELCOME TO THE BORDERLAND!");
                    return handleScene2();
                case "2":
                    Console.clearConsole();
                    Graphics.mainTitle();
                    System.out.println(BORDERLAND_INFO);
                    return handlePlayGamePrompt();
                case "3":
                    Console.clearConsole();
                    Graphics.mainTitle();
                    System.out.println("SYSTEM: Not enough information right now...");
                    return handlePlayGamePrompt();
                default:
                    Console.InvalidC();
                    return handleScene1();                
            }
        }
    }
   
    // Handles the subsequent prompt after [1] is chosen in Scene 1
    private Player handleScene2() {
        while (true) {
            System.out.println("\n[1] - What Borderland?");
            System.out.println("[2] - How to go back?");
            System.out.println("[3] - Return");
            System.out.print("\nEnter Choice: ");




            String choice = scanner.nextLine().trim();




            switch (choice) {
                case "1":
                    Console.clearConsole();
                    Graphics.mainTitle();
                    System.out.println(BORDERLAND_INFO);
                    return handlePlayGamePrompt();
                case "2":
                    Console.clearConsole();
                    Graphics.mainTitle();
                    System.out.println("System: Not enough information right now…");
                    return handlePlayGamePrompt();
                case "3":
                    Console.clearConsole();
                    return handlePlayGamePrompt(); // Return to the start of the initial scene
                default:
                    Console.InvalidC();
                    Graphics.mainTitle();
                    return handlePlayGamePrompt();
            }
        }
    }




    // Handles the "Play the game" prompt
    private Player handlePlayGamePrompt() {
        while (true) {
            System.out.println("\n[1] - Play the game");
            System.out.println("[2] - Return");
            System.out.print("\nEnter Choice: ");




            String choice = scanner.nextLine().trim();




            if (choice.equals("1")) {
                return setupPlayer();
            } else if (choice.equals("2")) {
                return handleScene1(); // Return to the initial scene
            } else {
                Console.InvalidB();
                Graphics.mainTitle();
            }
        }
    }




    // Handles username collection and Player object creation
    private Player setupPlayer() {
        String username;
        do {
            System.out.print("\nEnter Username: ");
            username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("Username cannot be empty. Please enter a name.");
            }
        } while (username.isEmpty());
        Console.clearConsole();
        Graphics.mainTitle();
        System.out.println("SYSTEM: Welcome Player, " + username + "!");
        Console.Continue();
        Console.clearConsole();
        return new Player(username);
    }




    public void displayOutro() {
        // We assume the caller (Main.java) has already checked player.hasAllCards()
        Console.clearConsole();
        Graphics.mainTitle();
       
        System.out.println("SYSTEM: The Trial is over. You have seen the system and lived.");
        System.out.println("\tNow, choose your new role in the reality you defined.\n");
        System.out.println("\n==================================================================");
        System.out.println("\t\t\tFINAL CHOICE");
        System.out.println("\n==================================================================");
        System.out.println("\nAccept the new reality or reject it.");
        System.out.println("Do you choose to become a " + Console.BOLD + "CITIZEN" + Console.RESET + " or " + Console.BOLD + "REJECT" + Console.RESET + " the system?");
        System.out.println("\n==================================================================\n");
       
        // Choice Loop
        while (true) {
            System.out.println("[1] CITIZEN (Accept the system)");
            System.out.println("[2] REJECT (Reject the system)");
            System.out.print("\nEnter Choice: ");
           
            String choice = scanner.nextLine().trim();
            Console.clearConsole();
            Graphics.mainTitle();
           
            if (choice.equals("1")) {
                // Citizen (Accept)
                System.out.println("SYSTEM: The Borderland is the silent space between realities, a consensus of non-existence.");  
                System.out.println("\tYou are trapped by the rules of the cage you built.");
                System.out.println("\tThe \"Truth\" you found? It was merely the Tutorial.");
                Console.Continue();
                Graphics.mainTitle();


                System.out.println("SYSTEM: Your reward is the burden of creation, the involuntary subject of " + Console.BOLD + "Version 2.0: The Suit of the Self." + Console.RESET);
                System.out.println("\tThe game doesn't end; it just changes hands. Begin.");
                Console.Continue();
                Graphics.mainTitle();


                System.out.println("SYSTEM: " + Console.BOLD + "WELCOME, JOKER!\n" + Console.RESET);
                Graphics.Joker();
                Console.Continue();
                Graphics.mainTitle();
                System.out.println("\n[TO BE CONTINUED...]");
                break;
            } else if (choice.equals("2")) {
                System.out.println("SYSTEM: The system resets, memory erased.");
                System.out.println("\tThe light fades entirely, leaving only the sound of a single, slow heartbeat.");
                Console.Continue();
                Graphics.mainTitle();


                System.out.println(Console.BOLD + "[SYSTEM RESET...]" + Console.RESET);
                break;
            } else {
                Console.InvalidB();
            }
        }
       
            // Final exit sequence
            System.out.print("\n[Press ENTER to exit the game...]");
            scanner.nextLine();
            System.exit(0);
        }
   
    // ... rest of the Intro class methods (handleScene1, handleScene2, etc.)
}