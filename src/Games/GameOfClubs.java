package ProjectAyesa.Games;
import ProjectAyesa.Core.Game; // 1. Import Parent
import ProjectAyesa.Core.Console;
import ProjectAyesa.Core.Graphics;
import java.util.*;


public class GameOfClubs extends Game {
    private char[] board;
    private Queue<Integer> systemMoves;
    private Queue<Integer> playerMoves;
    private Scanner sc;
    private boolean systemIsX; // decided by RPS
    private static final String gameName = "Clubs - Terminal Three";
    private static final String gameDesc = 
            "\tIn this game examines your strategic skill." +
            "\n\tBe the first player to achieve three of your marks in a row (horizontally, vertically, or diagonally) on the 3x3 grid on the train doors." + 
            Console.BOLD + "\n\nGood luck.\n" + Console.RESET;

    private static final String gameRules = 
        "The rules are simple:" +   
        "\n\t- You must enter the number (1-9) corresponding to the square where you wish to place your mark (X or O)." +
        "\n\t- You can only have " + Console.BOLD + "three of your Marks"+ Console.RESET + " on the board at any time." +
        "\n\t- The First 3 Moves, place your Mark anywhere on the empty grid, a " + Console.BOLD + "standard Tic-Tac-Toe." + Console.RESET +
        "\n\t- Starting on turn 4, your oldest mark is automatically removed from the board.\n" +
        "\nUse the board below as your guide: \n" +
        "\n\t\t\t   ╔════════════════════════════╗" +
        "\n\t\t\t   ║        ║         ║         ║" +
        "\n\t\t\t   ║    1   ║    2    ║    3    ║" +
        "\n\t\t\t   ║        ║         ║         ║" +
        "\n\t\t\t   ║════════║═════════║═════════║" +
        "\n\t\t\t   ║        ║         ║         ║" +
        "\n\t\t\t   ║    4   ║    5    ║    6    ║" +
        "\n\t\t\t   ║        ║         ║         ║" +
        "\n\t\t\t   ║════════║═════════║═════════║" +
        "\n\t\t\t   ║        ║         ║         ║" +
        "\n\t\t\t   ║    7   ║    8    ║    9    ║" +
        "\n\t\t\t   ║        ║         ║         ║" +
        "\n\t\t\t   ╚════════════════════════════╝";


    public GameOfClubs() {
        super(gameName, gameDesc, gameRules);
        this.sc = new Scanner(System.in);
        this.board = new char[10]; // 1-9 cells, index 0 unused
        this.systemMoves = new LinkedList<>();
        this.playerMoves = new LinkedList<>();
    }

    @Override
    public boolean play(){
        Graphics.ClubsTitle();
        Console.typewriter("You are trapped on the brightly-lit MRT-3 platform, the air is thick with ozone, exhaust, and the oppressive energy of perpetual rush hour.\n", 40);
        Console.Continue();

        while (true) {
            Graphics.ClubsTitle();
            System.out.println(Console.BOLD + "SYSTEM: " + Console.RESET + "Welcome, Player. You have entered the game of " + Console.BOLD + getGameName() + Console.RESET);  
            System.out.println(getGameDesc());
            System.out.println("[1] - Rules");
            System.out.println("[2] - Play");
            System.out.print("\nEnter your choice: ");
            String choice = sc.nextLine().trim();
            Console.clearConsole();
            switch (choice) { 
                case "1":
                    Graphics.ClubsTitle();
                    System.out.println(getGameRules());
                    Console.Menu();
                    break;
                case "2":
                    return startInternalGame();
                default:
                    Graphics.ClubsTitle();
                    Console.InvalidChoice();
                    break;
            }
        }
    }
    private boolean startInternalGame(){
        Console.clearConsole();
        playRPS(); // decides systemIsX    
        Arrays.fill(board,' ');
        System.out.println("\n===============================================\n");
        System.out.println(systemIsX ? "Game Master is X and will go first..." : "You are X! You go first.");
        Console.Continue();
        return playNow();
    }
   
    private void playRPS() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();
        while (true) {
            Graphics.ClubsTitle();
            System.out.println("Before we begin the match, we'll play Rock, Paper, Scissors to decide which player will take the " + Console.RED + "X mark.\n" + Console.RESET);
            System.out.print("Choose [1] Rock [2] Paper [3] Scissors: ");
            int playerChoice;
            try {
                playerChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1-3).");
                continue;
            }
            if (playerChoice < 1 || playerChoice > 3) {
                System.out.println("Invalid choice. Try again.\n");
                continue;
            }
            int systemChoice = rand.nextInt(3) + 1;
            System.out.println("\n===============================================\n");
            System.out.println("You picked: " + choices[playerChoice - 1]);
            System.out.println("Game Master picked: " + choices[systemChoice - 1]);
            String winner = determineWinner(playerChoice, systemChoice);

