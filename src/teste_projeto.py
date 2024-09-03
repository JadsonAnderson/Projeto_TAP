def dfs(matrix, vertice, visited):
    visited.add(vertice)
    total_edges = 0
    for neighbor in range(len(matrix)):
        ligacao_vizinho = matrix[vertice][neighbor] == 1
        vizinho_nao_visitado = neighbor not in visited
        
        if ligacao_vizinho and vizinho_nao_visitado:
            total_edges += 1 + dfs(matrix, neighbor, visited)
    return total_edges

def criar_matriz(vertices):
    # Criar a matriz de adjacência
    matrix = [[0] * vertices for _ in range(vertices)]
    return matrix

def solve_labirinto(T, test_cases):
    results = []
    for i in range(T):
        nodo_inicial = test_cases[i][0]
        vertices, arestas = test_cases[i][1]
        edges = test_cases[i][2:]
        
        matrix = criar_matriz(vertices)
        
        for u, v in edges:
            matrix[u][v] = 1
            matrix[v][u] = 1
        
        # Calcular a quantidade mínima de movimentos usando DFS
        visited = set()
        movements = 2 * dfs(matrix, nodo_inicial, visited)
        results.append(movements)
    
    return results

# Processando a entrada
def process_input():
    T = int(input())
    test_cases = []
    for _ in range(T):
        nodo_inicial = int(input())
        vertices, arestas = map(int, input().split())
        edges = [tuple(map(int, input().split())) for _ in range(arestas)]
        test_cases.append((nodo_inicial, (vertices, arestas), *edges))
    return T, test_cases

# Exemplo de uso com a entrada fornecida
T, test_cases = process_input()
results = solve_labirinto(T, test_cases)

for result in results:
    print(result)
