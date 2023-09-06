package category.search.dijkstra;

import java.io.*;
import java.util.*;

public class BOJ_1238_Party {
    static class Node {
        int vertex;
        int weight;
        Node next;

        Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }

    static int N, M, X;
    static Node[] adjList;
    static final int INF = Integer.MAX_VALUE;

    // 거꾸로 생각하기
    // X위치에서 -> 각 지점에 대한 최단 거리 비용을 구한다. (다익스트라)
    // 한 위치에서 X위치로 향하는 최단 거리 비용을 구함 (다익스트라) x N

    static void solution() {
        int[] dist1 = new int[N + 1];
        dijkstra(dist1);

        // 중간 정답 기록
        int[] answer = Arrays.copyOf(dist1, N + 1);

        int max = 0;
        for (int s = 1; s <= N; s++) {
            int cost = dijkstra(s);
            max = Math.max(max, cost + answer[s]);
        }

        System.out.println(max);
    }

    static void dijkstra(int[] dist) {
        boolean[] v = new boolean[N + 1];
        Arrays.fill(dist, INF);

        //int min = 0, stopOver = 0;
        dist[X] = 0;
        for(int i = 1; i <= N; i++) {
            // step1) 미방문 영역 중 출발지에서 가장 가중치가 작은 정점을 경유지로 선택
            // stopOver = -1;
            int minVertex = -1;
            int min = INF;
            for (int j = 1; j <= N; j++) {
                if (!v[j] && min > dist[j]) {
                    minVertex = j;
                    min = dist[j];
                }
            }

            if (minVertex == -1) break;


            // step2) 방문 처리
            v[minVertex] = true;

            // X-> 모든 정점에 대한 최단 거리 비용 구할 땐 실행 X
            // 한 정점에서 X까지 최단 비용 구할 땐 실행 O
            /*if (flag && stopOver == X) {
                break;
            }
            */
            // step3) 경유지를 이용하여 미방문 정점들에 대한 출발지에서 자신으로의 최소 비용 고려
            for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
                if (!v[temp.vertex] && dist[temp.vertex] > min + temp.weight) {
                    dist[temp.vertex] = min + temp.weight;
                }
            }
        }
    }

    // 주의!
    static int dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        boolean[] v = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.offer(new int[]{start, dist[start]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int minVertex = cur[0];
            int min = cur[1];

            if(v[minVertex]) {
                continue;
            }
            v[minVertex] = true;

            if(minVertex == X) {
                break;
            }

            for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
                if(!v[temp.vertex] && dist[temp.vertex] != 0 && dist[temp.vertex] > min + temp.weight) {
                    dist[temp.vertex] = min + temp.weight;
                    pq.offer(new int[]{temp.vertex, dist[temp.vertex]});
                }
            }
        }

        return dist[X];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new Node[N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[start] = new Node(to, w, adjList[start]);
        }

        solution();
        br.close();
    }
}
