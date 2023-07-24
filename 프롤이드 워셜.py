""" This is input value. you can just copy and paste it.
4 7
1 2 5
1 4 7
2 1 4
2 3 -3
3 1 6
3 4 4
4 3 2
"""
import sys
input = sys.stdin.readline

V, E = map(int, input().split())
INF = float('inf')
graph = [[INF] * (V+1) for _ in range(V+1)]

for _ in range(E):
    src, dst, weight = map(int, input().split())
    graph[src][dst] = weight

for k in range(1, V+1): # via
    graph[k][k] = 0
    for i in range(1, V+1): # src
        for j in range(1, V+1): # dst
            graph[i][j] = min(graph[i][j], graph[i][k]+graph[k][j])

for i in range(1, V+1):
    for j in range(1, V+1):
        print (graph[i][j], end= ' ')
    print()