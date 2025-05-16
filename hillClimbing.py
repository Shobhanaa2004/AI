import random

# Graph represented as an adjacency matrix (cost from city i to city j)
graph_matrix = [
    [0, 2, 3],
    [1, 0, 2],
    [5, 7, 0]
]

# Function to calculate the total cost of a given path (cycle)
def calculate_cycle_cost(graph, path):
    total_cost = 0
    for i in range(len(path) - 1):
        total_cost += graph[path[i]][path[i + 1]]
    total_cost += graph[path[-1]][path[0]]  # Return to starting city
    return total_cost

# Hill Climbing algorithm to approximate the shortest tour
def hill_climbing_tsp(graph):
    num_cities = len(graph)
    
    current_path = list(range(num_cities))  # Initial path [0, 1, 2, ...]
    random.shuffle(current_path)            # Random starting order

    current_cost = calculate_cycle_cost(graph, current_path)

    for _ in range(500):  # Perform 500 iterations
        candidate_path = current_path[:]
        random.shuffle(candidate_path)  # Try a new random path
        candidate_cost = calculate_cycle_cost(graph, candidate_path)

        # If the new path is better, accept it
        if candidate_cost < current_cost:
            current_path = candidate_path
            current_cost = candidate_cost

    return current_path, current_cost

# Run the algorithm
best_path, best_cost = hill_climbing_tsp(graph_matrix)

# Output the best result found
print("Best path found:", best_path)
print("Cost of best path:", best_cost)
