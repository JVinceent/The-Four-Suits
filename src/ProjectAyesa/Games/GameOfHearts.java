package ProjectAyesa.Games;
import ProjectAyesa.Core.Game; // 1. Import the parent class
import ProjectAyesa.Core.Graphics;
import ProjectAyesa.Core.Console;
import java.util.Random;
import java.util.Scanner;

// 2. INHERITANCE: Extends the abstract base class
public class GameOfHearts extends Game {
    // 3. ENCAPSULATION: Fields are private and non-static
    private Scanner sc;
    private Random rand;
    private static final String gameName = "Hearts - Divisoria's Soft Spot";
    private static final String gameDesc = 
            "\tThis test examines your psychological strength and decision-making under pressure." +
            "\n\tIn this game, you are paired with the Game Master whose intentions are unknown." +
            "\n\tBoth players must choose to TRUST or BETRAY." +
            "\n\tYour survival depends on predicting the Game Master's choice. Choose wisely." +
            "\n" + (Console.BOLD + "\nGood luck.\n" + Console.RESET);
    private static final String gameRules =
        "The rules are simple:" +
        "\n\t- You and the Game Master have two cards in hand: one is a HEART for TRUST, and the other is a DAGGER for BETRAY." +
        "\n\t- If BOTH TRUST, then both survive." +
        "\n\t- If ONE BETRAYS, the betrayer survives; the trusting one is eliminated." +
        "\n\t- If BOTH BETRAY, then both are eliminated.";

    // Constructor
    public GameOfHearts() {
        // Pass the Name and Rules summary to the Parent Game class
        super(gameName, gameDesc, gameRules);
        this.sc = new Scanner(System.in);
        this.rand = new Random();
    }
// 4. POLYMORPHISM: Implementation of the abstract method
    @Override
    public boolean play() {
        Graphics.HeartsTitle();
        Console.typewriter("The usual chaos is replaced by an eerie silence. People move, but no one speaks.\n", 50);
        Console.Continue();

        while (true) {
            Graphics.HeartsTitle();
            System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " Welcome, Player. You have entered the game of " + Console.BOLD + getGameName() + " - Divisoria's Soft Spot." + Console.RESET);
            System.out.println(getGameDesc());
            System.out.println("[1] - Rules");
            System.out.println("[2] - Play");
            System.out.print("\nEnter your choice: ");
            String choice = sc.nextLine().trim();
            Console.clearConsole();




            switch (choice) {
                case "1":
                    Graphics.HeartsTitle();
                    System.out.println(getGameRules());
                    displayCardsSideBySide("trust", "betray");
                    Console.Menu();
                    break;
                case "2":
                    return startInternalGame();
                default:
                    Graphics.HeartsTitle();
                    Console.InvalidChoice();
                    break;
            }
        }
    }
   // ENCAPSULATION: Hidden logic for the actual gameplay
    // (Renamed from playGame to startInternalGame to avoid confusion with the public play())
    private boolean startInternalGame(){
        Graphics.HeartsTitle();
        String player = null;
        System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " What do you value more: survival, or the slim hope of shared mercy?\n");
   
        displayCardsSideBySide("trust", "betray");




        while (true) {
            System.out.print("\nYour choice (TRUST / BETRAY): ");
            player = sc.nextLine().trim().toLowerCase();




            if (player.equals("trust") || player.equals("betray")) {
                break;
            }
            System.out.println("Invalid choice. Please type TRUST or BETRAY.");
        }




        String[] choices = {"trust", "betray"};
        String ai = choices[rand.nextInt(2)];
        Console.clearConsole();
        Graphics.HeartsTitle();
       
        Console.typewriter("SYSTEM: Decision locked. Revealing choices...\n", 50);
        try { Thread.sleep(1000); } catch (Exception e) {}




        boolean playerTrust = player.equals("trust");
        boolean aiTrust = ai.equals("trust");




        String playerLabel = playerTrust ? Console.GREEN + "TRUST" + Console.RESET : Console.RED + "BETRAY" + Console.RESET;
        String aiLabel = aiTrust ? Console.GREEN + "TRUST" + Console.RESET : Console.RED + "BETRAY" + Console.RESET;




        System.out.println("\n======================================");
        System.out.println("\n\tYour choice: " + playerLabel);
        System.out.println("\tGame Master: " + aiLabel);
        System.out.println("\n======================================\n");




        if ((playerTrust && aiTrust) || (!playerTrust && aiTrust)) {
            System.out.println(Console.GREEN + Console.BOLD + "CONGRATULATIONS!" + Console.RESET);
        } else {
            System.out.println(Console.RED + Console.BOLD + "GAME OVER" + Console.RESET);
        }
        Console.Continue();
        Graphics.HeartsTitle();




        if (playerTrust && aiTrust) {
            System.out.println("\t\tYour Choice\t\t  Game Master's Choice");
            displayCardsSideBySide(player, ai);
            System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " Congratulations, Player! Both participants trusted each other. " + Console.BOLD + "Both are safe." + Console.RESET);
            Console.Continue();
            Graphics.HeartsTitle();
            System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " You have earned the Hearts Card, a proof of psychological strength.");
            Graphics.HeartsSymbol();
            Console.MainMenu();
            return true;
        } else if (!playerTrust && aiTrust) {
            System.out.println("\t\tYour Choice\t\t  Game Master's Choice");
            displayCardsSideBySide(player, ai);
            System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " Congratulations, Player! You betrayed the player. Your action saved your life. " + Console.BOLD + "You are safe." + Console.RESET);
            Console.Continue();
            Graphics.HeartsTitle();
            System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " You have earned the Hearts Card, proof of psychological strength.");
            Graphics.HeartsSymbol();
            Console.MainMenu();
            return true;
        } else if (playerTrust) {
            System.out.println("\t\tYour Choice\t\t  Game Master's Choice");
            displayCardsSideBySide(player, ai);
            System.out.println(Console.BOLD + "SYSTEM:" + Console.RESET + " You trusted the Game Master, but they betrayed you. " + Console.BOLD + "You are Eliminated." + Console.RESET);
            Console.MainMenu();
            return false;
        } else {
            System.out.println("\t\tYour Choice\t\t  Game Master's Choice");
            displayCardsSideBySide(player, ai);
            System.out.println(Console.BOLD + "\nSYSTEM:" + Console.RESET + " Both participants betrayed each other. " + Console.BOLD + "Both are Eliminated." + Console.RESET);
            Console.MainMenu();
            return false;
        }
    }
