package daily.y2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_원숭이키우기 {
    static int N; // 최대 레벨 [1, 50]
    static int D; // 훈련 기간 [1, 100]
    static int[] characters = new int[51];
    static int[] powers = new int[51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());


        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            characters[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            powers[i] = Integer.parseInt(st.nextToken());
        }
        D = Integer.parseInt(br.readLine());

        long r = 0L;
        long[] dp = new long[101]; // MAX DATE

        for (int i = 1; i <= N; i++) {
            r += (long) characters[i] * powers[i];
            characters[i] = Math.min(D, characters[i]);
        }

        for (int i = 1; i <= N; i++) {
            while(characters[i]-- >= 0) {
                for (int j = D; j >= 0; j--) {
                    for (int k = i + 1; k <= N && k + j - i <= D; k++) {
                        dp[k + j - i] = Math.max(dp[k + j - i], dp[j] + powers[k] - powers[i]);
                    }
                }
            }
        }

        System.out.println(dp[D] + r);

    }
}
