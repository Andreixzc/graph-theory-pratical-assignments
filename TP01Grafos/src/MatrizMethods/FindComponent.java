package MatrizMethods;
import java.util.LinkedList;
import java.util.Queue;

public class FindComponent{
    public static int getNumConnectedComponents(int[][] matriz) {
        //dfs
        int numComponents = 0;
        boolean[] visited = new boolean[matriz.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < matriz.length; i++) {
            if (!visited[i]) {
                numComponents++;
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int v = queue.poll();
                    for (int j = 0; j < matriz.length; j++) {
                        if (matriz[v][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
        return numComponents;
    }
    public static int[][] removeVertex(int[][] adjacencyMatrix, int vertexToRemove) {
        // Obtém o número de vértices na matriz de adjacência
        int numVertices = adjacencyMatrix.length;

        // Cria uma nova matriz de adjacência com uma linha e uma coluna a menos
        int[][] newAdjacencyMatrix = new int[numVertices - 1][numVertices - 1];

        // Copia os elementos da matriz de adjacência original para a nova matriz, exceto para a
        // linha e coluna do vértice a ser removido
        int newRow = 0;
        int newCol;
        for (int i = 0; i < numVertices; i++) {
            // Ignora a linha correspondente ao vértice a ser removido
            if (i == vertexToRemove) {
                continue;
            }

            newCol = 0;
            for (int j = 0; j < numVertices; j++) {
                // Ignora a coluna correspondente ao vértice a ser removido
                if (j == vertexToRemove) {
                    continue;
                }
                // Copia o elemento da matriz de adjacência original para a nova matriz
                if (i < vertexToRemove && j < vertexToRemove) {
                    newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                } else if (i < vertexToRemove && j > vertexToRemove) {
                    newAdjacencyMatrix[i][j - 1] = adjacencyMatrix[i][j];
                } else if (i > vertexToRemove && j < vertexToRemove) {
                    newAdjacencyMatrix[i - 1][j] = adjacencyMatrix[i][j];
                } else if (i > vertexToRemove && j > vertexToRemove) {
                    newAdjacencyMatrix[i - 1][j - 1] = adjacencyMatrix[i][j];
                }
            }

            if (i > vertexToRemove) {
                newRow = i - 1;
            } else {
                newRow = i;
            }
        }

        return newAdjacencyMatrix;
    }
    public static int[] getArticulacoes(int [][] matriz){
        int[] vet = new int[matriz.length];
        
        for (int i = 0; i < matriz.length; i++) {
            int ConectedNum = getNumConnectedComponents(FindComponent.removeVertex(matriz, i));
            if (ConectedNum > 1) {
                vet[i] = 1;
            }
        }
        return vet;
    }
}
