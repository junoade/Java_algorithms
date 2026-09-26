package daily.y2026.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Study_Perm01 {

    static StringBuilder sb = new StringBuilder();
    static int[] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        final int N = Integer.parseInt(inputs[0]), M = Integer.parseInt(inputs[1]);

        path = new int[M];
        recursive(N, M, 0);

        System.out.print(sb);
    }

    static void recursive(int n, int m, int depth) {
        if(depth == m) {
            printArr(path);
            return;
        }

        for(int i = 1; i <= n; i++) {
            path[depth] = i;
            recursive(n, m, depth + 1);
        }
    }

    static void printArr(int[] arr) {
        for(int i : arr) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
    }
}
