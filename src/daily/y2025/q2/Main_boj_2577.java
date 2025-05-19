package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_2577 {
    /**
     * BOJ2577 숫자의 개수
     * https://www.acmicpc.net/problem/2577
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = 3, M = 10;
        int answer = 1;

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            answer *= input;
        }

        String s = String.valueOf(answer);
        int[] arr = new int[M];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - '0']++;
        }

        for (int num : arr) {
            System.out.println(num);
        }
    }
}
