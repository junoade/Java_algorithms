package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_2018 {
    static int solution(int N) {
        int start = 1, end = 1, sum = 1, count = 0;
        while (end <= N) {
            if(sum < N) sum += ++end;
            else if(sum > N) sum -= start++;
            else {
                count++;
                sum -= start++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(solution(N));
    }
}
