package dataStructures.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UndirectedGraph_Using_AdjList {
    Map<Integer, List<Integer>> graph;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        graph = new HashMap<>();

        for(int i = 0; i < T; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startKey = inputs[0];
            int endKey = inputs[1];

            updateUndirectedGraph(startKey, endKey);
            updateUndirectedGraph(endKey, startKey);
        }

        print();
    }

    private void updateUndirectedGraph(int startKey, int endKey) {
        List<Integer> adjList;

        if(graph.containsKey(startKey)) {
            adjList = graph.get(startKey);
            adjList.add(endKey);
        } else {
            adjList = new LinkedList<>(Arrays.asList(endKey));
        }

        graph.put(startKey, adjList);
    }

    private void print() {
        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            System.out.printf("key : %d, adjList : %s\n", entry.getKey(), entry.getValue());
        }
    }
}
