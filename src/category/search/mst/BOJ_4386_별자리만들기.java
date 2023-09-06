package category.search.mst;

import java.io.*;
import java.util.*;

public class BOJ_4386_별자리만들기 {

    static class Node {
        int key;
        double x;
        double y;

        Node(int key, double x, double y) {
            this.key = key;
            this.x = x;
            this.y = y;
        }
    }

    static class Vertex implements Comparable<Vertex> {
        int key;
        double cost;

        Vertex(int key, double cost) {
            this.key = key;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    static final double INF = Double.MAX_VALUE;

    static int N; // in [1, 100]

    static void solution(Node[] arr) {
        boolean[] v = new boolean[N];
        double[] minEdge = new double[N];
        Arrays.fill(minEdge, INF);
        minEdge[0] = 0.0;

        double result = 0.0;
        int count = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(0, minEdge[0]));

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            int minVertex = cur.key;
            double min = cur.cost;

            if (v[minVertex]) {
                continue;
            }

            v[minVertex] = true;
            result += min;

            if(++count == N) break;

            for (int j = 0; j < N; j++) {
                if (!v[j] && minEdge[j] > compare(arr[cur.key], arr[j])) {
                    minEdge[j] = compare(arr[cur.key], arr[j]);
                    pq.offer(new Vertex(j, minEdge[j]));
                }
            }
        }

        System.out.printf("%.2f\n", result);
    }


    static double compare(Node n1, Node n2) {
        double temp = Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2);
        return Math.sqrt(temp);
    }

    // N개의 별에 대한 좌표 구함
    // (N-1)! 수행하며 간선을 만들까??
    // 그런데 N = 100 이라
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        Node[] arr = new Node[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            arr[i] = new Node(i, x, y);
        }

        solution(arr);
        br.close();
    }
}
