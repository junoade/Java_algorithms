package category.search.bfs;

import java.io.*;
import java.util.*;

/* 23-06-19 복습*/
public class B_HideAndSeek_Review {
    static int N, M;
    static final int MAX = 100_000;
    static boolean[] visited;
    static int[] dist;

    static final int[] dir = {-1, 1, 2};

    static void solution() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        visited[N] = true;

        while(!queue.isEmpty()) {
            Integer value = queue.poll();

            // 도달 여부 확인
            if(value == M) {
                break;
            }

            for(int d = 0; d < dir.length; d++) {
                int newValue = 0;
                if(d == 2) {
                    newValue = value * dir[d];

                } else {
                    newValue = value + dir[d];
                }

                // 방문 가능 여부 판단
                // 범위를 넘을 때 || 이미 방문했을 때
                if(isOutbound(newValue) || visited[newValue]) {
                    continue;
                }

                visited[newValue] = true;
                dist[newValue] = dist[value] + 1;
                queue.offer(newValue);
            }
        }

        System.out.println(dist[M]);
    }


    static boolean isOutbound(int n) {
        return (n < 0 || n > MAX);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temps = br.readLine().split(" ");

        N = Integer.parseInt(temps[0]);
        M = Integer.parseInt(temps[1]);

        visited = new boolean[MAX + 1];
        dist = new int[MAX + 1];

        solution();
    }
}
