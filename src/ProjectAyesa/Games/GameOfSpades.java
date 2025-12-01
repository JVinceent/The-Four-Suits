package ProjectAyesa.Games;
import ProjectAyesa.Core.Game;
import java.util.*;
import ProjectAyesa.Core.Graphics;
import ProjectAyesa.Core.Console;
import java.io.IOException;

// 2. INHERITANCE: GameOfSpades "is a" Game
public class GameOfSpades extends Game {
// 3. ENCAPSULATION: Instance variables (private, non-static)

    private static final String gameName = "Spades - The Fire Room";
    private static final String gameDesc = 
            "\tThis is the game of reflexes." +
            "\n\tIn this game, you are inside a sealed steel chamber filled with flammable gas." +
            "\n\tThe chamber contains a total of five sections, each with active gas panels." +
            "\n\tYour survival depends entirely on your reaction speed." +
            "\n" + (Console.BOLD + "\nGood luck.\n" + Console.RESET);

    private Scanner sc;
    private Random rand;
    private static final String gameRules = 

        "The rules are simple:" +
        "\n\t- Complete 5 total rounds of the game." +
        "\n\t- If the screen says " + Console.BOLD + "'RUN!'" + Console.RESET + ", press " + Console.BOLD + "Enter" + Console.RESET + " IMMEDIATELY." +
        "\n\t- If it says " + Console.BOLD + "'STAY!'" + Console.RESET + ", " + Console.BOLD + "DO NOTHING " + Console.RESET + "for 3 seconds." +
        "\n\t- 2 wrong moves and you're dead.";
   
    // Constructor
    public GameOfSpades(){
        super(gameName, gameDesc, gameRules);
        this.sc = new Scanner(System.in);
        this.rand = new Random();
    }

    @Override

