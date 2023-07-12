package category.search.dfs;

import java.io.*;
import java.util.*;

public class SM_CheeseThief_7733 {

    // 기록
    static int N;
    static int[][] graphs;
    static int max;

    // 상 - 하 - 좌 - 우
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int solution() {
        max = Integer.MIN_VALUE;

        for (int day = 0; day < 100; day++) {
            boolean[][] visited = new boolean[N][N];

            // dfs 탐색 수행하여 치즈 덩어리 갯수 반환
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graphs[i][j] <= day || visited[i][j]) {
                        continue;
                    }
                    count++;
                    dfs(i, j, visited, day);
                }
            }
            max = Math.max(max, count);
        }

        return max;
    }

    // 미리 먹고 해당 메소드를 호출하게 됨;
    // count = 1일 때 먹을 수 있는 만큼 탐색(상태 변경) 후 종료
    static void dfs(int x, int y, boolean[][] visited, int day) {
        visited[x][y] = true;

        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny) || graphs[nx][ny] <= day || visited[nx][ny]) {
                continue;
            }

            dfs(nx, ny, visited, day);
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("res/2307/0712-02-input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            graphs = new int[N][N];

            // 입력 처리
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int j = 0;
                while (st.hasMoreTokens()) {
                    int value = Integer.parseInt(st.nextToken());
                    graphs[i][j] = value;
                    j++;
                }

            }
            sb.append("#").append(tc).append(" ").append(solution()).append("\n");
        }
        System.out.print(sb);
    }
}
