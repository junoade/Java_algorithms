package category.search;

import java.io.*;
import java.util.*;

public class BFS_Goorm {
    static HashMap<Integer, ArrayList<Integer>> graph;
    static int N;
    static int M;
    static int K;

    static void solution() {
        bfs_iterative(1, N);
    }

    static void bfs_iterative(int start, int to) {
        Integer[] visited = initVisited(to + 1); // -1 : 방문 X, 0 이상의 경우 거리를 의미
        Queue<Integer> queue = new LinkedList<>();
        System.out.println(graph);

        // 1번 노드 방문 표시
        visited[start] = 0;
        queue.add(start);

        while (queue.size() != 0) {
            Integer curNode = queue.remove();
            System.out.println("노드 탐색, curNode{" + curNode + "}");
            // 인접 리스트에 대해 모두 탐색
            for (Integer nextNode : graph.get(curNode)) {
                if (visited[nextNode] == -1) {
                    queue.add(nextNode); //
                    visited[nextNode] = visited[curNode] + 1; // 이동 거리 명시
                }
            }
        }

        if (isEnableDistance(visited[to], K)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static Integer[] initVisited(int n) {
        Integer[] arr = new Integer[n];
        Arrays.fill(arr, -1);
        return arr;
    }

    static boolean isEnableDistance(int actual, int max_distance) {
        return actual >= 1 && actual <= max_distance;
    }

    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        K = Integer.parseInt(inputs[2]);

        graph = new HashMap<>(); // 1부터 인덱싱 하기 위해
        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int edge1 = Integer.parseInt(inputs[0]);
            int edge2 = Integer.parseInt(inputs[1]);
            // 양방향성 고려
            updateGraph(edge1, edge2);
            updateGraph(edge2, edge1);
        }
    }

    static void updateGraph(int keyEdge, int adjEdge) {
        ArrayList<Integer> temp;
        if (graph.containsKey(keyEdge)) {
            temp = graph.get(keyEdge);
            temp.add(adjEdge);
        } else {
            temp = new ArrayList<>(List.of(adjEdge));
        }
        graph.put(keyEdge, temp);
    }

    public static void main(String[] args) throws IOException {
        processInput();
        solution();
    }
}