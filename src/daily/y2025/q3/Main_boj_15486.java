package daily.y2025.q3;

import java.io.*;
import java.util.Arrays;

public class Main_boj_15486 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] t = new int[N + 1];
        int[] p = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            String[] temp = br.readLine().split(" ");
            t[i] = Integer.parseInt(temp[0]);
            p[i] = Integer.parseInt(temp[1]);
        }

        System.out.println(solution(N, t, p));
    }

    static int solution(int N, int[] t, int[] p) {

        final int INIT = 0;
        int[] dp = new int[N + 2];
        Arrays.fill(dp, INIT);

        // i 번쨰 날 안하고 i+1 날 하게 될때 next Price 와 dp 테이블 값
        for(int i = 1; i <= N; i++) {

            // 1) 아무 것도 안할때 dp 비교
            // - 스킵 전파: 이전까지 최대 이익을 i 일에도 반영
            dp[i] = Math.max(dp[i], dp[i - 1]);
            // - 스킵 전파(명시): 다음 날로 최대 이익 전달
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // 2) 상담하는게 최대값 갱신을 하게 되는지 비교!
            // - 상담 진행 전파
            int end = i + t[i];     // 상담이 끝나는 '날'
            if (end <= N + 1) {
                dp[end] = Math.max(dp[end], dp[i] + p[i]);
            }
        }

        return Math.max(dp[N], dp[N + 1]);
    }
}
