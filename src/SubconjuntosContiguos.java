public class SubconjuntosContiguos {
// Resposta do 2º da prova de Técnicas Avançadas de Algoritmos.
    // Função principal para exibir os subconjuntos contíguos
    public static void exibirSubconjuntos(int[] arr) {
        int n = arr.length;

        // Chama a função auxiliar para cada índice inicial
        for (int i = 0; i < n; i++) {
            backtrack(i, i, arr);
        }
    }

    // Função de backtracking para gerar e exibir subconjuntos contíguos
    public static void backtrack(int inicio, int atual, int[] arr) {
        int n = arr.length;

        // Exibe o subconjunto atual
        for (int i = inicio; i <= atual; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Caso base: se o valor atual for o último índice, retorne
        if (atual == n - 1) {
            return;
        }

        // Chama a função recursivamente para expandir o subconjunto
        backtrack(inicio, atual + 1, arr);
    }

    // Função main para testar o código
    public static void main(String[] args) {
        int[] arr = {1, 2, 3}; // Entrada

        // Chama a função para exibir subconjuntos
        exibirSubconjuntos(arr);
    }
}
