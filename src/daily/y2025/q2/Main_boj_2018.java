package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_boj_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int start = 1;
        int answer = 0;
        StringBuilder sb;
        for (; start < N; start++) {
            int sum = start;

            for (int end = start + 1; end < N; end++) {
                sum += end;
                if (N == sum) {
                    answer++;

                    break;
                } else if (N < sum) {
                    break;
                }
            }
        }
        answer++;
        System.out.println(answer);
    }
}
