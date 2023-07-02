package category.bruteforce;

import java.io.*;
import java.util.Arrays;

public class SE_FlySprays {

    static int[] dxCross = {0, -1, 0, 1};
    static int[] dyCross = {-1, 0, 1, 0};
    static int[] dxAcross = {-1, -1, 1, 1};
    static int[] dyAcross = {-1, 1, -1, 1};

    static int solution(int N, int M, int[][] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }

                int kills = Math.max(sprayCross(i, j, M, arr), sprayAcross(i, j, M, arr));
                max = Math.max(max, kills);
            }
        }

        return max;
    }

    static int sprayCross(int x, int y, int M, int[][] arr) {
        int count = arr[x][y];

        for (int d = 0; d < dxCross.length; d++) {
            // 한 방향을 결정하고
            // 해당 방향에 대해 m 번 반복
            int nx = x, ny = y;
            for (int m = 1; m < M; m++) {
                nx = nx + dxCross[d];
                ny = ny + dyCross[d];

                if (isOutbound(nx, ny, arr.length)) {
                    continue;
                }

                if (arr[nx][ny] == 0) {
                    continue;
                }

                count += arr[nx][ny];
            }
        }

        return count;
    }

    static int sprayAcross(int x, int y, int M, int[][] arr) {
        int count = arr[x][y];

        for (int d = 0; d < dxAcross.length; d++) {
            int nx = x, ny = y;
            for (int m = 1; m < M; m++) {
                nx = nx + dxAcross[d];
                ny = ny + dyAcross[d];

                if (isOutbound(nx, ny, arr.length)) {
                    continue;
                }

                if (arr[nx][ny] == 0) {
                    continue;
                }

                count += arr[nx][ny];
            }
        }
        return count;
    }

    static boolean isOutbound(int x, int y, int L) {
        return (x < 0 || x >= L) || (y < 0 || y >= L);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int[] inputs = processInput(br);
            int N = inputs[0], M = inputs[1];
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                arr[i] = processInput(br);
            }
            System.out.printf("#%d %d\n", t + 1, solution(N, M, arr));
        }
    }

    private static int[] processInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
