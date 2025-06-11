package daily.y2025.q2;

import java.io.*;
import java.util.*;

public class Main_boj_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(arr);

    }

    static void solution(int[] arr) {
        final int N = arr.length;
        int answer = 0;

        Arrays.sort(arr);

        for (int k = 0; k < N; k++) {
            int i = 0, j = N-1, curValue = arr[k];

            while (i < j) {

                if(i == k) { i++; continue; }
                if(j == k) { j--; continue; }

                int sum = arr[i] + arr[j];
                if(sum > curValue) {
                    j--;
                } else if(sum < curValue) {
                    i++;
                } else {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);

    }
}
