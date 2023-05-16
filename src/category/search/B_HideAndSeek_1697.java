package category.search;

import java.io.*;
import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 숨바꼭질 1697</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 일차원 좌표 공간[1, 100,000]에서의 최소 이동 거리(time) 구하기<br/>
 * 이동 방법 3가지<br>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 기본적인 BFS 탐색<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  17560KB / 128MB , 실행시간  180ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class B_HideAndSeek_1697 {

    static class Point {
        int pos;
        int time;

        Point(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    // 어느 방향으로 이동하는 지 기록
    static boolean[] visited;
    static final int[] DIR = {2, 1, -1};
    static int N = 100000;

    static void solution(int A, int B) {
        visited = new boolean[N + 1];
        int min = bfs(A, B);
        System.out.println(min);
    }

    static int bfs(int src, int target) {
        int minValue = Integer.MAX_VALUE;
        Queue<Point> queue = new ArrayDeque<>();
        visited[src] = true;
        queue.offer(new Point(src, 0));

        while (!queue.isEmpty()) {
            Point cursor = queue.poll();
            if (cursor.pos == target) {
                minValue = Math.min(minValue, cursor.time);
            }

            for (int d = 0; d < DIR.length; d++) {
                int newPos = cursor.pos;
                if (d == 0) {
                    newPos *= DIR[d];
                } else {
                    newPos += DIR[d];
                }

                if (isOutbound(newPos)) {
                    continue;
                }

                if (!visited[newPos]) {
                    visited[newPos] = true;
                    queue.offer(new Point(newPos, cursor.time + 1));
                }
            }
        }
        return minValue;
    }

    static boolean isOutbound(int n) {
        return (n < 0 || n > N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = inputs[0];
        int B = inputs[1];
        solution(A, B);
    }
}
