package daily.y2026.study;

import java.io.*;
import java.util.*;

public class Study_Perm03 {

    static int[] path;
    static int[] numbers;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]), M = Integer.parseInt(temp[1]);

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[N];
        path = new int[M];

        recursive(N, M, 0);

        System.out.print(sb);
    }

    static void recursive(int N, int M, int depth) {
        if(depth == M) {
            setCurrentPath();
            return;
        }

        for(int i = 0; i < numbers.length; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            path[depth] = numbers[i];
            recursive(N, M, depth + 1);

            visited[i] = false; // 가보지 않은 경로를 위해 back-tracking
        }
    }

    static void setCurrentPath() {
        for(int v : path) sb.append(v).append(" ");
        sb.append("\n");
    }
}
