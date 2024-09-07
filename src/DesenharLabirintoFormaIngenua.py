def backtrack(matrix, vertice_atual, visitadas, caminho, total_arestas):
    # Se percorremos todas as arestas, retornamos o número de movimentos.
    if len(caminho) == total_arestas:
        return len(caminho)
    
    min_movements = float('inf')
    
    for vizinho in range(len(matrix)):
        if matrix[vertice_atual][vizinho] == 1 and (vertice_atual, vizinho) not in visitadas:
            # Marcar a aresta como visitada
            visitadas.add((vertice_atual, vizinho))
            visitadas.add((vizinho, vertice_atual))
            
            # Adicionar ao caminho
            caminho.append((vertice_atual, vizinho))
            
            # Continuar o backtracking a partir do vizinho
            movimentos = backtrack(matrix, vizinho, visitadas, caminho, total_arestas)
            
            # Atualizar o mínimo de movimentos encontrados
            min_movements = min(min_movements, movimentos)
            
            # Desfazer as mudanças (backtrack)
            caminho.pop()
            visitadas.remove((vertice_atual, vizinho))
            visitadas.remove((vizinho, vertice_atual))
    
    return min_movements

def criar_matriz(vertices):
    # Criar a matriz de adjacência
    matrix = [[0] * vertices for _ in range(vertices)]
    return matrix

def solve_labirinto_backtracking(T, test_cases):
    results = []
    for i in range(T):
        nodo_inicial = test_cases[i][0]
        vertices, arestas = test_cases[i][1]
        edges = test_cases[i][2:]
        
        matrix = criar_matriz(vertices)
        
        for u, v in edges:
            matrix[u][v] = 1
            matrix[v][u] = 1
        
        # Calcular a quantidade mínima de movimentos usando backtracking
        visitadas = set()
        caminho = []
        total_movements = backtrack(matrix, nodo_inicial, visitadas, caminho, arestas)
        
        # Duplicar os movimentos para ida e volta
        results.append(2 * total_movements)
    
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
results = solve_labirinto_backtracking(T, test_cases)

for result in results:
    print(result)