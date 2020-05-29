package base;


import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        DirectedGraph graph = new DirectedGraph();
//        graph.addVertex(1);
//        graph.addVertex(2);
//        graph.addEdge(1,2);
//        graph.addVertex(3);
//
//
//        graph.addEdge(1,3);
//
//        graph.display();
//
//        graph.saveGraph();

        System.out.println("Loading graph");
        graph.loadGraph();

        graph.display();

        char c = '\n';
        System.out.print((int) c);

    }
}
