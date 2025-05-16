import java.util.Scanner;

public class NQueens {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of sessions (N): ");
        int N = scanner.nextInt();
        int[] board = new int[N]; // board[i] = column position of queen in row i
        solveNQueens(board, 0, N);
        scanner.close();
    }

    // Backtracking function
    public static boolean solveNQueens(int[] board, int row, int N) {
        if (row == N) {
            printBoard(board, N);
            return true; // stop at first solution
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                if (solveNQueens(board, row + 1, N))
                    return true;
            }
        }

        return false;
    }

    // Check if the queen can be placed safely
    public static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || 
                Math.abs(board[i] - col) == Math.abs(i - row))
                return false;
        }
        return true;
    }

    // Display only the grid
    public static void printBoard(int[] board, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }
    }
}
