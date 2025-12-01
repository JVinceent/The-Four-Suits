package ProjectAyesa.Core;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStream;


public class Console {
    public static final String RED = "\033[91m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BOLD = "\033[1m";
    public static final String RESET = "\033[0m";

    public static final String R = "\u001B[0m";

    public static final String O = "\u001B[38;5;208m";    
    public static final String DR = "\u001B[31m";        
    public static final String DBB = "\u001B[34m";      
    public static final String DB = "\u001B[38;5;19m";
    public static final String M = "\u001B[47m\u001B[38;5;88m";
    public static final String B = "\u001B[30m";

    public static final String HIDE_CURSOR = "\033[?25l";
    public static final String SHOW_CURSOR = "\033[?25h";
    private static boolean pendingTypewriterSkip = false;

    public static final Scanner SC = new Scanner(System.in);
   
    public static void clearConsole() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    public static void flushPendingSkip() {
        if (!pendingTypewriterSkip) return;
        try {
            InputStream in = System.in;
            while (in.available() > 0) {
                in.read();
            }
        } catch (IOException ignore) {
        }
        pendingTypewriterSkip = false;
    }

    public static void Continue() {
            flushPendingSkip();
            System.out.print("\n[Press any ENTER to Continue...]");
            SC.nextLine();
            clearConsole();
    }
    public static void Menu() {
            flushPendingSkip();
            System.out.print("\n[Press ENTER to return to the Menu...]");
            SC.nextLine();
            clearConsole();
    }
    public static void MainMenu() {
            flushPendingSkip();
            System.out.print( "\n[Press ENTER to Return to the Main Menu and view your updated Player Profile...]");
            SC.nextLine();
            clearConsole();
    }
    public static void Start() {
        flushPendingSkip();
        System.out.print("[Press ENTER to start...]");
        SC.nextLine();
        clearConsole();
    }
    public static void Destination() {
        flushPendingSkip();
        System.out.print("[Press ENTER to choose another destination...]");
        SC.nextLine();
        clearConsole();
    }
    public static void InvalidA() {
        Console.clearConsole();
        Graphics.mainTitle();
        System.out.println("Invalid choice. Try again...");
    }

    public static void InvalidB() {
        Console.clearConsole();
        Graphics.mainTitle();
        System.out.println("Invalid choice. Please select 1 or 2...");
        Console.Menu();
    }
    public static void InvalidC() {
        Console.clearConsole();
        Graphics.mainTitle();
        System.out.println("Invalid choice. Please select 1, 2, or 3...");
        Console.Menu();
    }
    public static void InvalidChoice() {
        System.out.println("Invalid choice. Try again...\n");
        Console.Menu();
    }

        // 5. Typewriter Effect (Common to all games)
    public static void typewriter(String text, int delayMs) {
        boolean skipDelay = false;
        try {
            System.out.print(HIDE_CURSOR);
            for (char c : text.toCharArray()) {
                if (!skipDelay && System.in.available() > 0) {
                    skipDelay = true;
                    pendingTypewriterSkip = true;
                }
                System.out.print(c);
                if (!skipDelay && c != '\n' && c != '\t') {
                    Thread.sleep(delayMs);
                }
            }
            System.out.flush();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException ignore) {
        } finally {
            System.out.print(SHOW_CURSOR);
        }
    }
}
