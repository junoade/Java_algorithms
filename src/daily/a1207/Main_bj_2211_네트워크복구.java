package daily.a1207;

import java.io.*;
import java.util.*;

public class Main_bj_2211_네트워크복구 {

    static int N, M;
    static int[][] graph;
    static final int INF = Integer.MAX_VALUE / 2;

    /**
     * Dijkstra
     */
    static void solution() {
        boolean[] v = new boolean[N + 1];
        int[] dist = new int[N + 1];
        int[] connect = new int[N + 1];

        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        dist[1] = 0;
        pq.offer(new int[]{1, dist[1]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min = cur[1];

            if(v[minVertex]) continue;
            v[minVertex] = true;

            if(min > dist[minVertex]) {
                continue;
            }

            // 갱신
            for (int j = 1; j <= N; j++) {
                if (!v[j] && graph[minVertex][j] != 0 && dist[j] > min + graph[minVertex][j]) {
                    dist[j] = min + graph[minVertex][j];
                    connect[j] = minVertex;
                    pq.offer(new int[]{j, dist[j]});
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (connect[i] == 0) {
                continue;
            }

            count++;
            sb.append(i).append(" ").append(connect[i]).append("\n");
        }

        System.out.println(count);
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s][t] = c;
            graph[t][s] = c;
        }

        solution();
    }

}
