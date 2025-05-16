import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printBoard();

        while (true) {
            // Player move
            System.out.println("Enter your move (row and column: 0 1 2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (board[row][col] != ' ') {
                System.out.println("This cell is already occupied! Try again.");
                continue;
            }
            board[row][col] = 'X';

            printBoard();
            if (isWinner('X')) {
                System.out.println("You win!");
                break;
            }
            if (isFull()) {
                System.out.println("It's a tie!");
                break;
            }

            // Computer move
            System.out.println("Computer's turn:");
            int[] move = findBestMove();
            board[move[0]][move[1]] = 'O';
            printBoard();

            if (isWinner('O')) {
                System.out.println("Computer wins!");
                break;
            }
            if (isFull()) {
                System.out.println("It's a tie!");
                break;
            }
        }
        scanner.close();
    }

    // Print the current board
    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check for winner
    static boolean isWinner(char player) {
        // Rows, Columns, and Diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player)
                return true;

            if (board[0][i] == player &&
                board[1][i] == player &&
                board[2][i] == player)
                return true;
        }
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player)
            return true;

        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player)
            return true;

        return false;
    }

    // Check if board is full
    static boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    // Find the best move for computer using minimax
    static int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Try every empty cell
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int score = minimax(false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    // Minimax function to evaluate board
    static int minimax(boolean isMaximizing) {
        if (isWinner('O')) return 1;
        if (isWinner('X')) return -1;
        if (isFull()) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        best = Math.max(best, minimax(false));
                        board[i][j] = ' ';
                    }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        best = Math.min(best, minimax(true));
                        board[i][j] = ' ';
                    }
            return best;
        }
    }
}
  
