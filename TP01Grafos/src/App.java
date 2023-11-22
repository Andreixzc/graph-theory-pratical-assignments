
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import GrafosImplementados.Grafo;
import GrafosImplementados.GrafoLista;
import ListMethods.CaminhoDisjuntoLista;
import ListMethods.FindComponentList;
import ListMethods.TarjanList;
import MatrizMethods.CaminhosDisjuntos;
import MatrizMethods.FindComponent;
import MatrizMethods.Tarjan;


public class App {
    public static void main(String[] args) throws Exception {
        // Grafo g100 = geraGrafo("Grafos/undirectedPath100.csv");
        // GrafoLista grafoLista100 = geraGrafoLista("Grafos/undirectedPath100.csv");
        // System.out.println(Arrays.toString(medeTempoMatriz(g100)));
        // System.out.println(Arrays.toString(medeTempoLista(grafoLista100)));

        Grafo g1000 = geraGrafo("Grafos/undirectedPath1000.csv");
        GrafoLista grafoLista1000 = geraGrafoLista("Grafos/undirectedPath1000.csv");
        System.out.println(Arrays.toString(medeTempoMatriz(g1000)));
        System.out.println(Arrays.toString(medeTempoLista(grafoLista1000)));


        // Grafo g10000 = geraGrafo("Grafos/undirectedPath10000.csv");
        // GrafoLista grafoLista10000 = geraGrafoLista("Grafos/undirectedPath10000.csv");
        // System.out.println(Arrays.toString(medeTempoMatriz(g10000)));
        // System.out.println(Arrays.toString(medeTempoLista(grafoLista10000)));



        // Grafo g100000 = geraGrafo("Grafos/undirectedPath10000.csv");
        // GrafoLista grafoLista100000 = geraGrafoLista("Grafos/undirectedPath100000.csv");
        // System.out.println(Arrays.toString(medeTempoMatriz(g100000)));
        // System.out.println(Arrays.toString(medeTempoLista(grafoLista100000)));

    }
    public static GrafoLista geraGrafoLista(String filename) {
        //Grafos/undirectedPath10000.csv
        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "r");
            GrafoLista grafoLista = new GrafoLista(Integer.parseInt(raf.readLine()));

            String line;
            while ((line = raf.readLine()) != null) {
                
                String str[] = line.split(",");
                grafoLista.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            }
            return grafoLista;

        } catch (Exception e) {
            return null;
        }
    }
    public static Grafo geraGrafo(String filename) {
        //Grafos/undirectedPath10000.csv
        try {
            RandomAccessFile raf = new RandomAccessFile(filename, "r");
            Grafo grafo = new Grafo(Integer.parseInt(raf.readLine()));

            String line;
            while ((line = raf.readLine()) != null) {
                String str[] = line.split(",");
                grafo.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            }
            return grafo;

        } catch (Exception e) {
            return null;
        }
    }
    public static void  TarjanAux(Grafo grafo){
        Tarjan tarjan = new Tarjan(grafo.matrizAdj);
        List<List<Integer>> sccs = tarjan.calcularSCCs();

        // for (List<Integer> scc : sccs) {
        //     System.out.println(scc);
        // }
    }
    public static void  TarjanAuxLista(GrafoLista grafo){
        TarjanList tarjan = new TarjanList(grafo.adj);
        List<List<Integer>> sccs = tarjan.calcularSCCs();

        // for (List<Integer> scc : sccs) {
        //     System.out.println(scc);
        // }
    }
    public static long[] medeTempoMatriz(Grafo grafo){
        long[] vet = new long[3];
        Long inicio1 = System.currentTimeMillis();
        CaminhosDisjuntos.verificaCaminhosDisjuntos(grafo.matrizAdj);//
        Long termino1 = System.currentTimeMillis();
        long tempoExecucao1 = termino1 - inicio1;
        vet[0] = tempoExecucao1;

        Long inicio2 = System.currentTimeMillis();
        FindComponent.getArticulacoes(grafo.matrizAdj);//
        Long termino2 = System.currentTimeMillis();
        long tempoExecucao2 = termino2 - inicio2;
        vet[1] = tempoExecucao2;

        Long inicio3 = System.currentTimeMillis();
        TarjanAux(grafo);
        Long termino3 = System.currentTimeMillis();
        long tempoExecucao3 = termino3 - inicio3;
        vet[2] = tempoExecucao3;
        return vet;

    }
    public static long[] medeTempoLista(GrafoLista grafo){
        long[] vet = new long[3];
        Long inicio1 = System.currentTimeMillis();
        CaminhoDisjuntoLista.verificaCaminhosDisjuntosOuCiclo(grafo.adj);
        Long termino1 = System.currentTimeMillis();
        long tempoExecucao1 = termino1 - inicio1;
        vet[0] = tempoExecucao1;

        Long inicio2 = System.currentTimeMillis();
        FindComponentList.getArticulacoes(grafo.adj);
        Long termino2 = System.currentTimeMillis();
        long tempoExecucao2 = termino2 - inicio2;
        vet[1] = tempoExecucao2;
 
        Long inicio3 = System.currentTimeMillis();
        TarjanAuxLista(grafo);
        Long termino3 = System.currentTimeMillis();
        long tempoExecucao3 = termino3 - inicio3;
        vet[2] = tempoExecucao3;
        return vet;

    }
    

}   

