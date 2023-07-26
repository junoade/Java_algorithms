package category.dp.basic;

import java.io.*;

public class BOJ_2579_ClimbStairs {

    static int N;

    static void solution(int[] climbs) {
        int[] dp = new int[N + 1];

        // 초기값
        int count = 0;
        dp[1] = climbs[1];
        count++;

        for(int i = 2; i <= N; i++) {
            int upOnce = dp[i-1] + climbs[i];
            int upTwice = dp[i-2] + climbs[i];

            if(upOnce > upTwice) {
                if(count % 2 != 0) {
                    count++;
                    dp[i] = upOnce;
                    continue;
                }
            }

            count = 1;
            dp[i] = upTwice;
            /*if(count % 3 != 0) {
                if(upOnce > upTwice) {
                    count++;
                    dp[i] = upOnce;
                } else {
                    count = 1;
                    dp[i] = upTwice;
                }
            } else {
                count = 1;
                dp[i] = upTwice;
            }*/
        }

        System.out.println(dp[N]);
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
