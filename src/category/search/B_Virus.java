package category.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_Virus {
    static HashMap<Integer, ArrayList<Integer>> graph;
    static int N;
    static int T;


    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());

        graph = new HashMap<>();
        for(int i = 0; i < T; i++) {
            String[] inputs = br.readLine().split(" ");
            int node1 = Integer.parseInt(inputs[0]);
            int node2 = Integer.parseInt(inputs[1]);

            updateGraph(node1, node2);
            updateGraph(node2, node1);
        }
    }

    static void updateGraph(int keyNode, int adjNode) {
        ArrayList<Integer> adjList;
        if(graph.containsKey(keyNode)) {
            adjList = graph.get(keyNode);
            adjList.add(adjNode);
        } else {
            adjList = new ArrayList<>(List.of(adjNode));
        }

        graph.put(keyNode, adjList);
    }

    static void bfs_iter(int start) {
        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();

        /* 시작 노드 방문 */
        visited[start] = true;
        queue.add(start);
        int result = 0;

        while(queue.size() != 0) {
            Integer cursorNode = queue.remove();
            for(Integer nextNode : graph.get(cursorNode)) {
                if(!visited[nextNode]) {
                    queue.add(nextNode);
                    visited[nextNode] = true;
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        processInput();
        bfs_iter(1);
    }
}
