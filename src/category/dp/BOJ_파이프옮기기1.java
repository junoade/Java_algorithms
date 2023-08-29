package category.dp;

import java.io.*;
import java.util.*;


public class BOJ_파이프옮기기1 {
    static int N;
    static int[][] maps;
    static int[][][] dp;

    static final int HORIZON = 0;
    static final int VERTICAL = 1;
    static final int CROSS = 2;

    static void solution() {
        // 기록용 배열
        // 한 공간에 대해
        // 1. 가로로 파이프를 놓게 되는 경우에 대한 파이프의 개수 기록
        // 2. 세로로 파이프를 놓게 되는 경우에 대한 파이프의 개수 기록
        // 3. 대각선으로 파이프를 놓게 될 때에 대한 파이프의 개수 기록
        dp = new int[3][N][N];

        // 초기화 수행
        dp[HORIZON][0][1] = 1;
        for (int i = 2; i < N; i++) {
            if(maps[0][i] == 0) {
                dp[HORIZON][0][i] = dp[HORIZON][0][i - 1];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {

                if (maps[i][j] != 0) {
                    continue;
                }

                // 대각선으로 파이프를 놓을 수 있는 조건 체크
                if (maps[i][j - 1] == 0 && maps[i - 1][j] == 0) {
                    // 이전에 가로 위치에서 대각선으로 전이
                    // 이전에 세로 위치에서 대각선으로 전이
                    // 이전에 대각선 위치에서 대각선으로 전이
                    dp[CROSS][i][j] = dp[HORIZON][i - 1][j - 1] + dp[VERTICAL][i - 1][j - 1] + dp[CROSS][i - 1][j - 1];
                }

                // 가로로 파이프 놓기
                // 가로에서 전이, 대각선 이동 후에 전이
                dp[HORIZON][i][j] = dp[HORIZON][i][j - 1] + dp[CROSS][i][j - 1];

                // 세로로 파이프 놓기
                // 세로에서 전이, 대각선에서 전이
                dp[VERTICAL][i][j] = dp[VERTICAL][i - 1][j] + dp[CROSS][i - 1][j];
            }
        }

        int sum = dp[HORIZON][N - 1][N - 1] + dp[VERTICAL][N - 1][N - 1] + dp[CROSS][N - 1][N - 1];
        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        maps = new int[N][N];
        dp = new int[3][N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        br.close();
    }

}
