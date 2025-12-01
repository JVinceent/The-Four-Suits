package ProjectAyesa.Games;

import ProjectAyesa.Core.Game;
import ProjectAyesa.Core.Graphics;
import ProjectAyesa.Core.Console;

import java.util.Scanner;
import java.util.Random;
import java.time.Instant;
import java.time.Duration;

public class GameOfDiamonds extends Game {
    // 3. ENCAPSULATION: Instance variables (private, non-static)
    private final int DEFAULT_TIME_LIMIT = 60;
    private final int CODE_LEN = 3;
    private Scanner sc;
    private Random rng;
    
    private static final String gameName = "Diamonds - The Deadlock";
    
    private static final String gameDesc = 
            "\tThis test examines your intellect and logical skill." +
            "\n\tBehind this door lies your survival." + 
            "\n\tTo escape, you must find a three-digit code that will satisfy the conditions provided." +
            Console.BOLD + "\n\nGood luck.\n" + Console.RESET;

    private static final String gameRules = 
        "The rules are simple:" +
        "\n\t- " + Console.YELLOW + "YELLOW" + Console.RESET + " : Correct number, wrong position." +
        "\n\t- " + Console.GREEN + "GREEN" + Console.RESET + " : Correct number, correct position." +
        "\n\t- " + Console.RED + "RED" + Console.RESET + "  : Wrong number entirely." +
        "\n\nPlease note that: " +
        "\n\t- Each level of difficulty corresponds to a different time limit." +
        "\n\t\t- Easy (90 seconds)" +
        "\n\t\t- Normal (60 seconds)" +
        "\n\t\t- Hard (30 seconds)" +
        "\n\nYou have limited time to unlock the doors!";

    public GameOfDiamonds() {
        super(gameName, gameDesc, gameRules);
        this.sc = new Scanner(System.in);
        this.rng = new Random();
    }

    @Override
    public boolean play() {
        Graphics.DiamondsTitle();
        Console.typewriter(
                "You find yourself in a polished, unnervingly quiet room in Malacañang, the President's office. The clock on the wall ticks loudly.\n",
                35);
        Console.Continue();
        boolean gameResult = false; // Declare result variable
        while (true) {
            Graphics.DiamondsTitle();
            Graphics.GraphicsDoorLocked();
            System.out.println(Console.BOLD + "\nSYSTEM:" + Console.RESET
                    + " Welcome, Player. You have entered the game of " 
                    + Console.BOLD + getGameName() + Console.RESET);
            System.out.println(getGameDesc());
            System.out.println("[1] - Rules");
            System.out.println("[2] - Hint");
            System.out.println("[3] - Play");
            System.out.print("\nEnter you choice: ");
            String choice = sc.nextLine().trim();
            Console.clearConsole();

            switch (choice) {
                case "1":
                    Graphics.DiamondsTitle();
                    System.out.println(getGameRules());
                    Console.Menu();
                    break;
                case "2":
                    printHint();
                    Console.Menu();
                    break;
                case "3":
                    gameResult = startInternalGame(); // <-- CALL playGame and store the result
                    if (gameResult) {
                        return true; // <-- WIN: Exit IntroDiamonds and return true
                    }
                    return false;
                default:
                    Graphics.DiamondsTitle();
                    Console.InvalidChoice();
                    break;
            }
        }
    }

