package category.search;

import java.io.*;
import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>삼성 D4 - 보급로 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * NxN 배열에서 격자의 요소의 값(비용)이 1~9로 서로 다르며<br/>
 * 격자에서 4방향으로 이동할 수 있다고 하자.<br/>
 * (0,0) -> (n-1, n-1)까지 이동 후 최소 비용 반환하는 솔루션<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * **좌표 공간 상 비용이 다른 경우에 대해 좌표 이동을 이용한 BFS 탐색**<br/>
 * 다음 지점이 이미 방문했던 지점이더라도<br/>
 * 지금 방문하고 있는 경로의 비용이 최소 비용 경로일 수 있다.<br/>
 * 지금 방문하고 있는 경로의 비용 vs 이전 방문 경로의 비용을 비교해서 최소값으로 갱신해주고<br/>
 * 갱신해야하는 경우라면 다음 좌표를 해당 방문 큐에 삽입해주자. (앞서 실패했던 이유)<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * 통과<br/>
 * <p> 힙, 정적 메모리  / 256MB, 스택 메모리 / 1MB,  실행시간  / 20, 000ms<br/>
 * --------------------------------------------------------------
 */
public class SM_SupplyRoad {

    // u - r - d - l
    static final int[] dx = {-1, 0, 1, 0}; // row
    static final int[] dy = {0, 1, 0, -1}; // col

    static class Vertex {
        final int x;
        final int y;

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("{%d, %d}", x, y);
        }
    }

    static int solution(int[][] maps, int n) {
        // 방문 여부 확인
        boolean[][] visited = new boolean[n][n];
        // 총 이동 시간 확인
        int[][] records = new int[n][n];

        Queue<Vertex> queue = new ArrayDeque<>();
        queue.offer(new Vertex(0, 0));

        while(!queue.isEmpty()) {
            Vertex cursor = queue.poll();
            visited[cursor.x][cursor.y] = true;

            // 다음 위치를 결정해야
            // 비용이 제일 적게 드는 곳으로 이동하도록
            for(int d = 0; d < dx.length; d++) {
                int nx = cursor.x + dx[d];
                int ny = cursor.y + dy[d];

                if(isOutbound(nx, ny, n)) {
                    continue;
                }

                if(!visited[nx][ny]) {
                    records[nx][ny] = records[cursor.x][cursor.y] + maps[nx][ny]; // 이동거리를 합산
                    visited[nx][ny] = true;
                    queue.offer(new Vertex(nx, ny));
                } else {
                    // 다음 노드가 방문했던 노드이지만 만약 해당 경로의 이동 비용이 더 적다면 더 적은 비용으로 갱신
                    int before = records[nx][ny]; // 새로운 위치에 대한 이전 비용
                    int now = records[cursor.x][cursor.y] + maps[nx][ny];
                    if(now < before) {
                        records[nx][ny] = now;
                        queue.offer(new Vertex(nx, ny)); // 과연?
                    }
                }
            }
        }
        return records[n-1][n-1];
    }


    static boolean isOutbound(int x, int y, int N) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] maps = initMaps(br, N);
            System.out.printf("#%d %d\n", i+1, solution(maps, N));
        }
    }

    private static int[][] initMaps(BufferedReader br, int N) throws IOException {
        int[][] maps = new int[N][N];
        for(int[] row : maps) {
            int[] input = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(input, 0, row, 0, N);
        }
        return maps;
    }
}