//*************************************************************************************************************//





    private String[] heartCard(boolean isSelected) {
        String color = isSelected ? Console.GREEN : Console.RESET;
        String label = isSelected ? "TRUST" : "TRUST";
        return new String[]{
            color + "╔═════════════════════════╗" + Console.RESET + "    ",
            color + "║                         ║" + Console.RESET + "    ",
            color + "║                         ║" + Console.RESET + "    ",
            color + "║     █████     █████     ║" + Console.RESET + "    ",
            color + "║   ████████   ████████   ║" + Console.RESET + "    ",
            color + "║  ██████████ ██████████  ║" + Console.RESET + "    ",
            color + "║   ███████████████████   ║" + Console.RESET + "    ",
            color + "║    █████████████████    ║" + Console.RESET + "    ",
            color + "║      █████████████      ║" + Console.RESET + "    ",
            color + "║         ████████        ║" + Console.RESET + "    ",
            color + "║            ██           ║" + Console.RESET + "    ",
            color + "║                         ║" + Console.RESET + "    ",
            color + "║                 " + label + "   ║" + Console.RESET + "    ",
            color + "╚═════════════════════════╝" + Console.RESET + "    "
        };
    }




    private String[] daggerCard(boolean isSelected) {
        String color = isSelected ? Console.RED : Console.RESET;
        String label = isSelected ? "BETRAY" : "BETRAY";
        return new String[]{
            color + "╔═════════════════════════╗" + Console.RESET + "    ",
            color + "║            █            ║" + Console.RESET + "    ",
            color + "║           █░█           ║" + Console.RESET + "    ",
            color + "║          █░░░█          ║" + Console.RESET + "    ",
            color + "║         █░░▓░░█         ║" + Console.RESET + "    ",
            color + "║         █░░▓░░█         ║" + Console.RESET + "    ",
            color + "║         █░░▓░░█         ║" + Console.RESET + "    ",
            color + "║         █░░▓░░█         ║" + Console.RESET + "    ",
            color + "║         █░░▓░░█         ║" + Console.RESET + "    ",
            color + "║        █████████        ║" + Console.RESET + "    ",
            color + "║          █░░░█          ║" + Console.RESET + "    ",
            color + "║          █░░░█          ║" + Console.RESET + "    ",
            color + "║         ▀▀▀▀▀▀▀ " + label + "  ║" + Console.RESET + "    ",
            color + "╚═════════════════════════╝" + Console.RESET + "    "
        };
    }




    private void displayCardsSideBySide(String playerChoice, String aiChoice) {
        System.out.println();
   
        String[] leftCardLines = playerChoice.equals("trust") ?
            heartCard(true) : daggerCard(true);
   
        String[] rightCardLines = aiChoice.equals("trust") ?
            heartCard(true) : daggerCard(true);




        int maxLines = Math.max(leftCardLines.length, rightCardLines.length);




        for (int i = 0; i < maxLines; i++) {
            String line1 = (i < leftCardLines.length) ? leftCardLines[i] : "";
            String line2 = (i < rightCardLines.length) ? rightCardLines[i] : "";
            System.out.println("\t" + line1 + line2);
        }
        System.out.println();
    }
   
}





