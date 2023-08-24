package category.search.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SM_3124_최소스패닝트리 {

    static int V, E;

    static class Edge implements Comparable<Edge> {
        int start;
        int to;
        int weight;

        Edge(int start, int to, int weight) {
            this.start = start;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(weight, e.weight);
        }
    }

    static long solution(List<Edge> edgeList) {
        // 1. 정렬
        Collections.sort(edgeList);

        // 2. 정점에 대한 visited 배열
        //    parent 배열 생성
        int[] p = new int[V + 1];

        // 3. 단위 서로소 집합 초기화
        for (int i = 0; i < V; i++) {
            p[i] = i;
        }

        // 4. 유니온 파인드 수행
        int cnt = 0;
        long result = 0;

        for (Edge e : edgeList) {
            if (union(p, e.start, e.to)) {
                result += e.weight;
                cnt++;
            }

            if (cnt == V - 1) {
                break;
            }
        }

        return result;
    }

    static int find(int[] p, int x) {
        if(p[x] == x) return x;
        return p[x] = find(p, p[x]);
    }

    static boolean union(int[] p, int x, int y) {
        int xRoot = find(p, x);
        int yRoot = find(p, y);

        if (xRoot == yRoot) {
            return false;
        }
        p[yRoot] = xRoot;
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            List<Edge> edgeList = new ArrayList<>();

            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(start, to, weight));
            }

            sb.append("#").append(tc).append(" ").append(solution(edgeList)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
