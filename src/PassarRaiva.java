import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PassarRaiva {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*T => indica o número total de casos de teste.
          N => inteiro que representa cada caso que inicia com uma linha.
          Ponto(nodo) no qual o desenho deve ser iniciado e também onde o desenho deve ser terminado.
          X => largura em nodos do labirinto, que pode variar de 3 a 7.
          V => quantidade de vértices.
          A => quantidade de arestas. */
       
        int T = Integer.parseInt(sc.nextLine());
       
        while (T-- > 0) {
            int N = Integer.parseInt(sc.nextLine());
            int quantVertices = sc.nextInt();
            int quantArestas = sc.nextInt();
           
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < quantVertices; i++) {
                adjList.add(new ArrayList<>());
            }
           
            int[] degree = new int[quantVertices];
           
            for (int i = 0; i < quantArestas; i++) {
                int u = Integer.parseInt(sc.nextLine());
                int v = Integer.parseInt(sc.nextLine());
               
                adjList.get(u).add(v);
                adjList.get(v).add(u);
               
                degree[u]++;
                degree[v]++;
            }
           
            List<Integer> oddVertices = new ArrayList<>();
            for (int i = 0; i < quantVertices; i++) {
                if (degree[i] % 2 != 0) {
                    oddVertices.add(i);
                }
            }
           
            int totalEdges = quantArestas;
            int minExtraPath = 0;
           
            if (oddVertices.size() > 0) {
                int[][] dist = new int[quantVertices][quantVertices];
                for (int[] row : dist) {
                    Arrays.fill(row, Integer.MAX_VALUE);
                }
                for (int i = 0; i < quantVertices; i++) {
                    dist[i][i] = 0;
                }
               
                for (int u = 0; u < quantVertices; u++) {
                    for (int v : adjList.get(u)) {
                        dist[u][v] = 1;
                    }
                }
               
                for (int k = 0; k < quantVertices; k++) {
                    for (int i = 0; i < quantVertices; i++) {
                        for (int j = 0; j < quantVertices; j++) {
                            if (dist[i][j] > dist[i][k] + dist[k][j]) {
                                dist[i][j] = dist[i][k] + dist[k][j];
                            }
                        }
                    }
                }
               
                int n = oddVertices.size();
                int[][] dp = new int[1 << n][n];
                for (int[] row : dp) {
                    Arrays.fill(row, Integer.MAX_VALUE);
                }
               
                for (int i = 0; i < n; i++) {
                    dp[1 << i][i] = 0;
                }
               
                for (int mask = 0; mask < (1 << n); mask++) {
                    for (int u = 0; u < n; u++) {
                        if ((mask & (1 << u)) != 0) {
                            for (int v = 0; v < n; v++) {
                                if (u != v && (mask & (1 << v)) == 0) {
                                    dp[mask | (1 << v)][v] = Math.min(dp[mask | (1 << v)][v], dp[mask][u] + dist[oddVertices.get(u)][oddVertices.get(v)]);
                                }
                            }
                        }
                    }
                }
               
                minExtraPath = dp[(1 << n) - 1][0];
            }
           
            int result = 2 * totalEdges + minExtraPath;
            System.out.println(result);
        }
       
        sc.close();
    }
}
