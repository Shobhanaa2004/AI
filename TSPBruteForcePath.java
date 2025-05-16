import java.util.Scanner;

public class TSPBruteForcePath {
    static int N; // Number of cities
    static int[][] graph; // Adjacency matrix
    static boolean[] visited;
    static int minCost = Integer.MAX_VALUE;
    static int[] bestPath; // To store the path with minimum cost
    static int[] currentPath; // To track current path

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of cities
        System.out.print("Enter number of cities: ");
        N = sc.nextInt();

        // Input adjacency matrix
        graph = new int[N][N];
        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[N];
        bestPath = new int[N + 1]; // Include return to start
        currentPath = new int[N];

        visited[0] = true; // Start from city 0
        currentPath[0] = 0; // Start path with city 0

        // Start TSP
        tsp(0, 1, 0);

        // Output
        System.out.println("\nMinimum cost of visiting all cities: " + minCost);
        System.out.print("Path: ");
        for (int i = 0; i <= N; i++) {
            System.out.print((bestPath[i] + 1) + (i == N ? "\n" : " -> "));
        }
    }

    // Brute-force TSP using backtracking
    static void tsp(int currCity, int count, int cost) {
        // Base case: all cities visited, return to start
        if (count == N && graph[currCity][0] != 0) {
            int totalCost = cost + graph[currCity][0];
            if (totalCost < minCost) {
                minCost = totalCost;

                // Copy current path + return to start
                for (int i = 0; i < N; i++) {
                    bestPath[i] = currentPath[i];
                }
                bestPath[N] = 0; // Return to starting city
            }
            return;
        }

        // Try all cities
        for (int i = 0; i < N; i++) {
            if (!visited[i] && graph[currCity][i] != 0) {
                visited[i] = true;
                currentPath[count] = i; // Add city to current path
                tsp(i, count + 1, cost + graph[currCity][i]);
                visited[i] = false; // Backtrack
            }
        }
    }
}
