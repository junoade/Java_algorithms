package daily.a1226;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_bj_1277_InstallPlant {

    static class Vertex {
        int key;
        int x;
        int y;

        Vertex(int key, int x, int y) {
            this.key = key;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Vertex " + key + "{" + x + ", " + y + "}";
        }
    }

    static class State {
        Vertex vertex;
        double cost;

        State(Vertex vertex, double cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "State{" + vertex + ", " + cost + "}";
        }
    }

    static int N, W;
    static double M;
    static final int MAX_N = 1000;
    static boolean[][] graph;
    static Vertex[] vertices;

    static void solution() {
        boolean[] v = new boolean[N + 1];
        double[] dist = new double[N + 1]; // i번째를 방문하기 위해 추가된 최소 거리 비용 // 이미 경로가 존재하면 0
        final double INF = 987654321.0;
        Arrays.fill(dist, INF);

        PriorityQueue<State> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
        dist[1] = 0.0;
        pq.offer(new State(vertices[1], dist[1]));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int minVertex = cur.vertex.key;
            double minCost = cur.cost;
            // System.out.println(cur);

            if (v[minVertex]) continue;
            v[minVertex] = true;

            if (minCost > dist[minVertex]) {
                continue;
            }

            // 갱신
            for (int j = 1; j <= N; j++) {
                // 이미 최단거리를 구했다.
                if (v[j]) {
                    continue;
                }

                // 이미 연결되었던 곳은 단순 상태 전이만 하도록 pq에 삽입
                if (graph[minVertex][j]) {
                    pq.offer(new State(vertices[j], minCost + 0));
                    continue;
                }

                // 일단 minVertex와 연결이 파괴된 j번 정점간 거리를 구해본다.
                double tempDist = getDistance(vertices[minVertex], vertices[j]);
                // 갱신 조건
                if (tempDist <= M && dist[j] > minCost + tempDist) {
                    dist[j] = minCost + tempDist;
                    pq.offer(new State(vertices[j], dist[j]));
                }
            }
        }

        System.out.println((long) (dist[N] * 1000.0));
    }

    static double getDistance(Vertex v1, Vertex v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
    }


    // 필요없네..
    /*static void init(double[] dist, boolean[] v, int key) {
        if (v[key]) {
            return;
        }
        dist[key] = 0.0;
        v[key] = true;

        for (int j = 1; j <= N; j++) {
            if (graph[key][j]) {
                init(dist, v, j);
            }
        }
    }*/

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/daily/a1226/case3.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());

        vertices = new Vertex[N + 1];
        graph = new boolean[MAX_N + 1][MAX_N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            vertices[i] = new Vertex(i, x, y);
        }

        // 전선 정보 연결
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        solution();
    }
}
