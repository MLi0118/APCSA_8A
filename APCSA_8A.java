import java.util.Scanner;

public class APCSA_8A {
    // declare variables
    private static final int sizeBoard = 3;
    private static char[][] board = new char[sizeBoard][sizeBoard];
    private static char currentPlayer = 'X';
    private static int num = 1;

    // main method
    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    // setup array board
    public static void initializeBoard() {
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // display array board
    public static void displayBoard() {
        System.out.println("Round " + num);
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                System.out.print("[" +board[i][j] + "]" + " ");
            }
            System.out.println();
        }
    }

    // start game
    public static void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row,column): ");
            String move = scanner.nextLine();

            if (isValidMove(move)) {
                int row = Integer.parseInt(move.substring(0, 1));
                int col = Integer.parseInt(move.substring(2, 3));

                if (board[row][col] == ' ') {
                    board[row][col] = currentPlayer;

                    if (checkWin()) {
                        displayBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        if (playAgain(scanner)) {
                            resetGame();
                        } else {
                            break;
                        }
                    }

                    if (isBoardFull()) {
                        displayBoard();
                        System.out.println("The game is a draw!");
                        if (playAgain(scanner)) {
                            resetGame();
                        } else {
                            break;
                        }
                    }

                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    num++;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }

        scanner.close();
    }

    // check if the spot is already been taken
    public static boolean isValidMove(String move) {
        return move.matches("[0-2],[0-2]");
    }

    // check if there is a win or no
    public static boolean checkWin() {
        for (int i = 0; i < sizeBoard; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    // check for a draw
    public static boolean isBoardFull() {
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // play again?
    public static boolean playAgain(Scanner scanner) {
        System.out.println("Do you want to play again? (yes/no): ");
        String re = scanner.nextLine().toLowerCase();
        return re.equals("yes");
    }

    // if play again, reset the game
    public static void resetGame() {
        initializeBoard();
        currentPlayer = 'X';
        num = 1;
    }
}