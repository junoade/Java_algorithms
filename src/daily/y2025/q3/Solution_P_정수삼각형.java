package daily.y2025.q3;

import java.util.*;

public class Solution_P_정수삼각형 {
    int[] dx = {1, 1};
    int[] dy = {0, 1};

    public int solution(int[][] triangle) {
        int answer = 0;

        final int N = triangle.length;
        final int M = triangle[N-1].length;

        int[][] dp = new int[N][M];
        dp[0][0] = triangle[0][0];

        for(int i = 0; i < N - 1; i++) {
            for(int j = 0; j < triangle[i].length; j++) {

                int curValue = dp[i][j];

                for(int d = 0; d < 2; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(!isNextOutbound(N, M, nx, ny)) {
                        // DP 점화식
                        int nextSum = curValue + triangle[nx][ny];
                        dp[nx][ny] = Math.max(nextSum, dp[nx][ny]);
                    }
                }
            }
        }

        answer = Arrays.stream(dp[N-1]).max().getAsInt();
        return answer;
    }

    boolean isNextOutbound(int N, int M, int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= M);
    }
}
