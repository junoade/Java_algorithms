package category.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * --------------------------------------------------------------<br/>
 * <b> 백준 - 데스 나이트 16948  </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 기본 2차원 좌표 공간에서의 BFS을 이용한 최소 이동 거리<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 기본 BFS 알고리즘 + 방문 불가능한지 확인 코드 작성<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  15076KB / 512MB , 실행시간  148ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class B_DeathKnight {

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] records;
    static boolean[][] visited;
    static int N;

    // r, c
    static final int[] dx = {-2, -2, 0, 0, 2, 2};
    static final int[] dy = {-1, 1, -2, 2, -1, 1};

    static void solution(int[] inputs) {
        Point start = new Point(inputs[0], inputs[1]);
        Point end = new Point(inputs[2], inputs[3]);

        Queue<Point> queue = new ArrayDeque<>();
        visited[start.x][start.y] = true;
        queue.offer(start);

        boolean isAble = false;

        while (!queue.isEmpty()) {
            Point cursor = queue.poll();

            if (cursor.x == end.x && cursor.y == end.y) {
                isAble = true;
            }

            for (int d = 0; d < dx.length; d++) {
                int nx = cursor.x + dx[d];
                int ny = cursor.y + dy[d];

                if (isOutbound(nx, ny)) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                records[nx][ny] = records[cursor.x][cursor.y] + 1;
                queue.offer(new Point(nx, ny));
            }
        }

        if(!isAble) {
            System.out.println(-1);
        } else {
            System.out.println(records[end.x][end.y]);
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        records = new int[N][N];
        visited = new boolean[N][N];

        solution(inputs);
    }
}
