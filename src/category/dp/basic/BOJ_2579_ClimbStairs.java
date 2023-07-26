package category.dp.basic;

import java.io.*;

public class BOJ_2579_ClimbStairs {

    static int N;

    static void solution(int[] climbs) {
        // 0번쨰 행 : c[i-1] 밟고 올라온 경우
        // 1번째 행 : c[i-1] 밟지 않고 올라온 경우
        int[][] dp = new int[2][N+1];

        // 초기값
        dp[1][1] = climbs[1];

        for(int i = 2; i <= N; i++) {
            // c[i-1] 밟고 올라온 경우부터 계산
            // c[i-1]은 c[i-2] 가 아닌 c[i-3]에서 올라와야함
            dp[0][i] = dp[1][i-1] + climbs[i];

            // c[i-1] 밟지 않고 올라온 경우 계산
            // c[i-2]에서 바로 올라옴
            // c[i-3]를 밟고 온 경우(dp[0][i-2]) 아닌 경우 (dp[1][i-2]) 따짐
            dp[1][i] = Math.max(dp[0][i-2], dp[1][i-2]) + climbs[i];
        }

        System.out.println(Math.max(dp[0][N], dp[1][N]));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] climbs = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            climbs[i] = Integer.parseInt(br.readLine());
        }

        solution(climbs);
    }
}