    private boolean startInternalGame() {
        Graphics.DiamondsTitle();
        int timeLimit = rollDifficultySeconds();
        final String secret = generateSecret();

        System.out.println("Unlock the door with a three-digit code.");
        Console.Continue();

        Graphics.DiamondsTitle();
        System.out.println("\t\t\tStart guessing!\n");
        System.out.println("\tDigit 1: [ ? ]   Digit 2: [ ? ]   Digit 3: [ ? ]");

        Instant start = Instant.now();
        boolean unlocked = false;

        while (true) {
            long elapsed = Duration.between(start, Instant.now()).getSeconds();
            long remaining = timeLimit - elapsed;

            if (remaining <= 0) {
                System.out.println("\n================================================================");
                System.out.println("\nTime's up! The doors remain locked...\n");
                System.out.println(Console.RED + Console.BOLD + "GAME OVER" + Console.RESET);
                Console.Continue();
                unlocked = false;
                break;
            }

            System.out.println("\n================================================================");
            System.out.println("\nTime left: " + Math.max(0, remaining) + " seconds");
            System.out.print("Enter your 3-digit code: ");
            String guess = sc.nextLine().trim();

            if (!guess.matches("\\d{" + CODE_LEN + "}")) {
                System.out.println("Please enter exactly 3 digit numbers.");
                continue;

            }
            if (guess.startsWith("0")) {
                System.out.println("Code cannot start with 0.");
                continue;
            }

            if (guess.equals(secret)) {
                displayResult(
                        new String[] { Console.GREEN + "green" + Console.RESET, Console.GREEN + "green" + Console.RESET,
                                Console.GREEN + "green" + Console.RESET });
                System.out.println("\n================================================================");
                System.out.println(Console.GREEN + Console.BOLD + "\nCONGRATULATIONS!" + Console.RESET);
                System.out.println("Time taken: " + remaining + " seconds");
                Console.Continue();
                Graphics.DiamondsTitle();
                Graphics.GraphicsDoorUnlocked();
                System.out.println(Console.BOLD + "\nSYSTEM:" + Console.RESET
                        + " Congratulations, Player! You successfully unlocked the door and escaped Deadlock.");
                Console.Continue();
                Graphics.DiamondsTitle();
                System.out.println(Console.BOLD + "SYSTEM: " + Console.RESET
                        + "You have earned the Diamonds Card, proof of your intellect and logical skill.");
                unlocked = true;
                Graphics.DiamondsSymbol();
                Console.MainMenu();
                return true;

            } else {
                String[] marks = compareGuess(guess, secret);
                displayResult(marks);
            }
        }

        if (!unlocked) {
            Graphics.DiamondsTitle();
            Graphics.GraphicsDoorLocked();
            System.out.println("\nThe code was: " + Console.GREEN + secret + Console.RESET);
            System.out.println(
                    Console.BOLD + "\nSYSTEM:" + Console.RESET
                            + " You failed the game and the door remains locked. You are trapped!");
            Console.MainMenu();
            return false;
        }
        return false;
    }

    private String[] compareGuess(String guess, String code) {
        // Two-pass marking to handle duplicates correctly:
        // 1) Mark greens exactly matched; 2) Mark yellows using counts of remaining
        // digits.
        String[] marks = new String[CODE_LEN];
        int[] counts = new int[10];
        char[] g = guess.toCharArray();
        char[] c = code.toCharArray();

        for (int i = 0; i < CODE_LEN; i++) {
            if (g[i] == c[i]) {
                marks[i] = Console.GREEN + "green" + Console.RESET;
            } else {
                counts[c[i] - '0']++;
            }
        }
        for (int i = 0; i < CODE_LEN; i++) {
            if (marks[i] != null)
                continue;
            int d = g[i] - '0';
            if (d >= 0 && d <= 9 && counts[d] > 0) {
                marks[i] = Console.YELLOW + "yellow" + Console.RESET;
                counts[d]--;
            } else {
                marks[i] = Console.RED + "red" + Console.RESET;
            }
        }
        return marks;
    }

    private int rollDifficultySeconds() {
        Console.clearConsole();
        Graphics.DiamondsTitle();
        Console.typewriter("Rolling difficulty...\n\n", 50);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        int pick = rng.nextInt(3);
        switch (pick) {
            case 0:
                System.out.println("You got " + Console.BOLD + "EASY " + Console.RESET + Console.GREEN + "(90s)\n"
                        + Console.RESET);
                return 90;
            case 2:
                System.out.println(
                        "You got " + Console.BOLD + "HARD " + Console.RESET + Console.RED + "(30s)\n" + Console.RESET);
                return 30;
            case 1:
            default:
                System.out.println("You got " + Console.BOLD + "NORMAL " + Console.RESET + Console.YELLOW + "(60s)\n"
                        + Console.RESET);
                return DEFAULT_TIME_LIMIT;
        }
    }

    private String generateSecret() {
        int[] ones = { 6, 8 };
        for (int o : ones) {
            for (int t = 1; t <= 4; t++) {
                int h = 2 * t;
                if (h > 9)
                    continue;
                if (h + t + o == 14) {
                    return "" + h + t + o; // e.g., 428
                }
            }
        }
        return "428";
    }

    private void displayResult(String[] marks) {
        System.out.println(
                "Digit 1: [ " + marks[0] + " ]   Digit 2: [ " + marks[1] + " ]   Digit 3: [ " + marks[2] + " ]");
    }

    // *******************************************************************************************************************************************/
    private void printHint() {
        Graphics.DiamondsTitle();
        System.out.println("Be guided by the given hints:");
        System.out.println(
                "You have to find a " + Console.BOLD + "three-digit" + Console.RESET + " even number that satisfies:");
        System.out.println("\t- Hundreds digit is twice the tens digit.");
        System.out.println("\t- The sum of all digits is 14.");
        System.out.println("\t- The ones digit is even and GREATER THAN 4.");
    }

}
