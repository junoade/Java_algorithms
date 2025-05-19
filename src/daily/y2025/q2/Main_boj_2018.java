package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int start = 1, end = 1, sum = 1, answer = 0;

        while (end <= sum) {
            if (sum < N) {
                end++;
                sum += end; // end 포인터 이동
            } else if (sum > N) {
                sum -= start; // start 포인터를 이동
                start++;
            } else {
                answer++;
                sum -= start; // start 포인터 이동
                start++;
            }
        }
        System.out.println(answer);
    }
}
