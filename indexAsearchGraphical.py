import heapq
def a_star_search(start, goal, graph, heuristics):
    n = len(graph)
    open_list = []
    heapq.heappush(open_list, (heuristics[start], 0, start, [start]))  # (f, g, node, path)
    closed_set = set()
    while open_list:
        f, g, current, path = heapq.heappop(open_list)
        if current == goal:
            print("Optimal path:", " -> ".join(map(str, path)))
            print("Total cost:", g)
            return
        if current in closed_set:
            continue
        closed_set.add(current)
        for neighbor in range(n):
            if graph[current][neighbor] != 0:
                new_g = g + graph[current][neighbor]
                new_f = new_g + heuristics[neighbor]
                heapq.heappush(open_list, (new_f, new_g, neighbor, path + [neighbor]))
    print("No path found")
n = int(input("Enter number of nodes: "))
print("Enter adjacency matrix (use 0 for no direct path):")
graph = []
for i in range(n):
    row = list(map(int, input(f"Row {i}: ").split()))
    graph.append(row)
heuristics = list(map(int, input("Enter heuristic values separated by space: ").split()))
start = int(input("Enter start node index (0-based): "))
goal = int(input("Enter goal node index (0-based): "))
a_star_search(start, goal, graph, heuristics)


