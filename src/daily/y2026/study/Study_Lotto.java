package daily.y2026.study;

import java.io.*;
import java.util.*;

public class Study_Lotto {
    static int[] arr;
    static boolean[] visited;
    static int[] tempAnswer;
    static final int MAX_LEN = 6;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int K = Integer.parseInt(st.nextToken());
        arr = new int[K];
        tempAnswer = new int[MAX_LEN];
        visited = new boolean[K];


        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 중복 x, 순서 x, 조합
        recursive(K, 0, 0);

        System.out.print(sb);
    }

    // k C 6
    static void recursive(int k, int startIdx, int depth) {
        if(depth == MAX_LEN) {
            setBuffer();
            return;
        }

        for(int i = startIdx; i < k; i++) {
            tempAnswer[depth] = arr[i];
            recursive(k, i + 1, depth + 1);
        }

    }

    static void setBuffer() {
        for(int v : tempAnswer) sb.append(v).append(" ");
        sb.append("\n");
    }
}
