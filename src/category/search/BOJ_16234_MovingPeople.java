package category.search;

import java.io.*;
import java.util.*;

public class BOJ_16234_MovingPeople {

    static int N;
    static int L, R;

    static int[][] maps;

    // 상 - 하 - 좌 - 우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 현재 day에 대해
    // N * N 공간을 돌아가면서
    // 연합체가 가능한지 판단
    // 이미 연합체라면 PASS
    // 연합체가 될 수 없어도 PASS
    // 연합체가 될 수 있으면 가능한 연합체들을 묶어줌
    // 연합체 인구수 / 칸 개수로 나눠줌
    static void solution() {
        int day = 0;

        while (true) {
            boolean[][] visited = new boolean[N][N];
            List<int[]> union = new ArrayList<>();
            boolean hasUnion = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j] || !isAbleUnion(i, j)) {
                        continue;
                    }
                    dfs(union, visited, i, j);
                    // 연합체를 구성했는지 확인
                    if (!union.isEmpty()) {
                        hasUnion = true;
                        int sum = 0, size = union.size();
                        for (int[] pos : union) {
                            sum += maps[pos[0]][pos[1]];
                        }

                        for (int[] pos : union) {
                            maps[pos[0]][pos[1]] = sum / size;
                        }
                        union.clear();
                    }
                }
            }

            if(!hasUnion) {
                break;
            }
            day++;
        }

        System.out.println(day);
    }

    static void dfs(List<int[]> union, boolean[][] visited, int x, int y) {
        if(visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        union.add(new int[]{x, y});

        int value = maps[x][y];

        // 탐색부터 해본다
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny) || visited[nx][ny]) {
                continue;
            }

            int gap = Math.abs(value - maps[nx][ny]);
            boolean cond = gap >= L && gap <= R;
            if (!cond) {
                continue;
            }

            dfs(union, visited, nx, ny);
        }

        // base case : 연합체를 구성할 수 없을 때 종료
    }

    static boolean isAbleUnion(int x, int y) {
        int value = maps[x][y];

        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny)) {
                continue;
            }

            int gap = Math.abs(value - maps[nx][ny]);
            boolean cond = gap >= L && gap <= R;
            if (!cond) {
                continue;
            }
            return true;
        }

        return false;
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/category/search/input/input_bj_16234_fix.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }

}
