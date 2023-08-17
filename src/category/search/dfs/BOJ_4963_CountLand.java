package category.search.dfs;

import java.io.*;
import java.util.*;

public class BOJ_4963_CountLand {
    // 북 남 동 서 대각선
    static final int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static final int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    static int h, w;

    static int solution(int[][] maps) {
        // 1. 주어진 w x h 배열에 대해 탐색 수행
        boolean[][] visited = new boolean[h][w];

        int count = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(maps[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                count++;
                dfs(maps, visited, i, j);
            }
        }

        return count;
    }

    static void dfs(int[][] maps, boolean[][] visited, int x, int y) {
        visited[x][y] = true;

        for(int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOutbound(nx, ny) || maps[nx][ny] == 0 || visited[nx][ny]) {
                continue;
            }

            dfs(maps, visited, nx, ny);
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= h) || (y < 0 || y >= w);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) {
                break;
            }

            int[][] maps = new int[h][w];
            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(solution(maps)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
