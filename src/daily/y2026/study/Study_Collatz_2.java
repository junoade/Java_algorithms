package daily.y2026.study;

import java.io.*;

public class Study_Collatz_2 {

    // 메모이제이션 풀이

    static final int LIMIT = 1_000_000;
    static final int[] memo = new int[LIMIT + 1]; // 해당 값의 수열 길이를 메모이제이션

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        final int S = Integer.parseInt(input[0]);
        final int E = Integer.parseInt(input[1]);

        int maxDepth = Integer.MIN_VALUE;

        for(int n = S; n <= E; n++) {
            int depth = 1; // 자기 자신도 수열 길이의 일부
            long tempValue = n;

            maxDepth = Math.max(maxDepth, getLength(tempValue));

        }

        System.out.println(maxDepth);
    }

    static int getLength(long v) {
        if(v == 1) return 1;
        if(v <= LIMIT && memo[(int) v] != 0) return memo[(int) v];

        int len = getLength((v & 1) == 0 ? v >> 1 : v * 3 + 1) + 1;

        if (v <= LIMIT) memo[(int) v] = len;
        return len;
    }
}
