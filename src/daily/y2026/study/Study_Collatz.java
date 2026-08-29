package daily.y2026.study;

import java.io.*;

public class Study_Collatz {

    // 백준 6615 콜라츠의 추측
    // 정올 https://jungol.co.kr/contest/4303/problem/4?cursor=ImNfNDMwMyIsMCwz
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        final int S = Integer.parseInt(input[0]);
        final int E = Integer.parseInt(input[1]);

        int maxDepth = Integer.MIN_VALUE;

        for(int n = S; n <= E; n++) {
            int depth = 1; // 자기 자신도 수열 길이의 일부
            long tempValue = n;

            while(true) {

                if(tempValue % 2 == 0) {
                    tempValue /= 2;
                } else {
                    tempValue = tempValue * 3 + 1;
                }

                depth++;

                if(tempValue == 1) {
                    // 비교
                    maxDepth = Math.max(maxDepth, depth);
                    break;
                }
            }

        }

        System.out.println(maxDepth);
    }
}
