package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_boj_10809 {
    /**
     * 알파벳 찾기
     * https://www.acmicpc.net/problem/10809
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        final int MAX = 26;
        int[] arr = new int[MAX];
        Arrays.fill(arr, -1);

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int compIdx = c - 'a';
            if(arr[compIdx] == -1) {
                arr[compIdx] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }
}
