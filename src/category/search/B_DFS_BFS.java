package category.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_DFS_BFS {
    static int[][] graph;
    static boolean[] visited;

    static void dfs(int startKey, StringBuilder sb) {
        visited[startKey] = true;
        sb.append(startKey).append(" ");

        for(int i = 1; i < graph[startKey].length; i++) {
            if(visited[i]) {
                continue;
            }

            if(graph[startKey][i] != 1) {
                continue;
            }

            visited[i] = true;
            dfs(i, sb);
        }
    }

    static void bfs(int startKey, StringBuilder sb) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startKey);
        visited[startKey] = true;

        while (!queue.isEmpty()) {
            Integer cursor = queue.poll();
            sb.append(cursor).append(" ");

            for(int i = 1; i < graph[cursor].length; i++) {
                if(visited[i]) {
                    continue;
                }

                if(graph[cursor][i] != 1) {
                    continue;
                }

                visited[i] = true;
                queue.add(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        // 메모리 공간 O(N^2)
        graph = new int[N+1][N+1];
        for(int i = 0; i < M; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int src = inputs[0], target = inputs[1];
            // 양방향이라
            graph[src][target] = 1;
            graph[target][src] = 1;
        }

        visited = new boolean[N + 1]; // 정점은 1부터 N까지
        StringBuilder result = new StringBuilder();
        dfs(V, result);
        System.out.println(result);

        visited = new boolean[N + 1]; // 정점은 1부터 N까지
        result = new StringBuilder();
        bfs(V, result);
        System.out.println(result);
    }
}
