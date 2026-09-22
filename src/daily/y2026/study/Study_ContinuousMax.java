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

        int windowSum = 0;
        for(int i = 0; i < m; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;
        // m 번째 부터 한칸씩 이동하도록 리팩토링
        for(int i = m; i < n; i++) {
            int leftIdx = i - m, rightIdx = i;
            windowSum += arr[rightIdx] - arr[leftIdx];
            maxSum = Math.max(maxSum, windowSum);
        }

        System.out.println(maxSum);
    }

}
