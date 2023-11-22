package ListMethods;
import java.util.LinkedList;
import java.util.Queue;

public class FindComponentList {
public static int getNumConnectedComponents(LinkedList<Integer>[] adjList) {
// dfs
int numComponents = 0;
boolean[] visited = new boolean[adjList.length];
Queue<Integer> queue = new LinkedList<>();


    for (int i = 0; i < adjList.length; i++) {
        if (!visited[i]) {
            numComponents++;
            visited[i] = true;
            queue.add(i);

            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int neighbor : adjList[v]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
    return numComponents;
}

public static LinkedList<Integer>[] removeVertex(LinkedList<Integer>[] adjList, int vertexToRemove) {
    // Obtém o número de vértices na lista de adjacência
    int numVertices = adjList.length;

    // Cria uma nova lista de adjacência com um vértice a menos
    LinkedList<Integer>[] newAdjList = new LinkedList[numVertices - 1];
    for (int i = 0; i < numVertices - 1; i++) {
        newAdjList[i] = new LinkedList<>();
    }

    // Copia os elementos da lista de adjacência original para a nova lista, exceto para o
    // vértice a ser removido
    int j = 0;
    for (int i = 0; i < numVertices; i++) {
        if (i == vertexToRemove) {
            continue;
        }
        for (int neighbor : adjList[i]) {
            if (neighbor == vertexToRemove) {
                continue;
            }
            if (i < vertexToRemove && neighbor < vertexToRemove) {
                newAdjList[i].add(neighbor);
            } else if (i < vertexToRemove && neighbor > vertexToRemove) {
                newAdjList[i].add(neighbor - 1);
            } else if (i > vertexToRemove && neighbor < vertexToRemove) {
                newAdjList[i - 1].add(neighbor);
            } else if (i > vertexToRemove && neighbor > vertexToRemove) {
                newAdjList[i - 1].add(neighbor - 1);
            }
        }
        j++;
    }

    return newAdjList;
}

public static int[] getArticulacoes(LinkedList<Integer>[] adjList) {
    int[] vet = new int[adjList.length];

    for (int i = 0; i < adjList.length; i++) {
        int ConectedNum = getNumConnectedComponents(removeVertex(adjList, i));
        if (ConectedNum > 1) {
            vet[i] = 1;
        }
    }
    return vet;
}
}