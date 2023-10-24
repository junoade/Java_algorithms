package category.search.dfs;

import java.io.*;
import java.util.*;

public class SM_CountVillageGroup {

    static Map<Integer, List<Integer>> graph;
    static boolean[] visited;

    static int solution(int N, int M, int[][] arr) {
        // make graph
        initGraph(arr);
        // dfs
        // count
        visited = new boolean[N + 1];
        int count = 0;
        for (int i = 1; i <= N; i++) {
            count += dfs(i);
        }
        return count;
    }

    static int dfs(int key) {
        // 만약 이미 방문했다면
        if (visited[key]) {
            return 0;
        }

        // 만약 방문하지 않았다면 새로운 그룹
        visited[key] = true;
        List<Integer> adjList = graph.get(key);
        if (adjList.isEmpty()) {
            return 0;
        }

        for (int adjKey : adjList) {
            dfs(adjKey);
        }

        return 1;
    }



    static void initGraph(int[][] arr) {
        graph = new HashMap<>();

        for (int[] a : arr) {
            int x = a[0], y = a[1];
            // 양방향
            update(x, y);
            update(y, x);
        }

    }

    static void update(int x, int y) {
        List<Integer> adjList;
        if (graph.containsKey(x)) {
            adjList = graph.get(x);
            adjList.add(y);
        } else {
            graph.put(x, new ArrayList<>(){
                {
                    add(y);
                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int[] inputs = processInput(br);
            int N = inputs[0], M = inputs[1];
            int[][] arr = new int[M][2];
            for (int m = 0; m < M; m++) {
                arr[m] = processInput(br);
            }

            System.out.printf("#%d %d\n", t + 1, solution(N, M, arr));
        }
    }

    private static int[] processInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
