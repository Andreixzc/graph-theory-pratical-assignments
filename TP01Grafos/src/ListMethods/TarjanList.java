package ListMethods;

import java.util.*;

public class TarjanList {
    private LinkedList<Integer>[] listaAdj;
    private int n;
    private int[] ids;
    private int[] lowIds;
    private boolean[] naPilha;
    private Stack<Integer> pilha;
    private List<List<Integer>> sccs;
    private int idAtual;

    public TarjanList(LinkedList<Integer>[] listaAdj) {
        this.listaAdj = listaAdj;
        this.n = listaAdj.length;
        this.ids = new int[n];
        Arrays.fill(ids, -1);
        this.lowIds = new int[n];
        Arrays.fill(lowIds, -1);
        this.naPilha = new boolean[n];
        this.pilha = new Stack<>();
        this.sccs = new ArrayList<>();
        this.idAtual = 0;
    }

    public List<List<Integer>> calcularSCCs() {
        for (int i = 0; i < n; i++) {
            if (ids[i] == -1) {
                visitar(i);
            }
        }
        return sccs;
    }

    private void visitar(int v) {
        ids[v] = idAtual;
        lowIds[v] = idAtual;
        idAtual++;
        pilha.push(v);
        naPilha[v] = true;

        for (int w : listaAdj[v]) {
            if (ids[w] == -1) {
                visitar(w);
                lowIds[v] = Math.min(lowIds[v], lowIds[w]);
            } else if (naPilha[w]) {
                lowIds[v] = Math.min(lowIds[v], ids[w]);
            }
        }

        if (ids[v] == lowIds[v]) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = pilha.pop();
                naPilha[w] = false;
                scc.add(w);
            } while (w != v);
            sccs.add(scc);
        }
    }
}
