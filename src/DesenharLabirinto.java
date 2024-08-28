import java.util.Scanner;

public class DesenharLabirinto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int T = scanner.nextInt(); // Número de casos de teste.

        for (int teste = 0; teste < T; teste++) {
            int N = scanner.nextInt(); // Nodo qual deve ser iniciado e terminado.
            int V = scanner.nextInt(); // Quantidade de vértices.
            int A = scanner.nextInt(); // Quantidade de arestas.

            int X = (int) Math.sqrt(V); // X é a largura do labirinto, ou seja, V é igual a X².

            int[][] matriz = new int[V][V]; // Criando a matriz de tamanho V por V (vértices).

            if (X >= 3 && X <= 7) {
                for (int i = 0; i < A; i++) {   // Preenchendo as arestas entre os vértices.
                    int v1 = scanner.nextInt();
                    int v2 = scanner.nextInt();

                    matriz[v1][v2] = 1;
                    matriz[v2][v1] = 1;
                }
            }

            for (int[] m : matriz) {            // Printando a matriz.
                for (int n : m) {
                    System.out.print(n + " ");
                }
                System.out.println();   
            }
        }
        
        scanner.close();
    }

}
