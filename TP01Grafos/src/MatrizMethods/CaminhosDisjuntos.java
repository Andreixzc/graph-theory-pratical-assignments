package MatrizMethods;
public class CaminhosDisjuntos {
    public static boolean verificaCaminhosDisjuntosOuCiclo(int[][] grafo) {
        int n = grafo.length;
        int[][] dist = new int[n][n];
        int[][] prox = new int[n][n];
        
        // inicializa as matrizes de distância e proximidade
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = grafo[i][j];
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
                    if (grafo[j][i] == 1 && dist[i][j] - grafo[i][j] < dist[j][i]) {
                        return true;
                    }
                    
                    // verifica se há dois caminhos disjuntos
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j && grafo[i][k] == 1 && grafo[k][j] == 1 &&
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

    public static boolean[] verificaCaminhosDisjuntos(int[][] grafo) {
        int n = grafo.length;
        int[][] dist = new int[n][n];
        int[][] prox = new int[n][n];
        boolean[] result = new boolean[n];
        
        // inicializa as matrizes de distância e proximidade
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = grafo[i][j];
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
        
        // verifica se existem dois caminhos disjuntos entre cada par de vértices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] < Integer.MAX_VALUE) {
                    // verifica se há dois caminhos disjuntos
                    for (int k = 0; k < n; k++) {
                        if (k != i && k != j && grafo[i][k] == 1 && grafo[k][j] == 1 &&
                            dist[i][k] + dist[k][j] == dist[i][j]) {
                            result[k] = true;
                        }
                    }
                }
            }
        }
        
        // retorna o vetor booleano
        return result;
    }
    
    
}
