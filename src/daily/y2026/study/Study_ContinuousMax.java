package daily.y2026.study;

import java.io.*;
import java.util.*;

public class Study_ContinuousMax {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for(int i = 0; i < m; i++) {
            sum += arr[i];
        }

        int maxSum = sum;
        for(int i = 1; i <= n - m; i++) {
            int prevIdx = i - 1, nextIdx = i + m - 1;
            sum = sum - arr[prevIdx] + arr[nextIdx];
            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }

}
