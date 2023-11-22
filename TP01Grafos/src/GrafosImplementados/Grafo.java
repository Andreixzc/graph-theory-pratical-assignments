package GrafosImplementados;
import java.util.ArrayList;

public class Grafo {
    public int matrizAdj[][];
    int totalVertices;

    public Grafo(int totalVertices) {
        this.totalVertices = totalVertices;
        this.matrizAdj = new int[totalVertices][totalVertices];
    }

    public void addEdge(int origem, int destino) {
        matrizAdj[origem][destino] = 1;
        matrizAdj[destino][origem] = 1;
    }

    public void removeEdge(int origem, int destino) {
        matrizAdj[origem][destino] = 0;
        matrizAdj[destino][origem] = 0;
    }
    
    public ArrayList<ArrayList<Integer>> encontrarBlocos() {
        ArrayList<ArrayList<Integer>> blocos = new ArrayList<ArrayList<Integer>>();
        boolean[] visitados = new boolean[totalVertices];

        for (int i = 0; i < totalVertices; i++) {
            if (!visitados[i]) {
                ArrayList<Integer> bloco = new ArrayList<Integer>();
                DFS(i, visitados, bloco);
                blocos.add(bloco);
            }
        }

        return blocos;
    }

    private void DFS(int vertice, boolean[] visitados, ArrayList<Integer> bloco) {
        visitados[vertice] = true;
        bloco.add(vertice);

        for (int i = 0; i < totalVertices; i++) {
            if (matrizAdj[vertice][i] == 1 && !visitados[i]) {
                DFS(i, visitados, bloco);
            }
        }
    }
    public void printaMatrix(){
        for (int i = 0; i < matrizAdj.length; i++) {
            for (int j = 0; j < matrizAdj.length; j++) {
                System.out.print(matrizAdj[i][j]+" ");
            }
            System.out.println();
        }
    }
}
