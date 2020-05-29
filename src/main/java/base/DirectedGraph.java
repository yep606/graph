package base;

import java.io.*;
import java.util.LinkedList;

public class DirectedGraph {

    private LinkedList<Integer>[] adj = new LinkedList[100];

    public void addVertex(int vertex) {

        if (adj[vertex] == null && vertex <= 100)
            adj[vertex] = new LinkedList<>();
        else
            System.out.println("Произошла ошибка: вершина выходит за границы или уже существует");
    }

    public void addEdge(int vertex1, int vertex2) {
        if (adj[vertex1] != null && adj[vertex2] != null
                && !adj[vertex1].contains(vertex1) && !adj[vertex2].contains(vertex2)
                && vertex1 != vertex2) {

            adj[vertex1].add(vertex2);
            adj[vertex2].add(vertex1);

        } else
            System.out.println("Ошибка добавления ребра");
    }


    public void delVertex(int vertex) {
        if (adj[vertex] == null || vertex > 100)
            System.out.println("Произошла ошибка: вершина выходит за границы или ее не существует ");
        else {
            adj[vertex] = null;
            for (LinkedList<Integer> integers : adj) {
                if (integers != null && integers.contains(vertex)) {
                    integers.remove(Integer.valueOf(vertex));
                }
            }
        }
    }

    public void delEdge(int vertex1, int vertex2) {
        if (adj[vertex1] != null && adj[vertex2] != null
                && adj[vertex1].contains(vertex2) && adj[vertex2].contains(vertex1)
                && vertex1 != vertex2) {

            adj[vertex1].remove(Integer.valueOf(vertex2));
            adj[vertex2].remove(Integer.valueOf(vertex1));
        } else
            System.out.println("Ошибка удаления ребра");
    }

    public void display() {
        System.out.println("----------");
        System.out.println("|  Граф  |");
        System.out.println("----------");
        for (int i = 0; i < adj.length; i++) {
            if (adj[i] != null) {
                System.out.print(i + " : ");
                for (Integer num : adj[i]
                ) {
                    System.out.print(String.format("|%d|", num));
                }
                System.out.println();
            }
        }
    }

    public void loadGraph() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("graph.txt")));
        int c;
        int curVer = br.read();
        adj[curVer] = new LinkedList<>();
        System.out.println((char) curVer);

        while((c = br.read()) != -1)
        {
            char character = (char) c;
            if ((int) character == 10) {
                curVer = (char) br.read();
                adj[curVer] = new LinkedList<>();
            }
            else
                adj[curVer].add((int) character);
        }
    }

    public void saveGraph() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("graph.txt", "UTF-8");
        for (int i = 0; i < adj.length; i++) {
            if (adj[i] != null) {
                writer.print(i);
                for (Integer num : adj[i]
                ) {
                    writer.print(num);
                }
                writer.println();
            }
        }
        writer.close();

    }

    // 5 task option

    public void p(DirectedGraph graph) {
    }

    public void dMinus(LinkedList<Integer> vertex) {
    }

    public void D(int v, int n) {
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Integer>[] adj) {
        this.adj = adj;
    }
}
