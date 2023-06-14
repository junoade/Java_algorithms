package category.search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 2178 미로 탐색</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * BFS 탐색<br/>
 * 최소 거리는 2차원 배열 map을 탐색하면서 누적합으로 구한다.<br/>
 * 최소 거리를 보장하는 좌표들의 순서를 구하려거든 코드트리 문제 '신들의 전쟁' 참고.<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 16552KB = 약 16MB / 192 MB , 실행시간  204ms / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class B_SearchMaze {
    static class Vertex {
        final int x;
        final int y;
        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;

    // d, l, r, u;
    static int[] dx = {-1, 0, 0, 1}; // row
    static int[] dy = {0, -1, 1, 0}; // col


    static void solution() {
        Vertex init = new Vertex(0, 0);
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.offer(init);

        while(!queue.isEmpty()) {
            Vertex cursor = queue.poll();
            visited[cursor.x][cursor.y] = true;

            if(cursor.x == N - 1 && cursor.y == M - 1) {
                break;
            }

            for(int i = 0; i < dx.length; i++) {
                int nx = cursor.x + dx[i];
                int ny = cursor.y + dy[i];

                if(isOutbound(nx, ny)) {
                    continue;
                }

                // 방문할 수 없는 노드(좌표)라면
                if(map[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }

                map[nx][ny] += map[cursor.x][cursor.y];
                visited[nx][ny] = true; // 미리 해주기
                queue.offer(new Vertex(nx, ny));
            }
        }
        int answer = map[N-1][M-1];
        System.out.println(answer);
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= M);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        solution();
    }
}
