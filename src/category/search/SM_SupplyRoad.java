package category.search;

import java.io.*;
import java.util.*;

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
                    int before = records[nx][ny];
                    int now = records[cursor.x][cursor.y] + maps[nx][ny];
                    if(now < before) {
                        records[nx][ny] = now;
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
