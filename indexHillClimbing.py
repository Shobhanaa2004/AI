import random
def calculate_cost(path, graph):
    cost = 0
    for i in range(len(path) - 1):
        cost += graph[path[i]][path[i+1]]
    return cost
def get_neighbors(path):
    neighbors = []
    for i in range(1, len(path) - 1):  
        for j in range(i+1, len(path)):
            neighbor = path.copy()
            neighbor[i], neighbor[j] = neighbor[j], neighbor[i]
            neighbors.append(neighbor)
    return neighbors
def hill_climbing(graph, start_path):
    current_path = start_path
    current_cost = calculate_cost(current_path, graph)
    print(f"Initial path: {current_path}")
    print(f"Initial cost: {current_cost}")
    while True:
        neighbors = get_neighbors(current_path)
        found_better = False
        for neighbor in neighbors:
            cost = calculate_cost(neighbor, graph)
            if cost < current_cost:
                current_path = neighbor
                current_cost = cost
                print(f"Better path found: {current_path}")
                print(f"Updated cost: {current_cost}")
                found_better = True
                break  
        if not found_better:
            break
    return current_path, current_cost
n = int(input("Enter number of nodes in the graph: "))
graph = [[0 for _ in range(n)] for _ in range(n)]
print("Enter the weights between nodes (use 0 if not connected):")
for i in range(n):
    for j in range(i+1, n):
        w = int(input(f"Is {i} connected to {j}? "))
        graph[i][j] = w
        graph[j][i] = w
print("\nAdjacency Matrix:")
for row in graph:
    print(' '.join(map(str, row)))
initial_path = list(range(n))
random.shuffle(initial_path)
final_path, final_cost = hill_climbing(graph, initial_path)

