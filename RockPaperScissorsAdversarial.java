import java.util.*;

public class RockPaperScissorsAdversarial {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Integer> userHistory = new HashMap<>(); // Track user move counts

    public static void main(String[] args) {
        userHistory.put("rock", 0);
        userHistory.put("paper", 0);
        userHistory.put("scissors", 0);

        System.out.println("Welcome to Rock, Paper, Scissors (Adversarial Strategy Mode)");

        while (true) {
            System.out.print("\nEnter your move (rock/paper/scissors or exit): ");
            String userMove = scanner.nextLine().toLowerCase();

            if (userMove.equals("exit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (!isValidMove(userMove)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Update history
            userHistory.put(userMove, userHistory.get(userMove) + 1);

            // Adversarial move
            String computerMove = getAdversarialMove();
            System.out.println("Computer chose: " + computerMove);

            // Determine winner
            String result = determineWinner(userMove, computerMove);
            System.out.println(result);
        }
    }

    static boolean isValidMove(String move) {
        return move.equals("rock") || move.equals("paper") || move.equals("scissors");
    }

    // Predict user’s next move based on frequency and counter it
    static String getAdversarialMove() {
        String predictedUserMove = getMostFrequentUserMove();

        switch (predictedUserMove) {
            case "rock":
                return "paper";     // Paper beats Rock
            case "paper":
                return "scissors";  // Scissors beats Paper
            case "scissors":
                return "rock";      // Rock beats Scissors
            default:
                return getRandomMove(); // fallback
        }
    }

    // Find most frequent user move
    static String getMostFrequentUserMove() {
        String mostFrequent = "rock"; // default
        int maxCount = -1;

        for (String move : userHistory.keySet()) {
            int count = userHistory.get(move);
            if (count > maxCount) {
                maxCount = count;
                mostFrequent = move;
            }
        }

        return mostFrequent;
    }

    static String getRandomMove() {
        String[] moves = {"rock", "paper", "scissors"};
        Random rand = new Random();
        return moves[rand.nextInt(3)];
    }

    static String determineWinner(String user, String computer) {
        if (user.equals(computer)) return "It's a tie!";

        if ((user.equals("rock") && computer.equals("scissors")) ||
            (user.equals("scissors") && computer.equals("paper")) ||
            (user.equals("paper") && computer.equals("rock"))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
}