            if (winner.equals("Draw")) {
                System.out.println("It's a draw! Let's play again...\n");
                sc.nextLine();
                Console.Continue();
                continue;
            } else if (winner.equals("Player")) {
                System.out.println("You win! You will be X.");
                systemIsX = false;
                sc.nextLine();
                break;
            } else {
                System.out.println("Game Master wins! It will be X.");                
                systemIsX = true;
                sc.nextLine();
                break;
            }
        }
    }

    private String determineWinner(int player, int system) {
        if (player == system) return "Draw";
        if ((player == 1 && system == 3) || (player == 2 && system == 1) || (player == 3 && system == 2))
            return "Player";
        return "Game Master";
    }

    private boolean playNow() {
        System.out.println();
        Graphics.ClubsTitle();
        boolean systemTurn = systemIsX; // X always starts
        while (true) {
            if (systemTurn) {
                systemMove();
            } else {
                playerMove();
            }
            if (checkWin('X') || checkWin('O')) {
                printBoard();
                boolean systemWon = (checkWin('X') && systemIsX) || (checkWin('O') && !systemIsX);
                boolean playerWon = (checkWin('X') && !systemIsX) || (checkWin('O') && systemIsX);
                if (systemWon) {
                    System.out.println("\n" + Console.RED + Console.BOLD + "GAME OVER" + Console.RESET);
                    Console.MainMenu();
                    return false;
                } else if (playerWon) {
                    System.out.println(Console.BOLD + "\nSystem: " + Console.RESET + "Congratulations, Player! You mastered the rapid, shifting flow of the Metro.");
                    Console.Continue();
                    Graphics.ClubsTitle();
                    System.out.println(Console.BOLD + "SYSTEM: " + Console.RESET + "You have escaped The Terminal Three and earned the Club Card - proof of your strategic skill.\n");
                    Graphics.ClubsSymbol();
                    Console.MainMenu();
                    return true;
                }
                break;
            }
            systemTurn = !systemTurn;
        }
        return false;
    }
    private void printBoard() {
        System.out.println("\t\t\t   ╔═════════════════════════════╗");
        System.out.println("\t\t\t   ║         ║         ║         ║");
        System.out.printf("\t\t\t   ║    %c    ║    %c    ║    %c    ║\n", board[1], board[2], board[3]);
        System.out.println("\t\t\t   ║         ║         ║         ║");
        System.out.println("\t\t\t   ║═════════║═════════║═════════║");
        System.out.println("\t\t\t   ║         ║         ║         ║");
        System.out.printf("\t\t\t   ║    %c    ║    %c    ║    %c    ║\n", board[4], board[5], board[6]);
        System.out.println("\t\t\t   ║         ║         ║         ║");
        System.out.println("\t\t\t   ║═════════║═════════║═════════║");
        System.out.println("\t\t\t   ║         ║         ║         ║");
        System.out.printf("\t\t\t   ║    %c    ║    %c    ║    %c    ║\n", board[7], board[8], board[9]);
        System.out.println("\t\t\t   ║         ║         ║         ║");
        System.out.println("\t\t\t   ╚═════════════════════════════╝");
    }

    private void playerMove() {
        printBoard();
        char mark = systemIsX ? 'O' : 'X';
        System.out.printf(Console.GREEN + "\nYour turn (%c)" + Console.RESET + "\n", mark);

        int move = -1;

        while (true) {
            System.out.print("Enter your move (1-9): ");
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            try {
                // Use only the first character for parsing if they type multiple things
                String parsedInput = line.substring(0, 1);
                move = Integer.parseInt(parsedInput);

                if (move >= 1 && move <= 9) {
                    if (board[move] == ' ') {
                        // Valid move found
                        break;
                    } else {
                        System.out.println(Console.RED + "Cell " + move + " is already occupied. Try again." + Console.RESET);
                    }
                } else {
                    System.out.println(Console.RED + "Invalid move. Please enter a number between (1-9)." + Console.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(Console.RED + "Invalid input. Please enter a number (1-9)." + Console.RESET);
            }
        }
        makeMove(move, mark, playerMoves);
    }

    private void systemMove() {
        char mark = systemIsX ? 'X' : 'O';
        int move = findBestMove(mark);
        makeMove(move, mark, systemMoves);
        System.out.println("\u001B[31m\nGame Master placed " + mark + " on cell " + move + ".\u001B[0m");
    }

    private void makeMove(int move, char symbol, Queue<Integer> queue) {
        if (queue.size() == 3) {
            int old = queue.poll();
            board[old] = ' ';
        }
        board[move] = symbol;
        queue.add(move);
    }

    private boolean checkWin(char c) {
        int[][] winCombos = {
            {1,2,3}, {4,5,6}, {7,8,9},
            {1,4,7}, {2,5,8}, {3,6,9},
            {1,5,9}, {3,5,7}
        };
        for (int[] line : winCombos) {
            if (board[line[0]] == c && board[line[1]] == c && board[line[2]] == c)
                return true;
        }
        return false;
    }

    private int findBestMove(char mark) {
        char opponent = (mark == 'X') ? 'O' : 'X';

        for (int i = 1; i <= 9; i++) {
            if (board[i] == ' ') {
                board[i] = mark;
                if (checkWin(mark)) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (board[i] == ' ') {
                board[i] = opponent;
                if (checkWin(opponent)) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }

        if (board[5] == ' ') return 5;

        int[] priority = {1, 3, 7, 9, 2, 4, 6, 8};
        for (int pos : priority) {
            if (board[pos] == ' ') return pos;
        }
        return 1;
    }
}