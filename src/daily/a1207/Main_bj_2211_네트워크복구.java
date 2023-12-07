package daily.a1207;

import java.io.*;
import java.util.*;

public class Main_bj_2211_네트워크복구 {
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", cost=" + cost +
                    '}';
        }


        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M;
    static Edge[] edgeList;
    // union-find 구성을 위해
    static int[] parents;


    /**
     * Mst using Kruskal Algorithms
     */
    static void solution() {
        Arrays.sort(edgeList);

        make();

        int result = 0;
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (Edge edge : edgeList) { // 비용이 적은 간선 순으로 꺼내어서 처리한다.
            if (union(edge.start, edge.end)) {
                result += edge.cost;
                sb.append(edge.start).append(" ").append(edge.end).append("\n");
                if (++count == N - 1) {
                    break;
                }
            }
        }

        System.out.println(count);
        System.out.print(sb);
    }

    /**
     * 부모 번호 배열 초기화
     */
    static void make() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i; // 초기에는 자기 자신
        }
    }

    /**
     * 최고 부모 찾기
     * @param a
     * @return
     */
    static int find(int a) {
        if(parents[a] == a)
            return a;
        return parents[a] = find(parents[a]); // path compression
    }

    /**
     * union 구현
     * @param a
     * @param b
     * @return
     */
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) {
            return false;
        }
        parents[bRoot] = aRoot;
        return true;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(s, t, c);
        }

        solution();
    }

}
