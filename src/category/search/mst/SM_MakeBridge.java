package category.search.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * --------------------------------------------------------------<br/>
 * <b>SM 하나로 D4 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 최소 신장 트리 구하기<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 모든 지점에 대해 각 지점을 출발 지점으로 했을 때<br/>
 * 매번 최단 거리를 구해 다리를 하나 지어주고 이동하는 방법 고안<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 (20/20) </b><br/>
 * <p> 메모리  / 256MB , 실행시간  0.9101s / 20s<br/>
 * --------------------------------------------------------------
 */
public class SM_MakeBridge {
    static class Edge implements Comparable<Edge> {
        int key;
        double cost;

        Edge(int key, double cost) {
            this.key = key;
            this.cost = cost;
        }

        // 오름차순 정렬하도록
        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.cost, e.cost);
        }
    }

    static int[] x;
    static int[] y;
    static boolean[] visited;

    static int N;
    static double rate;
    static double min;

    static void solution(){
        min = Double.MAX_VALUE;
        visited = new boolean[N];
        // 우선순위 큐(힙 자료구조)를 이용한 프림 알고리즘 이용
        /*for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            min = Math.min(min, prim(i));
        }*/
        min = prim(0);
    }

    static double prim(int startIdx) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        double result = 0.0;
        pq.offer(new Edge(startIdx, 0));

        int count = 0;
        while (!pq.isEmpty()) {
            Edge cursor = pq.poll();
            int cursorIdx = cursor.key;
            double cost = cursor.cost;

            if (visited[cursorIdx]) {
                continue;
            }

            visited[cursorIdx] = true;
            result += getTax(cost);

            for(int i = 0; i < visited.length; i++) {
                if(!visited[i]) {
                    pq.offer(new Edge(i, calculate(cursorIdx, i)));
                }
            }

            if(++count == N) {
                break;
            }
        }

        return result;
    }



    static double calculate(int srcIdx, int targetIdx) {
        double left = Math.pow(x[targetIdx] - x[srcIdx], 2);
        double right = Math.pow(y[targetIdx] - y[srcIdx], 2);
        return Math.sqrt(left + right);
    }

    static double getTax(double distance) {
        return rate * Math.pow(distance, 2);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            y = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rate = Double.parseDouble(br.readLine());
            solution();
            // System.out.printf("#%d %d\n", i + 1, (long)(min * 10 + 0.5) / 10);
            System.out.printf("#%d %.0f\n", i + 1, min);
        }
    }
}
