package daily.y2026.study;

import java.io.*;

public class Study_Perm02 {
    // 중복 X , 사전순 오름차순

    static int[] path;
    static boolean[] digits;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        final int N = Integer.parseInt(temp[0]), M = Integer.parseInt(temp[1]);

        path = new int[M];
        digits = new boolean[N + 1];

        recursive(N, M, 0);

        System.out.print(sb);
    }

    static void recursive(int N, int M, int depth) {
        if(depth == M) {
            setCurrentPath();
            return;
        }

        for(int i = 1; i <= N; i++) {
            if(digits[i]) {
                continue;
            }

            path[depth] = i;
            digits[i] = true; // 숫자 중복 체크 셋
            recursive(N, M, depth + 1);
            digits[i] = false; // 숫자 중복 체크 해제
        }

    }

    static void setCurrentPath() {
        for(int v : path) {
            sb.append(v).append(" ");
        }
        sb.append("\n");
    }
}
