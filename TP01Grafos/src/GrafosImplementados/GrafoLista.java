package GrafosImplementados;
import java.util.*;

public class GrafoLista {
    int V; // número de vértices
   public LinkedList<Integer>[] adj; // lista de adjacência

    public GrafoLista(int V) {
        this.V = V;
        this.adj = new LinkedList[V];

        // inicializa as listas de adjacência
        for (int i = 0; i < V; i++) {
            this.adj[i] = new LinkedList<Integer>();
        }
    }

    // adiciona uma aresta na lista de adjacência
    public void addEdge(int u, int v) {
        this.adj[u].add(v);
        this.adj[v].add(u); // se for não-direcionado, adiciona a aresta inversa também
    }

    // retorna a lista de adjacência do vértice v
    public LinkedList<Integer> getAdj(int v) {
        return this.adj[v];
    }

    // retorna o número de vértices do grafo
    public int getV() {
        return this.V;
    }
}
