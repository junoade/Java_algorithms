package dataStructures.graph;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        testAdjMatrixImpl();
        // testAdjListImpl();
    }

    static void testAdjMatrixImpl() {
        UndirectGraph_Using_AdjMatrix test = new UndirectGraph_Using_AdjMatrix();
        try {
            test.solution();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testAdjListImpl() {
        UndirectedGraph_Using_AdjList test = new UndirectedGraph_Using_AdjList();
        try {
            test.solution();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
