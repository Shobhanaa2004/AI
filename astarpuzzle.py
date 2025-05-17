import heapq

goal = {(1,0,0), (2,0,1), (3,0,2), (8,1,0), (4,1,1), (0,1,2), (7,2,0), (6,2,1), (5,2,2)}

def manhattan(s):
    return sum(abs(i - gi) + abs(j - gj)
               for i, row in enumerate(s)
               for j, v in enumerate(row) if v
               for val, gi, gj in goal if val == v)

def neighbors(s):
    i, j = next((r, c) for r in range(3) for c in range(3) if s[r][c] == 0)
    moves = []
    for dr, dc in [(-1,0),(1,0),(0,-1),(0,1)]:
        ni, nj = i + dr, j + dc
        if 0 <= ni < 3 and 0 <= nj < 3:
            t = [list(row) for row in s]
            t[i][j], t[ni][nj] = t[ni][nj], t[i][j]
            moves.append(tuple(tuple(r) for r in t))
    return moves

def astar(start):
    goal_state = ((1,2,3), (8,4,0), (7,6,5))

    pq = [(manhattan(start), 0, start, [])]
    seen = set()
    while pq:
        f, g, cur, path = heapq.heappop(pq)
        if cur == goal_state:
            return path + [cur]
        if cur in seen: continue
        seen.add(cur)
        for n in neighbors(cur):
            heapq.heappush(pq, (g+1+manhattan(n), g+1, n, path+[cur]))

initial = ((2,8,3),(1,6,4),(7,0,5))
res = astar(initial)
for step in res:
    for row in step: print(row)
    print('---')
