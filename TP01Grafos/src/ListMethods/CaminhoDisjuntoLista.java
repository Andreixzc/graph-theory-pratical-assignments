package ListMethods;

import java.util.Arrays;
import java.util.LinkedList;

public class CaminhoDisjuntoLista {
    public static boolean verificaCaminhosDisjuntosOuCiclo(LinkedList<Integer>[] grafo) {
        int n = grafo.length;
        int[][] dist = new int[n][n];
        int[][] prox = new int[n][n];
    
        // inicializa as matrizes de distância e proximidade
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (grafo[i].contains(j)) {
                    dist[i][j] = 1;
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
                prox[i][j] = j;
            }
        }
    
        // executa o algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prox[i][j] = prox[i][k];
                    }
                }
            }
        }
    
        // verifica se existem dois caminhos disjuntos ou um ciclo entre cada par de vértices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] < Integer.MAX_VALUE) {
                    // verifica se há um ciclo
                    if (grafo[j].contains(i) && dist[i][j] - 1 < dist[j][i]) {
                        return true;
                    }
    
                    // verifica se há dois caminhos disjuntos
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j && grafo[i].contains(k) && grafo[k].contains(j) &&
                            dist[i][k] + dist[k][j] == dist[i][j]) {
                            return true;
                        }
                    }
                }
            }
        }
    
        // se não encontrou nenhum caminho disjunto ou ciclo, retorna falso
        return false;
    }

}
