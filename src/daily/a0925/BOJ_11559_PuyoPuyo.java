package daily.a0925;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11559_PuyoPuyo {

    static char[][] maps;
    static final int N = 12;
    static final int M = 6;

    // 동-서-남-북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};

    static void solution() {
        // step1) 현재 상태에서 서로 영향을 주지 않는 1연쇄 범위들을 수행
        int count = 0;
        while (true) {
            // log("[step1] 이전");
            boolean hasChanged = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (maps[i][j] != '.' && countPuyo(i, j, new boolean[N][M]) >= 4) {
                        dfs(i, j, new boolean[N][M]);
                        hasChanged = true;
                    }
                }
            }
            // log("[step1] 이후");
            if (!hasChanged) {
                break;
            } else {
                count++;
            }


            // step2) 중력 작용 구현 후 반복
            for (int col = 0; col < M; col++) {

                int mostBottom = N - 1;
                for (int row = N - 1; row > 0; row--) {
                    if (maps[row][col] == '.') {
                        mostBottom = row;
                        for (int i = row - 1; i >= 0; i--) {
                            if (maps[i][col] != '.') {
                                // swap
                                char temp = maps[i][col];
                                maps[i][col] = maps[mostBottom][col];
                                maps[mostBottom][col] = temp;
                                break; // 빼먹어서 문제 생겼다
                            }
                        }
                    }
                }
            }
            // step3) 더 이상 변하는 요소가 없을 때 까지 반복
            // log("[step2]");
        }
        System.out.println(count);
    }

    static int countPuyo(int x, int y, boolean[][] v) {
        v[x][y] = true;
        char cur = maps[x][y];
        int result = 1;

        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny) || cur != maps[nx][ny] || v[nx][ny]) {
                // return result;
                continue;
            }

            result += countPuyo(nx, ny, v);
        }
        return result;
    }

    static void dfs(int x, int y, boolean[][] v) {
        v[x][y] = true;
        char cur = maps[x][y];
        maps[x][y] = '.';

        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny) || cur != maps[nx][ny] || v[nx][ny]) {
                continue;
            }

            dfs(nx, ny, v);
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= M);
    }

    static void log(String msg) {
        System.out.println("================= " + msg + " ================");
        for (char[] a : maps) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("====================================");
    }

    public static void main(String[] args) throws Exception {
        // content 루트 부터 들어가야 된다.
        // System.setIn(new FileInputStream("src/daily/a0925/input/input_boj_11559_given01"));
        // System.setIn(new FileInputStream("src/daily/a0925/input/input_boj_11559_test01"));
        // System.setIn(new FileInputStream("src/daily/a0925/input/input_boj_11559_test02"));
        // System.setIn(new FileInputStream("src/daily/a0925/input/input_boj_11559_corner"));
        // System.setIn(new FileInputStream("src/daily/a0925/input/input_boj_11559_corner3"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        maps = new char[N][M];
        for (int i = 0; i < N; i++) {
            maps[i] = br.readLine().toCharArray();
        }

        solution();
        br.close();
    }
}