    public boolean play() {
        Graphics.SpadesTitle();
        Console.typewriter("You materialize in a maze of narrow, dark alleys, and the desperate, chaotic heart of Tondo. \n", 50);
        Console.Continue();

        while (true) {
            Graphics.SpadesTitle();
            Graphics.GraphicsFireA();
            System.out.println(Console.BOLD + "\nSYSTEM: " + Console.RESET + "Welcome, Player. You have entered the game of " + Console.BOLD + "Spades - the Fire Room." + Console.RESET);
            System.out.println(getGameDesc());
            System.out.println("[1] - Rules");
            System.out.println("[2] - Play");
            System.out.printf("\nEnter your choice: ");
            String choice = sc.nextLine().trim();
            Console.clearConsole();
            switch (choice) {
                    case "1":
                        Graphics.SpadesTitle();
                        System.out.println(getGameRules());
                        Console.Menu();
                        break;
                    case "2":
                        return startInternalGame();
                    default:
                        Graphics.SpadesTitle();
                        Console.InvalidChoice();
                        break;
            }
        }
    }
    private boolean startInternalGame() {
        Console.clearConsole();
        Graphics.SpadesTitle();
        int rounds = 5, score = 0;
        boolean hasBeenWarned = false;

        for (int i = 1; i <= rounds; i++) {
            // Clear any buffered input before each round starts
            flushInput();
           
            if (i == 4) {
                Console.clearConsole();
                Graphics.SpadesTitle();
                System.out.println(Console.RED + Console.BOLD + "WARNING: " + Console.RESET + Console.RED + "The rules will now be REVERSED!" + Console.RESET);
                System.out.println("\nFrom now on:");
                System.out.println("\t- If it says " + Console.BOLD + "'RUN!'" + Console.RESET + ", you must " + Console.BOLD + "STAY still." + Console.RESET);
                System.out.println("\t- If it says " + Console.BOLD + "'STAY!'" + Console.RESET + ", you must " + Console.BOLD + "RUN press ENTER." + Console.RESET);
                System.out.println(Console.BOLD + "\nGood luck." + Console.RESET);
                System.out.println();
                countdown(10);      
                Console.clearConsole();
                Graphics.SpadesTitle();
                // Clear input again after countdown
                flushInput();
            }
            String command = rand.nextBoolean() ? "RUN!" : "STAY!";
            System.out.println("Round " + i + "...");


            int baseDelay = hasBeenWarned ? 3500 : 2500;
            int randomDelay = rand.nextInt(2000) + 1000;
            try { Thread.sleep(baseDelay + randomDelay); } catch (Exception e) {}

            boolean isSwitched = (i >= 4);
           
            System.out.println(command);
            long start = System.currentTimeMillis();
            String input = waitForEnterWithTimeout(3000);
            long reaction = System.currentTimeMillis() - start;

            boolean shouldRun = command.equals("RUN!");
            if (isSwitched) shouldRun = !shouldRun;

            boolean roundWon = false;

            if (shouldRun) {
                if (input == null) {
                    System.out.println("You froze! The laser hit you!\n");
                } else if (reaction <= 700) {
                    System.out.println("\033[AYou reacted perfectly! (" + reaction + " ms)\n");
                    score++;
                    roundWon = true;
                } else {
                    System.out.println("Too slow! You got hit!\n");
                }
            } else {
                if (input == null) {
                    System.out.println("You stayed still. Safe!\n");
                    score++;
                    roundWon = true;
                } else {
                    System.out.println("\033[AYou moved when you shouldn't.\n");
                }
            }

            if (!roundWon) {
                if (!hasBeenWarned) {
                    hasBeenWarned = true;
                    if (i == rounds) {
                        System.out.println( Console.RED + "You are severely burned but managed to survive.\n" + Console.RESET);
                    } else {
                        System.out.println( Console.RED + Console.BOLD + "WARNING: " + Console.RESET + Console.RED + "You are severely burned. You only have one chance left to survive.\n" + Console.RESET);
                        try { Thread.sleep(1000); } catch (Exception e) {}
                    }
                } else {
                    System.out.println(Console.RED + Console.BOLD + "GAME OVER" + Console.RESET);
                    Console.Continue();
                    Graphics.SpadesTitle();
                    Graphics.GraphicsFireB();
                    System.out.println(Console.BOLD + "\nSYSTEM:" + Console.RESET + " You failed to move accordingly and got consumed by the raging flames. You were devouConsole.RED by the inferno!");
                    Console.MainMenu();
                    return false;
                }
            }
            if (i == 3 && rounds > 3) {
                System.out.println(Console.BOLD + "Congratulations!" + Console.RESET + " You passed the first three rounds.");
                Console.Continue();
            }
        }
        if (score >= 3){
            System.out.println(Console.GREEN + Console.BOLD + "CONGRATULATIONS!" + Console.RESET);
            Console.Continue();
            Graphics.SpadesTitle();
            Graphics.GraphicsFireC();
            System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " Congratulations, Player! You reacted faster than the fire itself and escaped the Fire Room.");
            Console.Continue();
            Graphics.SpadesTitle();
            System.out.println(Console.BOLD + "SYSTEM: " + Console.RESET + "You have earned the Spades card, a proof of decisive action.");
            Graphics.SpadesSymbol();
            Console.MainMenu();
           
        }
       


        return true; // <-- REQUIRED: Return false if score requirement wasn't met
    }
//*************************************************************************************************************//
    private void flushInput() {
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException ignore) {
        }
    }
   
    private String waitForEnterWithTimeout(int timeoutMillis) {
        // Clear any buffered input before waiting
        flushInput();
       
        long endTime = System.currentTimeMillis() + timeoutMillis;
        try {
            while (System.currentTimeMillis() < endTime) {
                if (System.in.available() > 0) {
                    return sc.nextLine();
                }
                Thread.sleep(10);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    private void countdown(int seconds) {
        try {
            System.out.print(Console.HIDE_CURSOR);
            System.out.flush();
            for (int i = seconds; i > 0; i--) {
                System.out.print("The game will continue in: " + i);
                System.out.flush();
                Thread.sleep(1000);
                System.out.print("\r\033[2K");
                System.out.flush();
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.print(Console.SHOW_CURSOR);
            System.out.flush();
        }
    }
}