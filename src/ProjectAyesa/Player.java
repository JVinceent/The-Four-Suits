package ProjectAyesa;
import ProjectAyesa.Core.Console;
import ProjectAyesa.Core.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class to manage the user's state within The Four Suits game.
 * It encapsulates player data like username, visas, and collected cards.
 */
public class Player {
    // Encapsulated fields
    private String username;
    private int visas;
    private List<String> cards;




    /**
     * Constructor initializes a new player with a username and default visas.
     * @param username The name entered by the player.
     */
    public Player(String username) {
        this.username = username;
        this.visas = 2; // Starts with 2 Visas
        this.cards = new ArrayList<>();
    }




    // Public Getters (Accessors)
    public String getUsername() { return username; }
    public int getVisas() { return visas; }
    public int getCardCount() { return this.cards.size(); }

    /**
     * Decrements the player's visa count.
     * In a full game, this would check for game over.
     */
/**
     * Decrements the player's visa count.
     * Returns true if the player is still alive (visas > 0), false if they are eliminated (visas == 0).
     */
   
     public boolean isGameOver(){
        return this.visas <= 0;
    }
    /**
     * Checks if the player has already acquired the card for a specific suit.
     * Searches the player's collection for a card name containing the suit string.
     * @param suit The name of the suit to check (e.g., "HEARTS", "SPADES").
     * @return true if a card matching the suit is found, false otherwise.
     */
    public boolean hasCard(String suit) {
        if (this.cards.isEmpty()) return false;
       
        String searchKey = suit.toUpperCase();
       
        for (String card : this.cards) {
            // Check if the stored card string (e.g., "Tondo (Spade)") contains the suit (e.g., "SPADE")
            if (card.toUpperCase().contains(searchKey)) {
                return true;
            }
        }
        return false;
    }

    public boolean loseVisa() {
        if (this.visas > 0) {
            this.visas--;

            if (this.visas > 0) {
                Graphics.mainTitle();
                // Case 1: Visas went from 2 to 1, or 1 to 0 (but we handle 0 below)
                System.out.println(Console.RED + Console.BOLD + "ALERT:" + Console.RESET + " You lost 1 Visa. \nRemaining Visas: " + this.visas + "\n");
                Console.Menu();
                return true; // Player is still alive
            } else { // this.visas is now 0
                Graphics.mainTitle();
                // Case 2: Visas went from 1 to 0. Game Over.
                System.out.println(Console.RED + Console.BOLD + "!!! NO VISAS LEFT. YOU ARE ELIMINATED. GAME OVER. !!!" + Console.RESET + "\n");
                Console.Menu();
                return false; // Player is eliminated
            }
        } else {
            Graphics.mainTitle();
            // Case 3: Visas was already 0 when this method was called.
            System.out.println(Console.RED + Console.BOLD + "!!! NO VISAS LEFT. YOU ARE ELIMINATED. GAME OVER. !!!" + Console.RESET);
            Console.Menu();
            return false; // Already eliminated
        }
    }

/**
     * Updates the player's profile (visas and cards) based on the game result.
     * @param suit The card suit associated with the game (e.g., "HEARTS").
     * @param wonGame True if the player won the game, false if they lost.
     */
/**
     * Updates player stats based on win/loss.
     */
    public void updateProfile(String suit, boolean wonGame) {
        if (wonGame) {
            // Internal method handles the naming
            String cardDescription = getCardNameForSuit(suit);
            addCard(cardDescription);
        } else {
            loseVisa();
        }
    }
// --- Private Helpers (Encapsulation) ---

    // This ensures consistency. Main.java sends "SPADES", we save "Tondo (Spade)"
    private String getCardNameForSuit(String suit) {
        switch (suit.toUpperCase()) {
            case "SPADES":   return "Tondo (Spades)";
            case "HEARTS":   return "Divisoria (Hearts)";
            case "DIAMONDS": return "Malacañang (Diamonds)";
            case "CLUBS":    return "MRT (Clubs)";
            default:         return suit + " Card";
        }
    }
/**
     * Adds a card to the player's collection, ensuring no duplicates.
     * @param card The descriptive name of the card (e.g., "Tondo (Spade)").
     */
    public void addCard(String cardName) {
       
        if (!this.cards.contains(cardName)) { // Prevent duplicates
            this.cards.add(cardName);
            // Graphics.mainTitle();
            // System.out.println("CARD GRANTED: " + Console.BOLD + cardName + Console.RESET + "\n");
            // Console.Menu();
        } else {
            // Graphics.mainTitle();
            // System.out.println("CARD ALREADY HELD: " + Console.BOLD + cardName + Console.RESET + "\n");
            // Console.Menu();
        }
    }
    /**
     * Displays the current player status to the console, following the game script format.
     */
    public void displayInfo() {
        // Calculate extra visas (since the script says "Status: 1 Extra Visa" when visas=2)
        int remainingLives = this.visas;
        String status = remainingLives > 0 ? remainingLives + " Extra Visa(s)" : "None";
        String cardList = this.cards.isEmpty() ? "None" : String.join("\n\t", this.cards);

        Console.clearConsole();
        Graphics.mainTitle();
        System.out.println("Player Information\n");
        System.out.println("Name: " + this.username);
        System.out.println("Status: " + status);
        System.out.println("Cards: \n\t" + cardList);
    }
}
