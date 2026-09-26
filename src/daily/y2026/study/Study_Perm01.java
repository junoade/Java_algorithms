package daily.y2026.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Study_Perm01 {

    static StringBuilder sb = new StringBuilder();
    static StringBuilder path = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        final int N = Integer.parseInt(inputs[0]), M = Integer.parseInt(inputs[1]);

        recursive(N, M, 0);

        System.out.print(sb);
    }

    static void recursive(int n, int m, int depth) {
        if(depth == m) {
            sb.append(path).append('\n');
            return;
        }

        for(int i = 1; i <= n; i++) {
            int len = path.length();
            path.append(i).append(" ");
            recursive(n, m, depth + 1);
            path.setLength(len); // 이전 path 를 유지하기 위해 back-traverse

        }
    }
}
