package ProjectAyesa.Core;
// 1. ABSTRACTION: "abstract" means this is a concept, not a specific object yet.
public abstract class Game {

    // 2. ENCAPSULATION: "private" means only this class can touch these variables directly.
    private String gameName;
    private String gameDesc;
    private String gameRules;


    // Constructor: This ensures every game MUST have a name and rules to exist.
    public Game(String gameName, String gameDesc, String gameRules) {
        this.gameName = gameName;
        this.gameDesc = gameDesc;
        this.gameRules = gameRules;
    }

    // ENCAPSULATION: Getters allow outsiders to READ the data, but not CHANGE it.
    public String getGameName() {
        return gameName;
    }

    public String getGameDesc() {
        return gameDesc;
    }
    
    public String getGameRules() {
        return gameRules;
    }
    // 3. POLYMORPHISM & ABSTRACTION:
    // This forces every child class (Hearts, Spades, etc.) to create their own version of "play".
    // The "boolean" return type will represent Win (true) or Loss (false).
    public abstract boolean play();
}